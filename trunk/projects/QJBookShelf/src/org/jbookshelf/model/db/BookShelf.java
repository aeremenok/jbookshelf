/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author eav
 */
public class BookShelf
{
    private static final Logger log = Logger.getLogger( BookShelf.class );

    @Nullable
    public static Book getBook(
        @Nonnull final String bookName,
        @Nonnull final String authorName,
        @Nonnull final String categoryName,
        @Nonnull final PhysicalBook physicalUnit )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Book book = new Book();
            book.setName( bookName );
            book.setPhysicalBook( physicalUnit );

            if ( authorName != null )
            {
                final Author author = getUnique( Author.class, authorName );
                session.load( author, author.getId() );
                book.addAuthor( author );
            }
            if ( categoryName != null )
            {
                final Category category = getUnique( Category.class, categoryName );
                session.load( category, category.getId() );
                book.addCategory( category );
            }

            session.beginTransaction();
            session.persist( book );
            session.persist( physicalUnit );
            session.getTransaction().commit();

            return book;
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

    @SuppressWarnings( "unchecked" )
    @Nonnull
    public static <T extends Unique> T getUnique(
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
}
