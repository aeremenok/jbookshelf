/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.Identifiable;
import org.jbookshelf.model.db.util.LogRunner;
import org.jbookshelf.model.db.util.TBeanListHandler;

/**
 * @author eav 2009
 * @param <T>
 */
public abstract class AbstractGenericDAO<T extends Identifiable>
    implements
    GenericDAO<T, Serializable>
{
    @SuppressWarnings( "unused" )
    private static final Logger log    = Logger.getLogger( AbstractGenericDAO.class );

    protected final LogRunner   runner = new LogRunner();
    protected final Class<T>    entityClass;

    @SuppressWarnings( "unchecked" )
    public AbstractGenericDAO()
    {
        super();
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll()
    {
        final TBeanListHandler<T> handler = new TBeanListHandler<T>( entityClass );
        final String sql = "select * from " + entityClass.getSimpleName();
        return runner.query( sql, handler, new Object[] {} );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public T getById(
        final Serializable id )
    {
        final BeanHandler handler = new BeanHandler( entityClass );
        final String sql = "select * from " + entityClass.getSimpleName() + " where id=?";
        return (T) runner.query( sql, handler, new Object[]
        { id } );
    }

    public Class<T> getEntityClass()
    {
        return this.entityClass;
    }

    @Override
    public void makeTransient(
        final T entity )
    {
        checkIfTransient( entity );

        final String sql = "delete from " + entityClass.getSimpleName() + " where id=?";
        runner.update( sql, new Object[]
        { entity.getId() } );
        entity.setId( null );
    }

    protected boolean checkIfPersistent(
        final T t )
    {
        if ( t.getId() != null )
        {
            return true;
            //            throw new IllegalStateException( "already persistent book: " + t.getId() + "/" + t.getName() );
        }
        return false;
    }

    protected boolean checkIfTransient(
        final T entity )
    {
        if ( entity.getId() == null )
        {
            throw new IllegalStateException( "already transient entity: " + entity );
        }
        return false;
    }

    protected Long generateId()
    {
        final Object res = runner.query( "select hibernate_sequence.nextval", new ScalarHandler() );
        return Long.valueOf( res.toString() );
    }
}
