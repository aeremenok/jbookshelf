/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab.book;

import icons.IMG;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.util.LogRunner;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.collection.tab.CollectionView;
import org.jbookshelf.view.swinggui.widget.tables.ExpandScrollPane;
import org.jbookshelf.view.swinggui.widget.tables.ExpandTableModel;
import org.jbookshelf.view.swinggui.widget.tables.RecordFactory;
import org.jdesktop.swingx.JXTable;
import org.xnap.commons.gui.util.PopupListener;

/**
 * displays books in a table
 * 
 * @author eav
 */
public class BookView
    extends CollectionView
    implements
    ListSelectionListener,
    RecordFactory<BookRecord>
{
    public static final int                    PAGE_SIZE = 70;

    private final ExpandTableModel<BookRecord> model     = new ExpandTableModel<BookRecord>( this, PAGE_SIZE );
    private final JXTable                      table     = new JXTable( model );

    private final LogRunner                    runner    = new LogRunner();

    private List<Object[]>                     books;

    public BookView()
    {
        super();
        setName( I18N.tr( "Books" ) );
        setIcon( IMG.icon( IMG.BOOK_PNG ) );
        setLayout( new BorderLayout() );
        add( new ExpandScrollPane( table, model ), BorderLayout.CENTER );

        table.getSelectionModel().addListSelectionListener( this );
        table.addMouseListener( new PopupListener( menu ) );
    }

    @Override
    public BookRecord createRecord(
        final int i )
    {
        final StringBuilder q1 = new StringBuilder( "select a.name from author a " );
        q1.append( "left join author_book ab on a.id=ab.authors_id " );
        q1.append( "where ab.books_id=? and rownum<2" );

        final StringBuilder q2 = new StringBuilder( "select c.name from category c " );
        q2.append( "left join category_book cb on c.id=cb.categories_id " );
        q2.append( "where cb.books_id=? and rownum<2" );

        final Object name = books.get( i )[0];
        final Object id = books.get( i )[1];

        final String aname = (String) runner.query( q1.toString(), new ScalarHandler(), new Object[]
        { id } );
        final String cname = (String) runner.query( q2.toString(), new ScalarHandler(), new Object[]
        { id } );

        return new BookRecord( id, name, aname, cname );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widget.tables.RecordFactory#getColumnNames()
     */
    @Override
    public String[] getColumnNames()
    {
        return new String[]
        { I18N.tr( "Name" ), I18N.tr( "Author" ), I18N.tr( "Category" ) };
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widget.tables.RecordFactory#recordCount()
     */
    @Override
    public int recordCount()
    {
        return books != null
            ? books.size() : 0;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void search(
        final Parameters p )
    {
        table.clearSelection();
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Object[]>, Object[]>()
        {
            @Override
            protected List<Object[]> doInBackground()
            {
                books = (List<Object[]>) runner.query( buildQuery( p ), new ArrayListHandler() );
                model.reset();
                return books;
            }

            @Override
            protected void doneSafe()
            {
                table.packAll();
                Single.instance( CollectionPanel.class ).setResultCount( getQuiet().size() );
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
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<IBook>, Object>()
            {
                @Override
                protected List<IBook> doInBackground()
                {
                    // todo optimize and catch ctrl+a
                    if ( selectedRows.length == BookShelf.bookCount() )
                    { // selected all
                        return BookShelf.allBooks();
                    }

                    // selected some rows
                    for ( int i = 0; i < selectedRows.length; i++ )
                    { // convert in case the table was sorted
                        final int row = selectedRows[i];
                        selectedRows[i] = table.convertRowIndexToModel( row );
                    }
                    return getBooks( selectedRows );
                }

                @Override
                protected void doneSafe()
                {
                    Single.instance( BookShelfMediator.class ).booksSelected( getQuiet() );
                }
            } );
        }
    }

    private List<IBook> getBooks(
        final int[] selectedRows )
    {
        final List<IBook> list = new ArrayList<IBook>();
        for ( final int i : selectedRows )
        {
            final Object id = books.get( i )[1];
            list.add( BookShelf.bookById( id ) );
        }
        return list;
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
