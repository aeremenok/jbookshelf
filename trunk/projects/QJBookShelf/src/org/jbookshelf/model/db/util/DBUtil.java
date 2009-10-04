/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;

/**
 * @author eav 2009
 */
public class DBUtil
{
    private static final Logger        log = Logger.getLogger( DBUtil.class );

    protected Boolean                  showSql;

    protected final JdbcConnectionPool pool;

    public DBUtil()
    {
        super();
        final String dir = Single.instance( Settings.class ).JBS_DIR.getValue();
        final String url = "jdbc:h2:nio:" + dir
            + "/db/jbs;TRACE_LEVEL_FILE=1;TRACE_LEVEL_SYSTEM_OUT=0;TRACE_MAX_FILE_SIZE=1;AUTO_SERVER=TRUE";
        pool = JdbcConnectionPool.create( url, "sa", "" );
        pool.setMaxConnections( 5 );

        log.debug( "db started" );
    }

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
        if ( showSql == null )
        {
            showSql = Level.DEBUG.equals( log.getLevel() );
        }
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

    public void startup()
    {}
}
