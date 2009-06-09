/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.LogRunner;
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

        public BookTableModel()
        {
            super( names, 0 );
        }

        public List<Book> getBooks(
            final int[] rowNumbers )
        {
            final List<Book> list = new ArrayList<Book>();
            for ( final int i : rowNumbers )
            {
                final Object id = rows.get( i )[3];
                log.debug( id );
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
        final LogRunner runner = new LogRunner();
        try
        {
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

                books.set( i, new Object[]
                { name, aname, cname, id } );
            }
            table.clearSelection();
            model.setRows( books );
        }
        catch ( final SQLException e1 )
        {
            log.error( e1, e1 );
            throw new Error( e1 );
        }
    }

    @Override
    public void valueChanged(
        final ListSelectionEvent e )
    {
        if ( !e.getValueIsAdjusting() )
        {
            final int[] selectedRows = table.getSelectedRows();
            final List<Book> selectedBooks = model.getBooks( selectedRows );
            Single.instance( BookShelfMediator.class ).booksSelected( selectedBooks );
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
