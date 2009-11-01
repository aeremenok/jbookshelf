/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.util.List;
import java.util.Set;

import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.EJBQLQuery;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.model.db.api.spec.dao.IBookDAO;
import org.jbookshelf.model.db.cayenne.Book;
import org.jbookshelf.view.logic.Parameters;

/**
 * @author eav 2009
 */
public class BookDAO
    extends CayenneNamedDAO<IBook>
    implements
    IBookDAO
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAO.class );

    @Override
    public IBook create()
    {
        return new Book();
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public IBook create(
        final String bookName,
        final String authorName,
        final String categoryName,
        final IPhysicalBook physicalUnit )
    {
        final ObjectContext context = context();
        final IBook book = create();
        context.registerNewObject( book );
        book.setPhysicalBook( physicalUnit );

        final AuthorDAO authorDAO = new AuthorDAO();
        final IAuthor author = authorDAO.getOrAdd( authorName );

        book.getAuthors().add( author );
        author.getBooks().add( book );

        // todo category

        context.commitChanges();

        return book;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public Set<IAuthor> getAuthors(
        final IBook book )
    {
        return book.getAuthors();
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public Set<ICategory> getCategories(
        final IBook book )
    {
        return book.getCategories();
    }

    @Override
    public Class<? extends IBook> getEntityClass()
    {
        return Book.class;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public Set<INote> getNotes(
        final IBook book )
    {
        return book.getNotes();
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void mergeRelatedBooks(
        final IBook book,
        final List<IBook> newRelatedBooks )
    {
        final ObjectContext context = context();

        for ( final IBook oldRelatedBook : (Set<IBook>) book.getRelatedBooks() )
        {
            if ( !newRelatedBooks.contains( oldRelatedBook ) )
            {
                book.getRelatedBooks().remove( oldRelatedBook );
            }
        }
        book.getRelatedBooks().addAll( newRelatedBooks );

        context.commitChanges();
    }

    @Override
    public List<IBook> query(
        final Parameters parameters )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int totalCount()
    {
        final ObjectContext context = context();
        final EJBQLQuery query = new EJBQLQuery( "select count(b) from Book b" );
        return (Integer) DataObjectUtils.objectForQuery( context, query );
    }

    @Override
    public void update(
        final IBook book )
    {
        final ObjectContext context = context();
        context.commitChanges();
    }
}
