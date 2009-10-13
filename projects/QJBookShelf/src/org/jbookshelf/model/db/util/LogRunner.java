/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;

/**
 * performs native SQL queries
 * 
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

    @Override
    public Object query(
        final Connection conn,
        final String sql,
        final ResultSetHandler rh,
        final Object[] params )
    {
        try
        {
            log( sql, params );
            return super.query( conn, sql, rh, params );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public Object query(
        final String sql,
        final ResultSetHandler rsh )
    {
        try
        {
            return super.query( sql, rsh );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public Object query(
        final String sql,
        final ResultSetHandler rsh,
        final Object[] params )
    {
        try
        {
            return super.query( sql, rsh, params );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @SuppressWarnings( "unchecked" )
    public <T> List<T> query(
        final String sql,
        final TBeanListHandler<T> rsh,
        final Object[] params )
    {
        try
        {
            return (List<T>) super.query( sql, rsh, params );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int update(
        final Connection conn,
        final String sql,
        final Object[] params )
    {
        try
        {
            log( sql, params );
            final int update = super.update( conn, sql, params );
            return update;
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int update(
        final String sql )
    {
        try
        {
            return super.update( sql );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int update(
        final String sql,
        final Object[] params )
    {
        try
        {
            return super.update( sql, params );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    private void log(
        final String sql,
        final Object[] params )
    {
        if ( Single.instance( DBUtil.class ).showSql() )
        {
            log.debug( sql + "\n" + toString( params ) );
        }
    }

    @Override
    protected Connection prepareConnection()
        throws SQLException
    {
        final Connection connection = Single.instance( DBUtil.class ).openConnection();
        connection.setAutoCommit( true );
        return connection;
    }

}
