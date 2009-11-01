/**
 * 
 */
package org.jbookshelf.model.db.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

/**
 * @author eav 2009
 */
public class PhysicalDAO
    extends AbstractDAO<IPhysicalBook>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( PhysicalDAO.class );

    public IPhysicalBook getByBook(
        final IBook byId )
    {
        final BeanHandler handler = new BeanHandler( entityClass );
        final String sql = "select * from PHYSICAL_BOOK where book_id=?";
        return (IPhysicalBook) runner.query( sql, handler, new Object[]
        { byId.getId() } );
    }

    @Override
    public IPhysicalBook makePersistent(
        final IPhysicalBook entity )
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
