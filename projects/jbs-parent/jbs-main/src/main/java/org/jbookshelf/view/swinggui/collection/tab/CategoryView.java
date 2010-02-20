/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab;

import static org.jbookshelf.controller.singleton.Single.instance;
import icons.IMG;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
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
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.Named;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.model.db.util.HibernateConnector;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.actions.NamedIdentifieableActions;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.collection.tab.book.BookNode;
import org.jbookshelf.view.swinggui.dnd.TreeDragSource;
import org.jbookshelf.view.swinggui.dnd.TreeDropTarget;
import org.jbookshelf.view.swinggui.main.ProgressBar;
import org.jdesktop.swingx.JXTree;
import org.xnap.commons.gui.util.PopupListener;

/**
 * displays {@link Category}s in a tree
 * 
 * @author eav
 */
public class CategoryView
    extends CollectionView
{
    public static class CategoryNode
        extends DefaultLazyNode
    {
        private final Category category;

        public CategoryNode()
        {
            super();
            category = null;
        }

        private CategoryNode(
            @Nonnull final Category category )
        {
            super( category.equals( BookShelf.rootCategory() )
                ? I18N.tr( "All categories" ) : category.getName() );
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

    private final CategoryNode     root       = new CategoryNode( BookShelf.rootCategory() );
    private final DefaultTreeModel model      = new DefaultTreeModel( root );
    private final JXTree           tree       = new JXTree( model );
    @SuppressWarnings( "unused" )
    private final TreeDragSource   dragSource = new TreeDragSource( tree );
    @SuppressWarnings( "unused" )
    private final TreeDropTarget   dropTarget = new TreeDropTarget( tree )
                                              {
                                                  @Override
                                                  protected void dropDataChange(
                                                      final DefaultMutableTreeNode parent,
                                                      final DefaultMutableTreeNode node,
                                                      final DefaultTreeModel model )
                                                  {
                                                      dataChange( parent, node );
                                                      if ( ((LazyNode) parent).isInitialized() )
                                                      {
                                                          super.dropDataChange( parent, node, model );
                                                      }
                                                  }
                                              };
    private static final Logger    log        = Logger.getLogger( CategoryView.class );

    public CategoryView()
    {
        super();
        setName( I18N.tr( "Categories" ) );
        setIcon( IMG.icon( IMG.FEED_SUBSCRIBE_PNG ) );

        setLayout( new BorderLayout() );

        add( new JScrollPane( tree ), BorderLayout.CENTER );

        tree.setOpenIcon( IMG.icon( IMG.FEED_SUBSCRIBE_PNG ) );
        tree.setClosedIcon( IMG.icon( IMG.FEED_SUBSCRIBE_PNG ) );
        tree.setLeafIcon( IMG.icon( IMG.BOOK_PNG ) );
        initListeners();
    }

    @Override
    @PostConstruct
    public void initMenu()
    {
        super.initMenu();
        final NamedIdentifieableActions actions = Single.instance( NamedIdentifieableActions.class );
        menu.add( actions.renameAction );
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
                final Session session = instance( HibernateConnector.class ).openSession();
                try
                {
                    final List<Category> list = session.createQuery( buildQuery( p ) ).list();

                    model.reload( root );
                    for ( final Category category : list )
                    {
                        final Category rootCategory = BookShelf.rootCategory();
                        if ( !rootCategory.equals( category ) && rootCategory.equals( category.getParent() ) )
                        {
                            publish( new CategoryNode( category ) );
                        }
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
            protected void doneSafe()
            {
                tree.expandRow( 0 );
                Single.instance( CollectionPanel.class ).setResultCount( getQuiet().size() - 1 );
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

    private void dataChange(
        final DefaultMutableTreeNode parent,
        final DefaultMutableTreeNode node )
    {
        final Category parentCategory = ((CategoryNode) parent).getCategory();
        if ( node instanceof CategoryNode )
        {
            final Category childCategory = ((CategoryNode) node).getCategory();
            BookShelf.setParent( parentCategory, childCategory );
        }
        else
        {
            final BookNode bookNode = (BookNode) node;
            final Book book = bookNode.getBook();

            final CategoryNode oldCategoryNode = (CategoryNode) node.getParent();
            final Category oldCategory = oldCategoryNode.getCategory();
            BookShelf.moveBook( book, oldCategory, parentCategory );
        }
    }

    private void initListeners()
    {
        tree.addMouseListener( new PopupListener( menu ) );

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
                final List<Named> list = new ArrayList<Named>();
                for ( final TreePath treePath : tree.getSelectionPaths() )
                {
                    final Object object = treePath.getLastPathComponent();

                    if ( object instanceof CategoryNode )
                    {
                        final Category category = ((CategoryNode) object).getCategory();
                        if ( !BookShelf.rootCategory().equals( category ) )
                        {
                            list.add( category );
                        }
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
        if ( !categoryNode.isInitialized() )
        { // not loaded yet
            final Category category = categoryNode.getCategory();

            final Set<Book> books = BookShelf.getBooks( category );
            for ( final Book book : books )
            {
                model.insertNodeInto( new BookNode( book ), categoryNode, 0 );
            }

            final Set<Category> children = BookShelf.getChildren( category );
            for ( final Category child : children )
            {
                model.insertNodeInto( new CategoryNode( child ), categoryNode, 0 );
            }
            categoryNode.setInitialized( true );
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
