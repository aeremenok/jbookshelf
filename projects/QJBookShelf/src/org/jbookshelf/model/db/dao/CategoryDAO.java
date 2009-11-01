/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;

/**
 * @author eav 2009
 */
public class CategoryDAO
    extends NamedDAO<ICategory>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( CategoryDAO.class );

    @Override
    public ICategory makePersistent(
        final ICategory entity )
    {
        if ( !checkIfPersistent( entity ) )
        {
            if ( entity.getParent() != null )
            { // todo better recursion
                makePersistent( entity.getParent() );
            }

            final Serializable id = generateId();
            final Serializable parentId = entity.getParent() != null
                ? entity.getParent().getId() : null;
            runner.update( "insert into Category (id,name,parent_id) values(?,?,?)", new Object[]
            { id, entity.getName(), parentId } );
            entity.setId( id );

            for ( final ICategory child : entity.getChildren() )
            {
                makePersistent( child );
            }

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
        final ICategory category )
    {
        final StringBuilder q = new StringBuilder();
        q.append( "delete from category_book where categories_id=?; " );
        // move all children up
        q.append( "update category set parent_id=(select parent_id from category where id=?) where parent_id=?; " );
        q.append( "delete from category where id=?; " );

        final List<Serializable> ids = new ArrayList<Serializable>();
        for ( int i = 0; i < 4; i++ )
        {
            ids.add( category.getId() );
        }
        runner.update( q.toString(), ids.toArray() );
    }
}
