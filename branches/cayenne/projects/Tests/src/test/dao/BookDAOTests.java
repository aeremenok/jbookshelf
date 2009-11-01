/**
 * 
 */
package test.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.dao.BookDAO;

/**
 * @author eav 2009
 */
public class BookDAOTests
    extends UniqueDAOTests<Book, BookDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAOTests.class );

    public BookDAOTests()
    {
        super( new BookDAO() );
    }

    @Override
    public void getById()
    {
        final List<Book> all = dao.findAll();
        final Book expected = all.get( 0 );
        final Book book = dao.getById( expected.getId() );
        assertBook( expected, book );
    }

    @Override
    public void getByName()
    {
        final List<Book> all = dao.findAll();
        final Book expected = all.get( 0 );
        final Book book = dao.getByName( expected.getName() );
        assertBook( expected, book );
    }

    @Override
    public void makePersistent()
    {
        final Book random = randomIdentifiable();
        assertNull( random.getId() );

        final AuthorDAOTests authorDAOTests = new AuthorDAOTests();
        final CategoryDAOTests categoryDAOTests = new CategoryDAOTests();

        final Author a = authorDAOTests.randomIdentifiable();
        random.getAuthors().add( a );

        final Category c = categoryDAOTests.randomIdentifiable();
        random.getCategories().add( c );

        final Book persistent = dao.makePersistent( random );
        assertNotNull( persistent );
        assertNotNull( persistent.getId() );
        assertEquals( random, persistent );

        final Book byId = dao.getById( persistent.getId() );

        assertEquals( byId.getAuthors().size(), 0 );
        dao.getAuthors( byId );
        assertEquals( byId.getAuthors().size(), 1 );
        assertTrue( byId.getAuthors().contains( a ) );

        assertEquals( byId.getCategories().size(), 0 );
        dao.getCategories( byId );
        assertEquals( byId.getCategories().size(), 1 );
        assertTrue( byId.getCategories().contains( c ) );
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
    public Book randomIdentifiable()
    {
        final Book book = super.randomIdentifiable();
        final PhysicalBook physicalBook = new PhysicalBook();
        physicalBook.setFile( new File( System.getProperty( "user.home" ) ) );
        book.setPhysicalBook( physicalBook );
        return book;
    }

    protected void assertBook(
        final Book expected,
        final Book book )
    {
        assertNotNull( book );
        assertEquals( expected, book );

        final PhysicalBook physicalBook = book.getPhysicalBook();
        assertNotNull( physicalBook );
        assertNotNull( physicalBook.getFileName() );
        assertNotNull( physicalBook.getFile() );
    }
}
