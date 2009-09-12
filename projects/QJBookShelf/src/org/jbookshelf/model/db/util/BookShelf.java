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
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.HasBooks;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.Unique;

/**
 * performs operations with database todo should be refactored
 * 
 * @author eav
 */
public class BookShelf
{
    private static final Logger    log    = Logger.getLogger( BookShelf.class );
    private static final LogRunner runner = new LogRunner();

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
            final Book book = (Book) session.get( Book.class, (Serializable) id );
            log.debug( "book returned " + book + " for id=" + id );
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
    public static Book bookByName(
        final String name )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Query query = session.createQuery( "from Book b where b.name=:p" );
            query.setString( "p", name );
            final List<Book> list = query.list();
            return list.size() > 0
                ? list.get( 0 ) : null;
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
    public static List<Note> getNotesByPage(
        final Bookmark bookmark )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Criteria criteria = session.createCriteria( Note.class );
            criteria.add( Restrictions.eq( "book", bookmark.getBook() ) );
            criteria.add( Restrictions.eq( "page", bookmark.getPage() ) );
            criteria.add( Restrictions.ne( "id", bookmark.getBook().getLastRead().getId() ) );

            return criteria.list();
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
    public static List<Note> getNotesByPosition(
        final Bookmark bookmark )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Criteria criteria = session.createCriteria( Note.class );
            criteria.add( Restrictions.eq( "book", bookmark.getBook() ) );
            final Float position = bookmark.getPosition();
            final Float pageSize = bookmark.getRelativePageSize();
            final float lo = position - pageSize;
            final float hi = position + pageSize;
            criteria.add( Restrictions.between( "position", lo, hi ) );
            criteria.add( Restrictions.ne( "id", bookmark.getBook().getLastRead().getId() ) );

            return criteria.list();
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

    public static Category getOrAddCategory(
        final String name,
        final Category parent )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Criteria criteria = session.createCriteria( Category.class );
            criteria.add( Restrictions.eq( "name", name ) );
            criteria.add( Restrictions.eq( "parent", parent ) );
            final List list = criteria.list();
            if ( list.size() == 1 )
            {
                return (Category) list.get( 0 );
            }

            final Category category = new Category();
            category.setName( name );

            session.beginTransaction();
            session.persist( category );
            session.getTransaction().commit();

            setParent( parent, category );
            return category;
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

            if ( unique instanceof Category && !Category.ROOT.equals( unique.getName() ) )
            {
                final Category category = (Category) unique;
                final Category rootCategory = rootCategory();
                setParent( rootCategory, category );
            }

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

    @SuppressWarnings( "unchecked" )
    public static <T extends Unique> T getUnique(
        final Class<T> clazz,
        final String name )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            final Criteria criteria = session.createCriteria( clazz ).add( Restrictions.eq( "name", name ) );
            final List list = criteria.list();
            return list.size() > 0
                ? (T) list.get( 0 ) : null;
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

    public static void mergeBook(
        @Nonnull final Book book,
        @Nonnull final Session session )
    {
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

        if ( book.getLastRead() != null )
        {
            book.getNotes().add( book.getLastRead() );
            session.merge( book.getLastRead() );
        }

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
            final Note lastRead = book.getLastRead();

            session.load( book, book.getId() );

            book.setLastRead( lastRead );
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

    public static void moveBook(
        final Book book,
        final Category oldCategory,
        final Category newCategory )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            getBooks( oldCategory ).remove( book );
            getBooks( newCategory ).add( book );
            getCategories( book ).remove( oldCategory );
            getCategories( book ).add( newCategory );

            session.beginTransaction();
            session.merge( book );
            session.merge( oldCategory );
            session.merge( newCategory );
            session.getTransaction().commit();
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
            final Note lastRead = book.getLastRead();
            if ( lastRead != null )
            {
                lastRead.timestamp();
                session.persist( lastRead );
            }

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
        try
        {
            for ( final Unique unique : selectedUniques )
            {
                if ( unique instanceof Book )
                {
                    removeBook( (Book) unique );
                }
                else if ( unique instanceof Author )
                {
                    removeAuthor( (Author) unique );
                }
                else
                {
                    removeCategory( (Category) unique );
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
        final Bookmark note )
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

    public static void rename(
        final Unique unique,
        final String newName )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            unique.setName( newName );
            session.update( unique );
            session.getTransaction().commit();
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

    public static Category rootCategory()
    { // todo cache?
        // todo name confilct is possible 
        return getOrAddUnique( Category.class, Category.ROOT );
    }

    public static void setParent(
        @Nonnull final Category parentCategory,
        @Nonnull final Category childCategory )
    {
        log.debug( "addChild" );
        final Session session = HibernateUtil.getSession();
        try
        {
            session.load( childCategory, childCategory.getId() );
            final Category oldParent = childCategory.getParent();
            childCategory.setParent( parentCategory );

            if ( oldParent != null )
            {
                getChildren( oldParent ).remove( childCategory );
            }
            getChildren( parentCategory ).add( childCategory );

            session.beginTransaction();
            session.merge( childCategory );
            if ( parentCategory != null )
            {
                session.merge( parentCategory );
            }
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

    public static void updatePhysical(
        final PhysicalBook physical )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            session.merge( physical );
            session.getTransaction().commit();
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

    private static void removeAuthor(
        final Author author )
        throws SQLException
    {
        final String q2 = "delete from author_book where authors_id=?";
        final String q6 = "delete from author where id=?";

        runner.update( q2, new Object[]
        { author.getId() } );
        runner.update( q6, new Object[]
        { author.getId() } );
    }

    private static void removeBook(
        final Book unique )
        throws SQLException
    {
        final String q1 = "delete from author_book where books_id=?";
        final String q2 = "delete from category_book where books_id=?";
        final String q3 = "delete from physical_book where book_id=?";
        final String q4 = "update book set LASTREAD_ID=null where id=?";
        final String q5 = "delete from note where book_id=?";
        final String q6 = "delete from book where id=?";

        runner.update( q1, new Object[]
        { unique.getId() } );
        runner.update( q2, new Object[]
        { unique.getId() } );
        runner.update( q3, new Object[]
        { unique.getId() } );
        runner.update( q4, new Object[]
        { unique.getId() } );
        runner.update( q5, new Object[]
        { unique.getId() } );
        runner.update( q6, new Object[]
        { unique.getId() } );
    }

    private static void removeCategory(
        final Category category )
        throws SQLException
    {
        final String q4 = "delete from category_book where categories_id=?";
        // move all children up
        final String q9 = "update category set parent_id=(select parent_id from category where id=?) where parent_id=?";
        final String q7 = "delete from category where id=?";

        runner.update( q4, new Object[]
        { category.getId() } );
        runner.update( q9, new Object[]
        { category.getId(), category.getId() } );
        runner.update( q7, new Object[]
        { category.getId() } );
    }
}
