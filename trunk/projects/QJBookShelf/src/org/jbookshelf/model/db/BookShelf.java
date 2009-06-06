/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author eav
 */
public class BookShelf
{
    private static final Logger log = Logger.getLogger( BookShelf.class );

    @Nullable
    public static Book createBook(
        @Nonnull final String bookName,
        @Nullable final String authorName,
        @Nullable final String categoryName,
        @Nonnull final PhysicalBook physicalUnit )
    {
        final Book book = new Book();
        book.setName( bookName );
        book.setPhysicalBook( physicalUnit );

        if ( authorName != null && !"".equals( bookName ) )
        {
            book.getAuthors().add( getOrAddUnique( Author.class, authorName ) );
        }
        if ( categoryName != null && !"".equals( categoryName ) )
        {
            book.getCategories().add( getOrAddUnique( Category.class, categoryName ) );
        }

        return book;
    }

    public static Set<Book> getBooks(
        final HasBooks hasBooks )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            // todo remove obsolete query
            session.load( hasBooks, hasBooks.getId() );
            Hibernate.initialize( hasBooks.getBooks() );
            return hasBooks.getBooks();
        }
        catch ( final HibernateException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
        finally
        {
            session.close();
        }

    }

    public static Set<Category> getChildren(
        final Category category )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            // todo remove obsolete query
            session.load( category, category.getId() );
            Hibernate.initialize( category.getChildren() );
            return category.getChildren();
        }
        catch ( final HibernateException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
        finally
        {
            session.close();
        }

    }

    @SuppressWarnings( "unchecked" )
    @Nonnull
    public static <T extends Unique> T getOrAddUnique(
        @Nonnull final Class<T> class1,
        @Nonnull final String name )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Criteria criteria = session.createCriteria( class1 ).add( Restrictions.eq( "name", name ) );
            final List list = criteria.list();

            if ( list.size() == 1 )
            {
                return (T) list.get( 0 );
            }

            final T unique = class1.newInstance();
            unique.setName( name );

            session.beginTransaction();
            session.persist( unique );
            session.getTransaction().commit();

            return unique;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
        finally
        {
            session.close();
        }
    }

    /**
     * @param book
     */
    public static void persistBook(
        @Nonnull final Book book )
    {
        log.debug( "persistBook" );
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            for ( final Author author : book.getAuthors() )
            {
                session.load( author, author.getId() );
                author.getBooks().add( book );
            }

            for ( final Category category : book.getCategories() )
            {
                session.load( category, category.getId() );
                category.getBooks().add( book );
            }

            session.persist( book );
            session.persist( book.getPhysicalBook() );

            session.getTransaction().commit();
        }
        catch ( final HibernateException e )
        {
            log.error( e, e );
        }
        finally
        {
            session.close();
        }
    }
}
