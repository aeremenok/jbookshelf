/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 * @author eav 2009
 */
public class DBUtil
{
    private static final Logger  log     = Logger.getLogger( DBUtil.class );

    protected final boolean      showSql = Level.DEBUG.equals( log.getLevel() );

    protected JdbcConnectionPool pool;

    public Connection openConnection()
    {
        try
        {
            return pool.getConnection();
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    public boolean showSql()
    {
        return showSql;
    }

    public void shutdown()
    {
        try
        {
            pool.dispose();
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    public File startup(
        final String dirName )
    {
        final File dir = new File( dirName + "/db" );
        final boolean nodir = !dir.exists();
        if ( nodir )
        {
            dir.mkdir();
        }

        final String url = "jdbc:h2:nio:" + dirName
            + "/db/jbs;TRACE_LEVEL_FILE=1;TRACE_LEVEL_SYSTEM_OUT=0;TRACE_MAX_FILE_SIZE=1;AUTO_SERVER=TRUE";
        pool = JdbcConnectionPool.create( url, "sa", "" );
        pool.setMaxConnections( 5 );

        if ( nodir )
        {
            initDb( dir );
        }

        return dir;
    }

    private void initDb(
        final File dir )
    {
        log.debug( "creating db structure" );
        try
        {
            final URL sqlFileUrl = DBUtil.class.getResource( "jbs-ddl.sql" );
            final String sql = FileUtils.readFileToString( new File( sqlFileUrl.toURI() ) );
            final LogRunner runner = new LogRunner();
            runner.update( sql );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            dir.deleteOnExit();
            throw new Error( e );
        }
        log.debug( "db structure created" );
    }
}
