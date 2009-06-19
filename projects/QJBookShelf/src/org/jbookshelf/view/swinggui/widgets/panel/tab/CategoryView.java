/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import icons.IMG;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.swing.JScrollPane;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.HibernateUtil;
import org.jbookshelf.model.db.Unique;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.widgets.ProgressBar;
import org.jdesktop.swingx.JXTree;

/**
 * @author eav
 */
public class CategoryView
    extends CollectionView
{
    private class CategoryNode
        extends DefaultMutableTreeNode
    {
        private final Category category;

        private CategoryNode(
            @Nonnull final Category category )
        {
            super( category.getName() );
            this.category = category;
        }

        /**
         * @return the category
         */
        public Category getCategory()
        {
            return this.category;
        }

        @Override
        public boolean isLeaf()
        {
            return false;
        }
    }

    private final DefaultMutableTreeNode root  = new DefaultMutableTreeNode();
    private final DefaultTreeModel       model = new DefaultTreeModel( root );
    private final JXTree                 tree  = new JXTree( model );

    private static final Logger          log   = Logger.getLogger( CategoryView.class );

    public CategoryView()
    {
        super();
        setName( "Categories" );
        setIcon( IMG.icon( IMG.FEED_SUBSCRIBE_PNG ) );

        setLayout( new BorderLayout() );

        add( new JScrollPane( tree ), BorderLayout.CENTER );

        tree.setRootVisible( false );
        tree.setOpenIcon( IMG.icon( IMG.FEED_SUBSCRIBE_PNG ) );
        tree.setClosedIcon( IMG.icon( IMG.FEED_SUBSCRIBE_PNG ) );
        tree.setLeafIcon( IMG.icon( IMG.BOOK_PNG ) );
        initListeners();
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.panel.CollectionTab#search(org.jbookshelf.view.swinggui.widgets.panel.SearchParameters)
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public void search(
        final Parameters p )
    {
        root.removeAllChildren();
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Category>, CategoryNode>()
        {
            @Override
            protected List<Category> doInBackground()
            {
                final Session session = HibernateUtil.getSession();
                try
                {
                    final List<Category> list = session.createQuery( buildQuery( p ) ).list();

                    model.reload( root );
                    for ( final Category category : list )
                    {
                        publish( new CategoryNode( category ) );
                    }
                    return list;
                }
                catch ( final HibernateException e )
                {
                    log.error( e, e );
                    throw new Error( e );
                }
                finally
                {
                    session.close();
                }
            }

            @Override
            protected void process(
                final List<CategoryNode> chunks )
            {
                for ( final CategoryNode categoryNode : chunks )
                {
                    model.insertNodeInto( categoryNode, root, 0 );
                }
            }
        } );
    }

    private void initListeners()
    {
        tree.addMouseListener( new CollectionPopupListener() );

        tree.addTreeWillExpandListener( new TreeWillExpandListener()
        {
            @Override
            public void treeWillCollapse(
                final TreeExpansionEvent event )
            {}

            @Override
            public void treeWillExpand(
                final TreeExpansionEvent event )
            {
                final Object node = event.getPath().getLastPathComponent();
                if ( node instanceof CategoryNode )
                { // lazy load children and books of category
                    loadChildren( (CategoryNode) node );
                }
            }
        } );

        tree.addTreeSelectionListener( new TreeSelectionListener()
        {
            @Override
            public void valueChanged(
                final TreeSelectionEvent e )
            {
                final List<Unique> list = new ArrayList<Unique>();
                for ( final TreePath treePath : tree.getSelectionPaths() )
                {
                    final Object object = treePath.getLastPathComponent();

                    if ( object instanceof CategoryNode )
                    {
                        list.add( ((CategoryNode) object).getCategory() );
                    }
                    else if ( object instanceof BookNode )
                    {
                        list.add( ((BookNode) object).getBook() );
                    }
                }
                Single.instance( BookShelfMediator.class ).uniquesSelected( list );
            }
        } );
    }

    /**
     * lazyly load node's children
     * 
     * @param categoryNode node to fill
     */
    private void loadChildren(
        final CategoryNode categoryNode )
    {
        if ( categoryNode.getChildCount() == 0 )
        { // not loaded yet
            final Category category = categoryNode.getCategory();

            final Set<Category> children = BookShelf.getChildren( category );
            for ( final Category child : children )
            {
                model.insertNodeInto( new CategoryNode( child ), categoryNode, 0 );
            }

            final Set<Book> books = BookShelf.getBooks( category );
            for ( final Book book : books )
            {
                model.insertNodeInto( new BookNode( book ), categoryNode, 0 );
            }
        }
    }

    @Override
    protected String buildQuery(
        final Parameters p )
    {
        final String text = p.get( Keys.SEARCH_TEXT );
        final StringBuilder q = new StringBuilder( "from Category c " );
        if ( text != null && !"".equals( text ) )
        {
            q.append( "where c.name='" ).append( text ).append( "'" );
        }
        q.append( " order by c.name desc" );
        return q.toString();
    }
}
