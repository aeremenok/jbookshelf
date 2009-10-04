/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 */
public class BookDAO
    extends BookShelfDAO<Book>
{
    private static final Logger log = Logger.getLogger( BookDAO.class );

    /* (non-Javadoc)
     * @see org.jbookshelf.model.db.dao.GenericDAO#findByExample(java.io.Serializable, java.lang.String[])
     */
    @Override
    public List<Book> findByExample(
        final Book example,
        final String... excludeProperties )
    {
        log.debug( "findByExample" );
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Book makePersistent(
        final Book entity )
    {
        try
        {
            final Object res = runner.query( "select hibernate_sequence.nextval", new ScalarHandler() );
            final Long id = Long.valueOf( res.toString() );

            runner.update( "insert into Book (id,name) values(?,?)", new Object[]
            { id, entity.getName() } );

            entity.setId( id );

            return entity;
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }
}
