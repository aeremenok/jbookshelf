/**
 * 
 */
package org.jbookshelf.model.db.dao;

import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.api.NamedDAO;

/**
 * @author eav 2009
 * @param <T>
 */
public abstract class CayenneNamedDAO<T extends Named>
    extends CayenneDAO<T>
    implements
    NamedDAO<T>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( CayenneNamedDAO.class );

    @SuppressWarnings( "unchecked" )
    @Override
    public T getByName(
        final String name )
    {
        final ObjectContext context = context();
        final Expression qual = Expression.fromString( "name = $n" );
        final SelectQuery query = new SelectQuery( getEntityClass(), qual );
        qual.setOperand( 1, name );
        return (T) DataObjectUtils.objectForQuery( context, query );
    }

    @Override
    public T getOrAdd(
        final String name )
    {
        final T byName = getByName( name );
        if ( byName != null )
        {
            return byName;
        }
        return makePersistent( create() );
    }

    @Override
    public void rename(
        final T named,
        final String newName )
    {
        final ObjectContext context = context();
        named.setName( newName );
        context.commitChanges();
    }
}
