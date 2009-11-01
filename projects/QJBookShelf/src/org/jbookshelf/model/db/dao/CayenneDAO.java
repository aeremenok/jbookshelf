/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.query.ObjectIdQuery;
import org.apache.cayenne.query.SelectQuery;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.DAO;
import org.jbookshelf.model.db.api.Identifiable;

/**
 * @author eav
 * @param <T>
 */
public abstract class CayenneDAO<T extends Identifiable>
    implements
    DAO<T>
{
    private static final Logger  log = Logger.getLogger( CayenneDAO.class );
    private final Class<T>       entityClass;

    private static ObjectContext objectContext;

    @SuppressWarnings( "unchecked" )
    public CayenneDAO()
    {
        super();
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public T create()
    {
        try
        {
            return getEntityClass().newInstance();
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<T> findAll()
    {
        final ObjectContext context = context();
        final SelectQuery query = new SelectQuery( getEntityClass() );
        return context.performQuery( query );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public T getById(
        final Serializable id )
    {
        final ObjectContext context = context();
        final ObjectIdQuery query = new ObjectIdQuery( (ObjectId) id );
        return (T) DataObjectUtils.objectForQuery( context, query );
    }

    @Override
    public Class<? extends T> getEntityClass()
    {
        return entityClass;
    }

    @Override
    public T makePersistent(
        final T entity )
    {
        final ObjectContext context = context();
        context.registerNewObject( entity );
        context.commitChanges();
        return entity;
    }

    @Override
    public void makeTransient(
        final T entity )
    {
        final ObjectContext context = context();
        context.deleteObject( entity );
        context.commitChanges();
    }

    protected ObjectContext context()
    {
        if ( objectContext == null )
        {
            objectContext = DataContext.createDataContext();
        }
        return objectContext;
    }
}
