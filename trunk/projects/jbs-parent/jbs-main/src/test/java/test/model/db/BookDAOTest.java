/**
 * 
 */
package test.model.db;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.dao.alter.BookDAO;
import org.jbookshelf.model.db.util.HibernateUtil;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author eav 2010
 */
public class BookDAOTest
{
    static
    {
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
    }
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAOTest.class );

    final BookDAO               dao = new BookDAO();
    Book                        book1;

    File                        testDbDir;

    @Test( dependsOnMethods =
    { "listCreatedBook" } )
    public void changeBook1Name()
    {
        final Book toRename = dao.getById( book1.getId() );
        Assert.assertEquals( toRename, book1 );

        toRename.setName( "book01" );
        dao.update( toRename );

        HibernateUtil.close();
        HibernateUtil.open( testDbDir );

        final Book expectedToChange = dao.getById( book1.getId() );
        Assert.assertEquals( expectedToChange, toRename );
    }

    @Test( dependsOnMethods =
    { "firstListBooks" } )
    public void createBook1()
    {
        book1 = new Book();
        book1.setName( "book1" );
        final Book persistent = dao.persist( book1 );

        assert persistent.getId() != null;
        Assert.assertEquals( persistent, book1 );
    }

    @Test( dependsOnMethods =
    { "changeBook1Name" } )
    public void deleteBook1()
    {
        dao.delete( book1.getId() );
        assert dao.getById( book1.getId() ) == null;
        assert dao.find().isEmpty();
    }

    @Test
    public void firstListBooks()
    {
        final List<Book> find = dao.find();
        assert find.isEmpty();
    }

    @Test( dependsOnMethods =
    { "createBook1" } )
    public void listCreatedBook()
    {
        final List<Book> find = dao.find();
        assert find.size() == 1;
        Assert.assertEquals( find.get( 0 ), book1 );
    }

    @BeforeTest
    public void setUp()
    {
        testDbDir = new File( System.getProperty( "java.io.tmpdir" ) + "/testdb" );
        FileUtils.deleteQuietly( testDbDir );
        testDbDir.mkdir();
        HibernateUtil.open( testDbDir );
    }

    @AfterTest
    public void tearDown()
    {
        HibernateUtil.close();
    }
}
