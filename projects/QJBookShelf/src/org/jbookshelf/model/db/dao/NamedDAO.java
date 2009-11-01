/**
 * 
 */
package org.jbookshelf.model.db.dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.jbookshelf.model.db.api.Named;

/**
 * @author eav 2009
 * @param <T>
 */
public abstract class NamedDAO<T extends Named>
    extends AbstractDAO<T>
{
    public T getByName(
        final String name )
    {
        final String sql = "select id from " + entityClass.getSimpleName() + " where name=?";
        final Object id = runner.query( sql, new ScalarHandler(), new Object[]
        { name } );
        return getById( Long.valueOf( id.toString() ) );
    }
}
