/**
 * 
 */
package org.jbookshelf.model.db.dao.alter;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.jbookshelf.model.db.Book;

/**
 * @author eav 2010
 */
public class BookDAO
    extends AbstractDAO<Book>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAO.class );

    public void delete(
        final Book book )
    {
        final Session session = openSession();
        try
        {
            session.beginTransaction();
            session.delete( book.getPhysicalBook() );
            session.delete( book.getLastRead() );
            session.delete( book );
            session.getTransaction().commit();
        }
        catch ( final Exception e )
        {
            logAndRollback( session, e );
            throw new Error( e );
        }
        finally
        {
            closeSession( session );
        }
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Book> findAll()
    {
        final Session session = openSession();
        try
        {
            return session.createCriteria( Book.class ).list();
        }
        finally
        {
            closeSession( session );
        }
    }

    public Book getById(
        final Serializable id )
    {
        final Session session = openSession();
        try
        {
            return (Book) session.get( Book.class, id );
        }
        finally
        {
            closeSession( session );
        }
    }

    public Book persist(
        final Book book )
    {
        final Session session = openSession();
        try
        {
            session.beginTransaction();
            book.getLastRead().timestamp();
            session.save( book );
            session.save( book.getLastRead() );
            session.save( book.getPhysicalBook() );
            session.getTransaction().commit();

            return book;
        }
        catch ( final Exception e )
        {
            logAndRollback( session, e );
            throw new Error( e );
        }
        finally
        {
            closeSession( session );
        }
    }

    public void update(
        final Book book )
    {
        final Session session = openSession();
        try
        {
            session.beginTransaction();
            session.merge( book );
            session.getTransaction().commit();
        }
        catch ( final Exception e )
        {
            logAndRollback( session, e );
            throw new Error( e );
        }
        finally
        {
            closeSession( session );
        }
    }
}
