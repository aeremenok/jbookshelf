/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.api.Bookmark;
import org.jbookshelf.model.db.api.HasBooks;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.dao.AuthorDAO;
import org.jbookshelf.model.db.dao.BookDAO;
import org.jbookshelf.model.db.dao.CategoryDAO;

/**
 * performs operations with database todo should be refactored
 * 
 * @author eav
 */
public class BookShelf
{
    private static final Logger      log         = Logger.getLogger( BookShelf.class );

    private static final BookDAO     bookDAO     = new BookDAO();
    private static final AuthorDAO   authorDAO   = new AuthorDAO();
    private static final CategoryDAO categoryDAO = new CategoryDAO();

    public static List<Book> allBooks()
    {
        return bookDAO.findAll();
    }

    public static Book bookById(
        final Object id )
    {
        return bookDAO.getById( (Long) id );
    }

    public static Book bookByName(
        final String name )
    {
        return bookDAO.getByName( name );
    }

    public static int bookCount()
    {
        return bookDAO.totalCount();
    }

    public static Book createBook(
        final String bookName,
        final String authorName,
        final String categoryName,
        final PhysicalBook physicalUnit )
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
        return bookDAO.getAuthors( book );
    }

    public static Set<Book> getBooks(
        final HasBooks hasBooks )
    {
        return hasBooks.getBooks();
    }

    public static Set<Category> getCategories(
        final Book book )
    {
        return bookDAO.getCategories( book );
    }

    public static Set<Category> getChildren(
        final Category category )
    {
        return category.getChildren();
    }

    public static Set<Note> getNotes(
        final Book book )
    {
        return bookDAO.getNotes( book );
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
    public static <T extends Named> T getOrAddUnique(
        final Class<T> class1,
        final String name )
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
    public static <T extends Named> T getUnique(
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
        final Book book,
        final Session session )
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
        final Book book )
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

    public static void remove(
        final Set<Named> selectedUniques )
    {
        for ( final Named unique : selectedUniques )
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
        final Named unique,
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
        final Category parentCategory,
        final Category childCategory )
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
    {
        authorDAO.makeTransient( author );
    }

    private static void removeBook(
        final Book unique )
    {
        bookDAO.makeTransient( unique );
    }

    private static void removeCategory(
        final Category category )
    {
        categoryDAO.makeTransient( category );
    }
}
