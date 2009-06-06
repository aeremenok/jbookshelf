/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.swing.JScrollPane;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.HibernateUtil;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jdesktop.swingx.JXTree;

/**
 * displays authors in a tree
 * 
 * @author eav
 */
public class AuthorView
    extends CollectionView
{
    private class AuthorNode
        extends DefaultMutableTreeNode
    {
        private final Author author;

        private AuthorNode(
            @Nonnull final Author author )
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
        setName( "Authors" );
        setLayout( new BorderLayout() );

        add( new JScrollPane( tree ), BorderLayout.CENTER );

        tree.setRootVisible( false );
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
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void search(
        final Parameters p )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final List<Author> list = session.createQuery( buildQuery( p ) ).list();

            root.removeAllChildren();
            model.reload( root );
            for ( final Author author : list )
            {
                model.insertNodeInto( new AuthorNode( author ), root, 0 );
            }
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

    /**
     * lazyly load node's chilren
     * 
     * @param authorNode node to fill
     */
    private void loadChildren(
        final AuthorNode authorNode )
    {
        if ( authorNode.getChildCount() == 0 )
        { // not loaded yet
            final Author author = authorNode.getAuthor();

            final Set<Book> books = BookShelf.getBooks( author );
            for ( final Book book : books )
            {
                model.insertNodeInto( new BookNode( book ), authorNode, 0 );
            }
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
