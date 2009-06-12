/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import images.IMG;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.LogRunner;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.widgets.ProgressBar;
import org.jbookshelf.view.swinggui.widgets.SafeWorker;
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

        public BookTableModel()
        {
            super( names, 0 );
        }

        @Override
        public void addRow(
            final Object[] rowData )
        {
            rows.add( rowData );
            final int row = rows.size();
            fireTableRowsInserted( row, row );
        }

        public List<Book> getBooks(
            final int[] rowNumbers )
        {
            final List<Book> list = new ArrayList<Book>();
            for ( final int i : rowNumbers )
            {
                final Object id = rows.get( i )[3];
                list.add( BookShelf.bookById( id ) );
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
         * @param rows the rows to set
         */
        public void setRows(
            final List<Object[]> rows )
        {
            this.rows = rows;
            fireTableDataChanged();
        }
    }

    private final BookTableModel model = new BookTableModel();
    private final JXTable        table = new JXTable( model );

    public BookView()
    {
        super();
        setName( "Books" );
        setIcon( IMG.icon( IMG.BOOK_PNG ) );
        setLayout( new BorderLayout() );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.getSelectionModel().addListSelectionListener( this );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void search(
        final Parameters p )
    {
        table.clearSelection();
        model.setRows( new ArrayList<Object[]>() );
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Object[]>, Object[]>()
        {
            @Override
            protected List<Object[]> doInBackground()
                throws Exception
            {
                final LogRunner runner = new LogRunner();
                final List<Object[]> books = (List<Object[]>) runner.query( buildQuery( p ), new ArrayListHandler() );
                final StringBuilder q1 = new StringBuilder( "select a.name from author a " );
                q1.append( "left join author_book ab on a.id=ab.authors_id " );
                q1.append( "where ab.books_id=? and rownum<2" );

                final StringBuilder q2 = new StringBuilder( "select c.name from category c " );
                q2.append( "left join category_book cb on c.id=cb.categories_id " );
                q2.append( "where cb.books_id=? and rownum<2" );
                for ( int i = 0; i < books.size(); i++ )
                {
                    final Object name = books.get( i )[0];
                    final Object id = books.get( i )[1];

                    final String aname = (String) runner.query( q1.toString(), new ScalarHandler(), new Object[]
                    { id } );
                    final String cname = (String) runner.query( q2.toString(), new ScalarHandler(), new Object[]
                    { id } );

                    final Object[] book = new Object[]
                    { name, aname, cname, id };
                    publish( book );
                    books.set( i, book );
                }
                return books;
            }

            @Override
            protected void process(
                final List<Object[]> chunks )
            {
                for ( final Object[] objects : chunks )
                {
                    model.addRow( objects );
                }
            }
        } );
    }

    @Override
    public void valueChanged(
        final ListSelectionEvent e )
    {
        if ( !e.getValueIsAdjusting() )
        {
            final int[] selectedRows = table.getSelectedRows();
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Book>, Object>()
            {
                @Override
                protected List<Book> doInBackground()
                {
                    if ( selectedRows.length == model.getRowCount() )
                    { // selected all
                        return BookShelf.allBooks();
                    }

                    // selected some rows
                    for ( int i = 0; i < selectedRows.length; i++ )
                    { // convert in case the table was sorted
                        final int row = selectedRows[i];
                        selectedRows[i] = table.convertRowIndexToModel( row );
                    }
                    return model.getBooks( selectedRows );
                }

                @Override
                protected void doneSafe()
                {
                    Single.instance( BookShelfMediator.class ).booksSelected( getQuiet() );
                }
            } );
        }
    }

    @Override
    protected String buildQuery(
        final Parameters p )
    {
        final StringBuilder q = new StringBuilder( "select b.name, b.id " );
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