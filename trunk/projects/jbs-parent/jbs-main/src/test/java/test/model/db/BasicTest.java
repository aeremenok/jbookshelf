/**
 * 
 */
package test.model.db;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.model.db.util.HibernateUtil;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author eav 2010
 */
public class BasicTest
{
    private static final Logger log;
    static
    {
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
        log = Logger.getLogger( BasicTest.class );
    }

    protected File              testDbDir;

    @BeforeTest
    public void setUp()
    {
        testDbDir = new File( System.getProperty( "java.io.tmpdir" ) + "/testdb" );
        FileUtils.deleteQuietly( testDbDir );
        testDbDir.mkdir();
        HibernateUtil.open( testDbDir );
        log.debug( "test set up" );
    }

    @AfterTest
    public void tearDown()
    {
        HibernateUtil.close();
    }
}
