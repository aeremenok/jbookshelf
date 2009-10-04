/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Unique;
import org.jbookshelf.model.db.util.LogRunner;

/**
 * @author eav 2009
 * @param <T>
 */
public abstract class BookShelfDAO<T extends Unique>
    implements
    GenericDAO<T, Long>
{
    private static final Logger log    = Logger.getLogger( BookShelfDAO.class );

    protected final LogRunner   runner = new LogRunner();

    protected final Class<T>    entityClass;

    @SuppressWarnings( "unchecked" )
    public BookShelfDAO()
    {
        super();
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public void clear()
    {
        try
        {
            runner.update( "delete from " + entityClass.getSimpleName() );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<T> findAll()
    {
        try
        {
            final BeanListHandler handler = new BeanListHandler( entityClass );
            final String sql = "select * from " + entityClass.getSimpleName();
            return (List<T>) runner.query( sql, handler );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public void flush()
    {
    // todo
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public T getById(
        final Long id )
    {
        try
        {
            final BeanHandler handler = new BeanHandler( entityClass );
            final String sql = "select * from " + entityClass.getSimpleName() + " where id=?";
            return (T) runner.query( sql, handler, new Object[]
            { id } );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public void makeTransient(
        final T entity )
    {
        try
        {
            final String sql = "delete from " + entityClass.getSimpleName() + " where id=?";
            runner.update( sql, new Object[]
            { entity.getId() } );
            entity.setId( null );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

}
