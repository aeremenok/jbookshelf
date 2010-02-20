/**
 * 
 */
package org.jbookshelf;

import static org.jbookshelf.controller.singleton.Single.instance;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
        testDbDir = new File( instance( JBSSystem.class ).tempDir() + "/testdb" );
        testDbDir.mkdir();
        instance( HibernateConnector.class ).start( testDbDir );
        log.debug( "env set up" );
    }

    @Override
    public void tearDown()
    {
        instance( HibernateConnector.class ).stop();
        FileUtils.deleteQuietly( testDbDir );
    }
}
