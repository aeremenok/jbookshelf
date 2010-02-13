/**
 * 
 */
package test.model.db;

import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.dao.alter.BookDAO;
import org.jbookshelf.model.db.dao.alter.DAO;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.BasicTest;

/**
 * @author eav 2010
 */
public class BookDAOTest
    extends BasicTest
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAOTest.class );

    private final DAO<Book>     dao = new BookDAO();
    private Book                book1;

    @Test( dependsOnMethods = "listCreatedBook" )
    public void changeBook1Name()
    {
        final Book toRename = dao.getById( book1.getId() );
        Assert.assertEquals( toRename, book1 );

        toRename.setName( "book01" );
        dao.update( toRename );

        final Book expectedToChange = dao.getById( book1.getId() );
        Assert.assertEquals( expectedToChange, toRename );
    }

    @Test( dependsOnMethods = "firstListBooks" )
    public void createBook1()
    {
        final Book persistent = dao.persist( book1 );

        assert persistent.getId() != null;
        Assert.assertEquals( persistent, book1 );
    }

    @Test( dependsOnMethods = "changeBook1Name" )
    public void deleteBook1()
    {
        dao.delete( book1 );
        assert dao.getById( book1.getId() ) == null;
        assert dao.findAll().isEmpty();
    }

    @Test
    public void firstListBooks()
    {
        final List<Book> find = dao.findAll();
        assert find.isEmpty();
    }

    @Test( dependsOnMethods = "createBook1" )
    public void listCreatedBook()
    {
        final List<Book> find = dao.findAll();
        assert find.size() == 1;

        final Book foundBook = find.get( 0 );
        Assert.assertEquals( foundBook, book1 );
        Assert.assertEquals( foundBook.getPhysicalBook(), book1.getPhysicalBook() );
    }

    @Override
    @BeforeTest
    public void setUp()
    {
        super.setUp();

        book1 = new Book();
        book1.setName( "book1" );

        final PhysicalBook physicalBook1 = new PhysicalBook();
        physicalBook1.setFileName( "filename1" );

        book1.setPhysicalBook( physicalBook1 );
        book1.setRead( false );
    }
}
