/**
 * 
 */
package test.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.dao.BookDAO;

/**
 * @author eav 2009
 */
public class BookDAOTests
    extends CommonDAOTests<Book, BookDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAOTests.class );

    public BookDAOTests()
    {
        super( new BookDAO() );
    }

    @Override
    public void _makePersistent()
    {
        super._makePersistent();
    }

    @Override
    public void findAll()
    {
        super.findAll();
    }

    @Override
    public void getById()
    {
        final List<Book> all = dao.findAll();
        final Book u = all.get( 0 );
        final Book byId = dao.getById( u.getId() );
        assertNotNull( byId );
        assertEquals( u, byId );

        assertNotNull( byId.getPhysicalBook() );
    }

    @Override
    public void makeTransient()
    {
        final List<Book> all = dao.findAll();
        final Book u = all.get( 0 );

        final Long id = u.getId();

        dao.makeTransient( u );
        assertNull( u.getId() );
        assertEquals( u.getAuthors().size(), 0 );
        assertEquals( u.getNotes().size(), 0 );
        assertEquals( u.getCategories().size(), 0 );
        assertEquals( u.getRelatedBooks().size(), 0 );

        assertNull( u.getLastRead() );
        assertNull( u.getPhysicalBook() );

        final Book byId = dao.getById( id );
        assertNull( byId );

    }

    @Override
    public Book randomUnique()
    {
        final Book book = new Book();
        book.setName( "Book" + System.currentTimeMillis() );
        return book;
    }

}
