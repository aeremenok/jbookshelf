/**
 * 
 */
package test.env;

import static org.jbookshelf.controller.singleton.Single.instance;

import java.io.File;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.JBSSystem;
import org.jbookshelf.model.db.util.HibernateConnector;

/**
 * @author eav 2010
 */
public class TestDBEnvironment
    extends TestEnvironment
{
    private static final Logger log = Logger.getLogger( TestDBEnvironment.class );
    protected File              testDbDir;

    @Override
    public void setUp()
    {
        Single.setImplementation( JBSSystem.class, FakeJBSSystem.class );
        instance( HibernateConnector.class ).start();
        log.debug( "env set up" );
    }

    @Override
    public void tearDown()
    {
        instance( HibernateConnector.class ).stop();
    }
}
