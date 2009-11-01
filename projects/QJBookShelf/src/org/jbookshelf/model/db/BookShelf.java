/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.Bookmark;
import org.jbookshelf.model.db.api.DAO;
import org.jbookshelf.model.db.api.HasBooks;
import org.jbookshelf.model.db.api.Identifiable;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.api.NamedDAO;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.model.db.api.spec.dao.IAuthorDAO;
import org.jbookshelf.model.db.api.spec.dao.IBookDAO;
import org.jbookshelf.model.db.api.spec.dao.ICategoryDAO;
import org.jbookshelf.model.db.api.spec.dao.INoteDAO;
import org.jbookshelf.model.db.api.spec.dao.IPhusicalBookDAO;
import org.jbookshelf.view.logic.Parameters;

/**
 * performs operations with database todo should be refactored
 * 
 * @author eav
 */
public class BookShelf
{
    private static final Logger           log             = Logger.getLogger( BookShelf.class );

    private static final IBookDAO         bookDAO         = null;
    private static final IAuthorDAO       authorDAO       = null;
    private static final ICategoryDAO     categoryDAO     = null;
    private static final INoteDAO         noteDAO         = null;
    private static final IPhusicalBookDAO physicalBookDAO = null;

    public static List<IBook> allBooks()
    {
        return bookDAO.findAll();
    }

    public static IBook bookById(
        final Object id )
    {
        return bookDAO.getById( (Long) id );
    }

    public static IBook bookByName(
        final String name )
    {
        return bookDAO.getByName( name );
    }

    public static int bookCount()
    {
        return bookDAO.totalCount();
    }

    public static IAuthor createAuthor()
    {
        return authorDAO.create();
    }

    public static IBook createBook()
    {
        return bookDAO.create();
    }

    public static IBook createBook(
        final String bookName,
        final String authorName,
        final String categoryName,
        final IPhysicalBook physicalUnit )
    {
        return bookDAO.create( bookName, authorName, categoryName, physicalUnit );
        //        final IBook book = createBook();
        //        book.setName( bookName );
        //        book.setPhysicalBook( physicalUnit );
        //
        //        if ( authorName != null && !"".equals( bookName ) )
        //        {
        //            book.getAuthors().add( getOrAddUnique( IAuthor.class, authorName ) );
        //        }
        //        if ( categoryName != null && !"".equals( categoryName ) )
        //        {
        //            book.getCategories().add( getOrAddUnique( ICategory.class, categoryName ) );
        //        }
        //
        //        return book;
    }

    public static ICategory createCategory()
    {
        return categoryDAO.create();
    }

    public static INote createNote()
    {
        return noteDAO.create();
    }

    public static IPhysicalBook createPhysicalBook()
    {
        return physicalBookDAO.create();
    }

    public static Set<IAuthor> getAuthors(
        final IBook book )
    {
        return bookDAO.getAuthors( book );
    }

    public static Set<IBook> getBooks(
        final HasBooks hasBooks )
    {
        return hasBooks.getBooks();
    }

    public static Set<ICategory> getCategories(
        final IBook book )
    {
        return bookDAO.getCategories( book );
    }

    public static Set<ICategory> getChildren(
        final ICategory category )
    {
        return category.getChildren();
    }

    public static Set<INote> getNotes(
        final IBook book )
    {
        return bookDAO.getNotes( book );
    }

