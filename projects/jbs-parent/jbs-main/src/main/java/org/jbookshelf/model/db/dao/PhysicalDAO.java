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
        final BeanHandler<PhysicalBook> handler = new BeanHandler<PhysicalBook>( entityClass );
        final String sql = "select * from PHYSICAL_BOOK where book_id=?";
        return runner.query( sql, handler, new Object[]
        { byId.getId() } );
    }

    @Override
    public PhysicalBook makePersistent(
        final PhysicalBook entity )
    {
        if ( !checkIfPersistent( entity ) )
        {
            final Long id = generateId();
            final StringBuilder q = new StringBuilder( "insert into PHYSICAL_BOOK (" );
            q.append( "id,charsetName,fileName,unpackedFileName,viewer,book_id" );
            q.append( ") values (?,?,?,?,?,?)" );

            runner.update( q.toString(), new Object[]
            { id, entity.getCharsetName(), entity.getFileName(), entity.getUnpackedFileName(), entity.getViewer(),
                            entity.getBook().getId() } );

            entity.setId( id );
        }
        return entity;
    }
}
