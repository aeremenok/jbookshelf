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
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
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
import org.jbookshelf.view.swinggui.main.ProgressBar;
import org.jdesktop.swingx.JXTree;
import org.xnap.commons.gui.util.PopupListener;

/**
 * displays {@link Author}s in a tree
 * 
 * @author eav
 */
public class AuthorView
    extends CollectionView
{
    private class AuthorNode
        extends DefaultLazyNode
    {
        private final Author author;

        private AuthorNode(
            final Author author )
        {
            super( author.getName() );
            this.author = author;
        }

        public Author getAuthor()
        {
            return this.author;
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

    private static final Logger          log   = Logger.getLogger( AuthorView.class );

    public AuthorView()
    {
        super();
        setName( I18N.tr( "Authors" ) );
        setIcon( IMG.icon( IMG.USER_IDENITY_PNG ) );
        setLayout( new BorderLayout() );

        add( new JScrollPane( tree ), BorderLayout.CENTER );

        tree.setRootVisible( false );
        tree.setOpenIcon( IMG.icon( IMG.USER_IDENITY_PNG ) );
        tree.setClosedIcon( IMG.icon( IMG.USER_IDENITY_PNG ) );
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

    @SuppressWarnings( "unchecked" )
    @Override
    public void search(
        final Parameters p )
    {
        root.removeAllChildren();
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Author>, AuthorNode>()
        {
            @Override
            protected List<Author> doInBackground()
            {
                final Session session = instance( HibernateConnector.class ).openSession();
                try
                {
                    final List<Author> list = session.createQuery( buildQuery( p ) ).list();

                    model.reload( root );
                    for ( final Author author : list )
                    {
                        publish( new AuthorNode( author ) );
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
                Single.instance( CollectionPanel.class ).setResultCount( getQuiet().size() );
            }

            @Override
            protected void process(
                final List<AuthorNode> chunks )
            {
                for ( final AuthorNode authorNode : chunks )
                {
                    model.insertNodeInto( authorNode, root, 0 );
                }
            }
        } );
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
                if ( node instanceof AuthorNode )
                { // lazy load author's books
                    loadChildren( (AuthorNode) node );
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

                    if ( object instanceof AuthorNode )
                    {
                        list.add( ((AuthorNode) object).getAuthor() );
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
     * lazyly load node's chilren
     * 
     * @param authorNode node to fill
     */
    private void loadChildren(
        final AuthorNode authorNode )
    {
        if ( !authorNode.isInitialized() )
        { // not loaded yet
            final Author author = authorNode.getAuthor();

            final Set<Book> books = BookShelf.getBooks( author );
            for ( final Book book : books )
            {
                model.insertNodeInto( new BookNode( book ), authorNode, 0 );
            }
            authorNode.setInitialized( true );
        }
    }

    @Override
    protected String buildQuery(
        final Parameters p )
    {
        final String text = p.get( Keys.SEARCH_TEXT );
        final StringBuilder q = new StringBuilder( "from Author a " );
        if ( text != null && !"".equals( text ) )
        {
            q.append( "where a.name='" ).append( text ).append( "'" );
        }
        q.append( " order by a.name desc" );
        return q.toString();
    }
}
