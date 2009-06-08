/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.HibernateUtil;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jdesktop.swingx.JXTable;

/**
 * displays books in a table
 * 
 * @author eav
 */
public class BookView
    extends CollectionView
    implements
    ListSelectionListener
{
    private static class BookTableModel
        extends DefaultTableModel
    {
        private static String[] names =
                                      { I18N.tr( "Name" ), I18N.tr( "Author" ), I18N.tr( "Category" ) };

        private List<Object[]>  rows  = new ArrayList<Object[]>();

        private List<Book>      books = new ArrayList<Book>();

        public BookTableModel()
        {
            super( names, 0 );
        }

        /**
         * @return the books
         */
        public List<Book> getBooks()
        {
            return this.books;
        }

        public List<Book> getBooks(
            final int[] rowNumbers )
        {
            final List<Book> list = new ArrayList<Book>();
            for ( final int i : rowNumbers )
            {
                list.add( books.get( i ) );
            }
            return list;
        }

        @Override
        public int getRowCount()
        {
            return rows != null
                ? rows.size() : 0;
        }

        @Override
        public Object getValueAt(
            final int row,
            final int column )
        {
            return rows.get( row )[column];
        }

        @Override
        public boolean isCellEditable(
            final int row,
            final int column )
        {
            return false;
        }

        /**
         * @param books the books to set
         */
        public void setBooks(
            final List<Book> books )
        {
            this.books = books;
            // todo optimize
            final List<Object[]> list = new ArrayList<Object[]>();
            for ( final Book book : books )
            {
                final Iterator<Author> aIterator = book.getAuthors().iterator();
                final String firstAuthorName = aIterator.hasNext()
                    ? aIterator.next().getName() : "";

                final Iterator<Category> bIterator = book.getCategories().iterator();
                final String firstCategoryName = bIterator.hasNext()
                    ? bIterator.next().getName() : "";

                list.add( new Object[]
                { book.getName(), firstAuthorName, firstCategoryName } );
            }
            setRows( list );
        }

        /**
         * @param rows the rows to set
         */
        public void setRows(
            final List<Object[]> rows )
        {
            this.rows = rows;
            fireTableDataChanged();
        }
    }

    private static final Logger  log   = Logger.getLogger( BookView.class );

    private final BookTableModel model = new BookTableModel();
    private final JXTable        table = new JXTable( model );

    public BookView()
    {
        super();
        setName( "Books" );
        setLayout( new BorderLayout() );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.getSelectionModel().addListSelectionListener( this );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void search(
        final Parameters p )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final List<Book> books = session.createQuery( buildQuery( p ) ).list();
            table.clearSelection();
            model.setBooks( books );
        }
        catch ( final HibernateException e1 )
        {
            log.error( e1, e1 );
            throw new Error( e1 );
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public void valueChanged(
        final ListSelectionEvent e )
    {
        final int[] selectedRows = table.getSelectedRows();
        final List<Book> selectedBooks = model.getBooks( selectedRows );
        Single.instance( BookShelfMediator.class ).booksSelected( selectedBooks );
    }

    @Override
    protected String buildQuery(
        final Parameters p )
    {
        final StringBuilder q = new StringBuilder();
        q.append( "from Book b " );
        q.append( "where 1=1 " );

        final String text = p.get( Keys.SEARCH_TEXT );
        final Boolean isRead = p.get( Keys.SEARCH_IS_READ );
        // todo search content
        if ( text != null && !"".equals( text ) )
        {
            q.append( " and upper(b.name) like '%" ).append( text.toUpperCase() ).append( "%'" );
        }
        if ( Boolean.TRUE.equals( isRead ) )
        {
            q.append( " and b.read=1" );
        }
        else if ( Boolean.FALSE.equals( isRead ) )
        {
            q.append( " and b.read<1" );
        }

        return q.toString();
    }
}
