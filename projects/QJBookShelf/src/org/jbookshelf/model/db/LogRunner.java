/**
 * 
 */
package org.jbookshelf.model.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

/**
 * @author eav 2009
 */
public class LogRunner
    extends QueryRunner
{
    private static final Logger log = Logger.getLogger( LogRunner.class );

    private static String toString(
        final Object[] params )
    {
        if ( params == null )
        {
            return "";
        }

        final StringBuilder builder = new StringBuilder();
        for ( int i = 0; i < params.length; i++ )
        {
            builder.append( i + 1 ).append( " > " ).append( params[i] ).append( "\n" );
        }
        return builder.toString();
    }

    private final boolean showSql;

    public LogRunner()
    {
        super();
        showSql = "true".equalsIgnoreCase( HibernateUtil.getProperties().getProperty( "show_sql" ) );
    }

    @Override
    public Object query(
        final Connection conn,
        final String sql,
        final ResultSetHandler rh,
        final Object[] params )
        throws SQLException
    {
        log( sql, params );
        return super.query( conn, sql, rh, params );
    }

    @Override
    public int update(
        final Connection conn,
        final String sql,
        final Object[] params )
        throws SQLException
    {
        log( sql, params );
        return super.update( conn, sql, params );
    }

    private void log(
        final String sql,
        final Object[] params )
    {
        if ( showSql )
        {
            log.debug( sql + "\n" + toString( params ) );
        }
    }

    /* (non-Javadoc)
     * @see org.apache.commons.dbutils.QueryRunner#prepareConnection()
     */
    @Override
    protected Connection prepareConnection()
        throws SQLException
    {
        final Connection connection = HibernateUtil.connection();
        connection.setAutoCommit( true );
        return connection;
    }

}
