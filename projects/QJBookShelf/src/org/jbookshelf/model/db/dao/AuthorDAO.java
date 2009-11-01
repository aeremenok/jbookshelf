/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;

/**
 * @author eav 2009
 */
public class AuthorDAO
    extends NamedDAO<IAuthor>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( AuthorDAO.class );

    @Override
    public IAuthor makePersistent(
        final IAuthor entity )
    {
        if ( !checkIfPersistent( entity ) )
        {
            final Long id = generateId();
            runner.update( "insert into Author (id,name) values(?,?)", new Object[]
            { id, entity.getName() } );
            entity.setId( id );

            final BookDAO bookDAO = new BookDAO();
            for ( final IBook book : entity.getBooks() )
            {
                bookDAO.makePersistent( book );
            }
        }
        return entity;
    }

    @Override
    public void makeTransient(
        final IAuthor entity )
    {
        final StringBuilder q = new StringBuilder();
        q.append( "delete from author_book where authors_id=?; " );
        q.append( "delete from author where id=?; " );

        final List<Long> ids = new ArrayList<Long>();
        for ( int i = 0; i < 2; i++ )
        {
            ids.add( (Long) entity.getId() );
        }
        runner.update( q.toString(), ids.toArray() );
    }
}