    public static List<INote> getNotesByPage(
        final Bookmark bookmark )
    {
        return noteDAO.getByPage( bookmark );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            final Criteria criteria = session.createCriteria( INote.class );
        //            criteria.add( Restrictions.eq( "book", bookmark.getBook() ) );
        //            criteria.add( Restrictions.eq( "page", bookmark.getPage() ) );
        //            criteria.add( Restrictions.ne( "id", bookmark.getBook().getLastRead().getId() ) );
        //
        //            return criteria.list();
        //        }
        //        catch ( final Exception e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static List<INote> getNotesByPosition(
        final Bookmark bookmark )
    {
        return noteDAO.getByPosition( bookmark );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            final Criteria criteria = session.createCriteria( INote.class );
        //            criteria.add( Restrictions.eq( "book", bookmark.getBook() ) );
        //            final Float position = bookmark.getPosition();
        //            final Float pageSize = bookmark.getRelativePageSize();
        //            final float lo = position - pageSize;
        //            final float hi = position + pageSize;
        //            criteria.add( Restrictions.between( "position", lo, hi ) );
        //            criteria.add( Restrictions.ne( "id", bookmark.getBook().getLastRead().getId() ) );
        //
        //            return criteria.list();
        //        }
        //        catch ( final Exception e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static ICategory getOrAddCategory(
        final String name,
        final ICategory parent )
    {
        return categoryDAO.getOrAdd( name, parent );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            final Criteria criteria = session.createCriteria( Category.class );
        //            criteria.add( Restrictions.eq( "name", name ) );
        //            criteria.add( Restrictions.eq( "parent", parent ) );
        //            final List list = criteria.list();
        //            if ( list.size() == 1 )
        //            {
        //                return (ICategory) list.get( 0 );
        //            }
        //
        //            final ICategory category = createCategory();
        //            category.setName( name );
        //
        //            session.beginTransaction();
        //            session.persist( category );
        //            session.getTransaction().commit();
        //
        //            setParent( parent, category );
        //            return category;
        //        }
        //        catch ( final Exception e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    @SuppressWarnings( "unchecked" )
    public static <T extends Named> T getOrAddUnique(
        final Class<T> class1,
        final String name )
    {
        final NamedDAO namedDAO = (NamedDAO) getDAO( class1 );
        return (T) namedDAO.getOrAdd( name );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            final Criteria criteria = session.createCriteria( class1 ).add( Restrictions.eq( "name", name ) );
        //            final List list = criteria.list();
        //
        //            if ( list.size() == 1 )
        //            {
        //                return (T) list.get( 0 );
        //            }
        //
        //            final T unique = class1.newInstance();
        //            unique.setName( name );
        //
        //            session.beginTransaction();
        //            session.persist( unique );
        //            session.getTransaction().commit();
        //
        //            if ( unique instanceof ICategory && !ICategory.ROOT.equals( unique.getName() ) )
        //            {
        //                final ICategory category = (ICategory) unique;
        //                final ICategory rootCategory = rootCategory();
        //                setParent( rootCategory, category );
        //            }
        //
        //            return unique;
        //        }
        //        catch ( final Exception e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    @SuppressWarnings( "unchecked" )
    public static <T extends Named> T getUnique(
        final Class<T> class1,
        final String name )
    {
        final NamedDAO namedDAO = (NamedDAO) getDAO( class1 );
        return (T) namedDAO.getByName( name );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            final Criteria criteria = session.createCriteria( clazz ).add( Restrictions.eq( "name", name ) );
        //            final List list = criteria.list();
        //            return list.size() > 0
        //                ? (T) list.get( 0 ) : null;
        //        }
        //        catch ( final Throwable e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static void mergeBook(
        final IBook book )
    {
        bookDAO.update( book );
        //        session.beginTransaction();
        //
        //        for ( final IAuthor author : book.getAuthors() )
        //        {
        //            if ( !session.contains( author ) )
        //            {
        //                session.load( author, author.getId() );
        //            }
        //            author.getBooks().add( book );
        //        }
        //
        //        for ( final ICategory category : book.getCategories() )
        //        {
        //            if ( !session.contains( category ) )
        //            {
        //                session.load( category, category.getId() );
        //            }
        //            category.getBooks().add( book );
        //        }
        //        session.merge( book );
        //        session.merge( book.getPhysicalBook() );
        //
        //        if ( book.getLastRead() != null )
        //        {
        //            book.getNotes().add( book.getLastRead() );
        //            session.merge( book.getLastRead() );
        //        }
        //
        //        session.getTransaction().commit();
    }

    public static void mergeNote(
        final INote note )
    {
        noteDAO.updateNote( note );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            note.timestamp();
        //
        //            session.beginTransaction();
        //
        //            session.saveOrUpdate( note );
        //
        //            final IBook book = note.getBook();
        //            final INote lastRead = book.getLastRead();
        //
        //            session.load( book, book.getId() );
        //
        //            book.setLastRead( lastRead );
        //            book.getNotes().add( note );
        //
        //            session.merge( book );
        //
        //            session.getTransaction().commit();
        //        }
        //        catch ( final Throwable e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static void mergeRelatedBooks(
        final IBook book,
        final List<IBook> relatedBooks )
    {
        bookDAO.mergeRelatedBooks( book, relatedBooks );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            session.beginTransaction();
        //            session.load( book, book.getId() );
        //            book.getRelatedBooks().clear();
        //            book.getRelatedBooks().addAll( relatedBooks );
        //
        //            session.merge( book );
        //
        //            session.getTransaction().commit();
        //        }
        //        catch ( final HibernateException e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static void moveBook(
        final IBook book,
        final ICategory oldCategory,
        final ICategory newCategory )
    {
        categoryDAO.moveBook( book, oldCategory, newCategory );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            getBooks( oldCategory ).remove( book );
        //            getBooks( newCategory ).add( book );
        //            getCategories( book ).remove( oldCategory );
        //            getCategories( book ).add( newCategory );
        //
        //            session.beginTransaction();
        //            session.merge( book );
        //            session.merge( oldCategory );
        //            session.merge( newCategory );
        //            session.getTransaction().commit();
        //        }
        //        catch ( final Exception e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static void persistBook(
        final IBook book )
    {
        bookDAO.makePersistent( book );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            session.beginTransaction();
        //            for ( final IAuthor author : book.getAuthors() )
        //            {
        //                session.load( author, author.getId() );
        //                author.getBooks().add( book );
        //            }
        //
        //            for ( final ICategory category : book.getCategories() )
        //            {
        //                session.load( category, category.getId() );
        //                category.getBooks().add( book );
        //            }
        //
        //            session.persist( book );
        //            session.persist( book.getPhysicalBook() );
        //            final INote lastRead = book.getLastRead();
        //            if ( lastRead != null )
        //            {
        //                lastRead.timestamp();
        //                session.persist( lastRead );
        //            }
        //
        //            session.getTransaction().commit();
        //        }
        //        catch ( final HibernateException e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    @SuppressWarnings( "unchecked" )
    public static <T extends Identifiable> List<T> query(
        final Class<T> class1,
        final Parameters p )
    {
        return getDAO( class1 ).query( p );
    }

    @SuppressWarnings( "unchecked" )
    public static void remove(
        final Set<Named> selectedUniques )
    {
        for ( final Named unique : selectedUniques )
        {
            final DAO<Named> dao = getDAO( unique.getClass() );
            dao.makeTransient( unique );
        }
    }

    public static void removeNote(
        final INote note )
    {
        noteDAO.makeTransient( note );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            session.beginTransaction();
        //            session.load( note, note.getId() );
        //
        //            final IBook book = note.getBook();
        //            book.getNotes().remove( note );
        //            session.merge( book );
        //
        //            session.delete( note );
        //            session.getTransaction().commit();
        //        }
        //        catch ( final Throwable e1 )
        //        {
        //            log.error( e1, e1 );
        //            throw new Error( e1 );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    @SuppressWarnings( "unchecked" )
    public static void rename(
        final Named unique,
        final String newName )
    {
        final NamedDAO<Named> namedDAO = (NamedDAO) getDAO( unique.getClass() );
        namedDAO.rename( unique, newName );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            session.beginTransaction();
        //            unique.setName( newName );
        //            session.update( unique );
        //            session.getTransaction().commit();
        //        }
        //        catch ( final Exception e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static ICategory rootCategory()
    { // todo cache?
        // todo name confilct is possible 
        return getOrAddUnique( ICategory.class, ICategory.ROOT );
    }

    public static void setParent(
        final ICategory parentCategory,
        final ICategory childCategory )
    {
        log.debug( "addChild" );
        categoryDAO.setParent( parentCategory, childCategory );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            session.load( childCategory, childCategory.getId() );
        //            final ICategory oldParent = childCategory.getParent();
        //            childCategory.setParent( parentCategory );
        //
        //            if ( oldParent != null )
        //            {
        //                getChildren( oldParent ).remove( childCategory );
        //            }
        //            getChildren( parentCategory ).add( childCategory );
        //
        //            session.beginTransaction();
        //            session.merge( childCategory );
        //            if ( parentCategory != null )
        //            {
        //                session.merge( parentCategory );
        //            }
        //            session.getTransaction().commit();
        //        }
        //        catch ( final Throwable e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    public static void updatePhysical(
        final IPhysicalBook physical )
    {
        physicalBookDAO.update( physical );
        //        final Session session = HibernateUtil.getSession();
        //        try
        //        {
        //            session.beginTransaction();
        //            session.merge( physical );
        //            session.getTransaction().commit();
        //        }
        //        catch ( final Exception e )
        //        {
        //            log.error( e, e );
        //            throw new Error( e );
        //        }
        //        finally
        //        {
        //            session.close();
        //        }
    }

    private static <T extends Identifiable> DAO getDAO(
        final Class<T> class1 )
    {
        if ( IAuthor.class.equals( class1 ) )
        {
            return authorDAO;
        }
        else if ( ICategory.class.equals( class1 ) )
        {
            return categoryDAO;
        }
        return bookDAO;
    }
}
