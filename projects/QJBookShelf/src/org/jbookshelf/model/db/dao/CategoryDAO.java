/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.EJBQLQuery;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.dao.ICategoryDAO;
import org.jbookshelf.model.db.cayenne.Category;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;

/**
 * @author eav 2009
 */
public class CategoryDAO
    extends CayenneNamedDAO<ICategory>
    implements
    ICategoryDAO
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( CategoryDAO.class );

    @Override
    public ICategory create()
    {
        return new Category();
    }

    @Override
    public Class<? extends ICategory> getEntityClass()
    {
        return Category.class;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public ICategory getOrAdd(
        final String name,
        final ICategory parent )
    {
        ICategory c = getByName( name );
        if ( c == null )
        {
            c = create();
            c.setName( name );
            c.setParent( parent );
            return makePersistent( c );
        }

        if ( !parent.equals( c.getParent() ) )
        {
            final ObjectContext context = context();
            c.setParent( parent );
            parent.getChildren().add( c );
            context.commitChanges();
        }
        return c;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void moveBook(
        final IBook book,
        final ICategory oldCategory,
        final ICategory newCategory )
    {
        final ObjectContext context = context();

        book.getCategories().remove( oldCategory );
        oldCategory.getBooks().remove( book );

        book.getCategories().add( newCategory );
        newCategory.getBooks().add( book );

        context.commitChanges();
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<ICategory> query(
        final Parameters parameters )
    {
        final ObjectContext context = context();
        final String text = parameters.get( Keys.SEARCH_TEXT );
        final EJBQLQuery query = new EJBQLQuery( "select c from category c where lower(c.name) like lower(:n%) " );
        query.setParameter( "n", text );
        return context.performQuery( query );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void setParent(
        final ICategory parentCategory,
        final ICategory childCategory )
    {
        final ObjectContext context = context();

        final ICategory oldParent = childCategory.getParent();
        if ( oldParent != null )
        {
            oldParent.getChildren().remove( childCategory );
        }
        parentCategory.getChildren().add( childCategory );
        childCategory.setParent( parentCategory );

        context.commitChanges();
    }
}
