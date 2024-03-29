/**
 * 
 */
package org.jbookshelf.model.db.dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Named;

/**
 * @author eav 2009
 * @param <T>
 */
public abstract class UniqueDAO<T extends Named>
    extends AbstractGenericDAO<T>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( UniqueDAO.class );

    public T getByName(
        final String name )
    {
        final String sql = "select id from " + entityClass.getSimpleName() + " where name=?";
        final Object id = runner.query( sql, new ScalarHandler(), new Object[]
        { name } );
        return getById( Long.valueOf( id.toString() ) );
    }
}
