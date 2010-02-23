/**
 * 
 */
package org.jbookshelf.controller.importer;

import static java.util.Arrays.asList;
import static org.jbookshelf.controller.importer.CompareUtil.authors;
import static org.jbookshelf.controller.importer.CompareUtil.book;
import static org.jbookshelf.controller.importer.CompareUtil.file;
import static org.jbookshelf.controller.importer.CompareUtil.isEqual;
import static org.jbookshelf.controller.importer.CompareUtil.set;
import static org.jbookshelf.controller.singleton.Single.instance;
import static org.testng.Assert.assertFalse;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.importer.CompareUtil.BookComparator;
import org.jbookshelf.controller.importer.strategy.UseMasksStrategy;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.env.TestEnvironment;

/**
 * @author eav 2010
 */
public class ImporterTest
{
    public class TestImporter
        extends FileImporter
    {
        List<File>      failureFiles  = new ArrayList<File>();
        List<Exception> failureCauses = new ArrayList<Exception>();
        List<Book>      importedBooks = new ArrayList<Book>();

        @Override
        protected void onImportFailure(
            final File file,
            final Exception cause )
        {
            failureFiles.add( file );
            failureCauses.add( cause );
            log.error( cause, cause );
        }

        @Override
        protected void onImportSuccess(
            final Book book )
        {
            importedBooks.add( book );
            log.debug( "book imported " + book );
        }
    }

    private static final Logger log = Logger.getLogger( ImporterTest.class );

    @DataProvider
    public Iterator<Object[]> forSuccess()
        throws URISyntaxException
    {
        final List<Object[]> list = new ArrayList<Object[]>();
        list.add( new Object[]
        { file( "./test-files/1" ), "%b", set( book( "Свифт Дж. Путешествия Гулливера" ) ) } );
        list.add( new Object[]
        { file( "./test-files/1" ), "%a. %b", set( book( "Путешествия Гулливера", authors( "Свифт Дж" ) ) ) } );
        return list.iterator();
    }

    @BeforeClass
    public void setUp()
    {
        instance( TestEnvironment.class ).setUp();
    }

    @AfterClass
    public void tearDown()
    {
        instance( TestEnvironment.class ).tearDown();
    }

    @Test( dataProvider = "forSuccess" )
    public void testImportSuccess(
        final File root,
        final String mask,
        final Set<Book> expectedBooks )
    {
        final TestImporter importer = new TestImporter();
        final Parameters p = new Parameters();

        p.put( Keys.IMPORT_ROOTS, new File[]
        { root } );

        final UseMasksStrategy strategy = new UseMasksStrategy();
        strategy.setMasks( asList( mask ) );
        p.put( Keys.IMPORT_STRATEGY, strategy );

        importer.importFiles( p );

        assert importer.failureFiles.isEmpty();
        assert importer.failureCauses.isEmpty();

        final List<Book> importedBooks = importer.importedBooks;
        assertFalse( importedBooks.isEmpty() );
        assert isEqual( importedBooks, expectedBooks, new BookComparator() );
    }
}