/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.HasBooks;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.Unique;

/**
 * @author eav
 */
public class BookShelf
{
    private static final Logger log = Logger.getLogger( BookShelf.class );

    @SuppressWarnings( "unchecked" )
    public static List<Book> allBooks()
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            return session.createQuery( "from Book" ).list();
        }
        catch ( final Exception e2 )
        {
            log.error( e2, e2 );
            throw new Error( e2 );
        }
        finally
        {
            session.close();
        }
    }

    public static Book bookById(
        @Nonnull final Object id )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            return (Book) session.get( Book.class, (Serializable) id );
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

    public static Book bookByName(
        final String name )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Query query = session.createQuery( "from Book b where b.name=:p" );
            query.setString( "p", name );
            final List list = query.list();
            return list.size() > 0
                ? (Book) list.get( 0 ) : null;
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

    public static int bookCount()
    {
        try
        {
            final LogRunner runner = new LogRunner();
            final Object object = runner.query( "select count(*) from book", new ScalarHandler() );
            return Integer.valueOf( object.toString() );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

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

    public static Set<Author> getAuthors(
        final Book book )
    {
        if ( book.getId() != null )
        {
            final Session session = HibernateUtil.getSession();
            try
            {
                // todo remove obsolete query
                session.load( book, book.getId() );
                Hibernate.initialize( book.getAuthors() );
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
        return book.getAuthors();
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

    public static Set<Category> getCategories(
        final Book book )
    {
        if ( book.getId() != null )
        {
            final Session session = HibernateUtil.getSession();
            try
            {
                // todo remove obsolete query
                session.load( book, book.getId() );
                Hibernate.initialize( book.getCategories() );
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
        return book.getCategories();
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

    public static Set<Note> getNotes(
        final Book book )
    {
        if ( book.getId() != null )
        {
            final Session session = HibernateUtil.getSession();
            try
            {
                // todo remove obsolete query
                session.load( book, book.getId() );
                Hibernate.initialize( book.getNotes() );
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
        return book.getNotes();
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
     * @param session
     */
    public static void mergeBook(
        @Nonnull final Book book,
        @Nonnull final Session session )
    {
        log.debug( "mergeBook" );

        session.beginTransaction();

        for ( final Author author : book.getAuthors() )
        {
            if ( !session.contains( author ) )
            {
                session.load( author, author.getId() );
            }
            author.getBooks().add( book );
        }

        for ( final Category category : book.getCategories() )
        {
            if ( !session.contains( category ) )
            {
                session.load( category, category.getId() );
            }
            category.getBooks().add( book );
        }
        session.merge( book );
        session.merge( book.getPhysicalBook() );

        session.getTransaction().commit();
    }

    public static void mergeNote(
        final Note note )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            note.timestamp();
            session.beginTransaction();
            session.saveOrUpdate( note );

            final Book book = note.getBook();
            session.load( book, book.getId() );
            book.getNotes().add( note );
            session.merge( book );

            session.getTransaction().commit();
        }
        catch ( final Throwable e )
        {
            log.error( e, e );
            throw new Error( e );
        }
        finally
        {
            session.close();
        }
    }

    public static void mergeRelatedBooks(
        final Book book,
        final List<Book> relatedBooks )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            session.load( book, book.getId() );
            book.getRelatedBooks().clear();
            book.getRelatedBooks().addAll( relatedBooks );

            session.merge( book );

            session.getTransaction().commit();
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

    /**
     * @param book
     */
    public static void persistBook(
        @Nonnull final Book book )
    {
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
            throw new Error( e );
        }
        finally
        {
            session.close();
        }
    }

    /**
     * @param selectedUniques
     */
    public static void remove(
        final Set<Unique> selectedUniques )
    {
        final LogRunner runner = new LogRunner();

        final String q1 = "delete from author_book where books_id=?";
        final String q2 = "delete from author_book where authors_id=?";

        final String q3 = "delete from category_book where books_id=?";
        final String q4 = "delete from category_book where categories_id=?";

        final String q5 = "delete from book where id=?";
        final String q6 = "delete from author where id=?";
        final String q7 = "delete from category where id=?";

        final String q8 = "delete from physical_book where book_id=?";

        try
        {
            for ( final Unique unique : selectedUniques )
            {
                if ( unique instanceof Book )
                {
                    runner.update( q1, new Object[]
                    { unique.getId() } );
                    runner.update( q3, new Object[]
                    { unique.getId() } );
                    runner.update( q8, new Object[]
                    { unique.getId() } );
                    runner.update( q5, new Object[]
                    { unique.getId() } );
                }
                else if ( unique instanceof Author )
                {
                    runner.update( q2, new Object[]
                    { unique.getId() } );
                    runner.update( q6, new Object[]
                    { unique.getId() } );
                }
                else
                {
                    runner.update( q4, new Object[]
                    { unique.getId() } );
                    runner.update( q7, new Object[]
                    { unique.getId() } );
                }
            }
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    public static void removeNote(
        final Note note )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            session.load( note, note.getId() );

            final Book book = note.getBook();
            book.getNotes().remove( note );
            session.merge( book );

            session.delete( note );
            session.getTransaction().commit();
        }
        catch ( final Throwable e1 )
        {
            log.error( e1, e1 );
            throw new Error( e1 );
        }
        finally
        {
            session.close();
        }
    }

    /**
     * @param book
     */
    public static void updateIsRead(
        final Book book )
    {
        try
        {
            final LogRunner runner = new LogRunner();
            runner.update( "update book set read=? where id=?", new Object[]
            { book.getRead(), book.getId() } );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }
}