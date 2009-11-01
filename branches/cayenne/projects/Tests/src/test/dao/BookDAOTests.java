/**
 * 
 */
package test.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.model.db.api.spec.dao.IBookDAO;

/**
 * @author eav 2009
 */
public class BookDAOTests
    extends UniqueDAOTests<IBook, IBookDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAOTests.class );

    public BookDAOTests()
    {
        super( null );
    }

    @Override
    public void getById()
    {
        final List<IBook> all = dao.findAll();
        final IBook expected = all.get( 0 );
        final IBook book = dao.getById( expected.getId() );
        assertBook( expected, book );
    }

    @Override
    public void getByName()
    {
        final List<IBook> all = dao.findAll();
        final IBook expected = all.get( 0 );
        final IBook book = dao.getByName( expected.getName() );
        assertBook( expected, book );
    }

    @Override
    public void makePersistent()
    {
        final IBook random = randomIdentifiable();
        assertNull( random.getId() );

        final AuthorDAOTests authorDAOTests = new AuthorDAOTests();
        final CategoryDAOTests categoryDAOTests = new CategoryDAOTests();

        final IAuthor a = authorDAOTests.randomIdentifiable();
        random.getAuthors().add( a );

        final ICategory c = categoryDAOTests.randomIdentifiable();
        random.getCategories().add( c );

        final IBook persistent = dao.makePersistent( random );
        assertNotNull( persistent );
        assertNotNull( persistent.getId() );
        assertEquals( random, persistent );

        final IBook byId = dao.getById( persistent.getId() );

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
        final List<IBook> all = dao.findAll();
        final IBook u = all.get( 0 );

        final Serializable id = u.getId();

        dao.makeTransient( u );
        assertNull( u.getId() );
        assertEquals( u.getAuthors().size(), 0 );
        assertEquals( u.getNotes().size(), 0 );
        assertEquals( u.getCategories().size(), 0 );
        assertEquals( u.getRelatedBooks().size(), 0 );

        assertNull( u.getLastRead() );
        assertNull( u.getPhysicalBook() );

        final IBook byId = dao.getById( id );
        assertNull( byId );
    }

    @Override
    public IBook randomIdentifiable()
    {
        final IBook book = super.randomIdentifiable();
        final IPhysicalBook physicalBook = BookShelf.createPhysicalBook();
        physicalBook.setFile( new File( System.getProperty( "user.home" ) ) );
        book.setPhysicalBook( physicalBook );
        return book;
    }

    protected void assertBook(
        final IBook expected,
        final IBook book )
    {
        assertNotNull( book );
        assertEquals( expected, book );

        final IPhysicalBook physicalBook = book.getPhysicalBook();
        assertNotNull( physicalBook );
        assertNotNull( physicalBook.getFileName() );
        assertNotNull( physicalBook.getFile() );
    }
}
