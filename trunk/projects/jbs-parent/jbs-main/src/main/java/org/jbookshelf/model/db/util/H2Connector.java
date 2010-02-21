/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.exception.JDBCConnectionException;

/**
 * @author eav 2010
 */
public class H2Connector
    implements
    ConnectionProvider
{
    private static final Logger  log = Logger.getLogger( H2Connector.class );

    protected JdbcConnectionPool connectionPool;

    @Override
    public void close()
        throws HibernateException
    {
        try
        {
            if ( connectionPool != null )
            {
                connectionPool.dispose();
                connectionPool = null;
            }
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new JDBCConnectionException( e.getMessage(), e );
        }
    }

    @Override
    public void closeConnection(
        final Connection conn )
        throws SQLException
    {
        DbUtils.close( conn );
    }

    @Override
    public void configure(
        final Properties props )
        throws HibernateException
    {
        final String url = props.getProperty( Environment.URL );
        final String user = props.getProperty( Environment.USER );
        final String pass = props.getProperty( Environment.PASS );
        connectionPool = JdbcConnectionPool.create( url, user, pass );
        connectionPool.setMaxConnections( 5 );
    }

    @Override
    public Connection getConnection()
        throws SQLException
    {
        return connectionPool.getConnection();
    }

    @Override
    public boolean supportsAggressiveRelease()
    {
        return false;
    }
}
