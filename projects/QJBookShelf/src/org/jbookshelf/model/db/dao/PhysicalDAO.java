/**
 * 
 */
package org.jbookshelf.model.db.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;

/**
 * @author eav 2009
 */
public class PhysicalDAO
    extends AbstractGenericDAO<PhysicalBook>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( PhysicalDAO.class );

    public PhysicalBook getByBook(
        final Book byId )
    {
        final BeanHandler handler = new BeanHandler( entityClass );
        return (PhysicalBook) runner.query( "select * from PHYSICAL_BOOK where book_id=?", handler, new Object[]
        { byId.getId() } );
    }

    @Override
    public PhysicalBook makePersistent(
        final PhysicalBook entity )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
