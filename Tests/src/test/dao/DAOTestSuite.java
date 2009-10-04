/**
 * 
 */
package test.dao;

import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.util.DBUtil;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author eav 2009
 */
@RunWith( Suite.class )
@SuiteClasses(
{ BookDAOTests.class } )
public class DAOTestSuite
{
    @BeforeClass
    public static void setUp()
    {
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
        Single.instance( DBUtil.class ).startup();
    }

    @AfterClass
    public static void tearDown()
    {
        Single.instance( DBUtil.class ).shutdown();
    }
}
