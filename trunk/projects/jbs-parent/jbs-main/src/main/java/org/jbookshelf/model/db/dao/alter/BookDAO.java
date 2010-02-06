/**
 * 
 */
package org.jbookshelf.model.db.dao.alter;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.util.HibernateUtil;

/**
 * @author eav 2010
 */
public class BookDAO
    extends AbstractDAO<Book>
{
    private static final Logger log = Logger.getLogger( BookDAO.class );

    public void delete(
        final Long id )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            final Object proxy = session.load( Book.class, id );
            session.delete( proxy );
            session.getTransaction().commit();
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            session.getTransaction().rollback();
            throw new Error( e );
        }
        finally
        {
            session.close();
        }
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Book> find()
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            return session.createCriteria( Book.class ).list();
        }
        finally
        {
            session.close();
        }
    }

    public Book getById(
        final Long id )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            return (Book) session.get( Book.class, id );
        }
        finally
        {
            session.close();
        }
    }

    public Book persist(
        final Book book )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            session.save( book );
            session.getTransaction().commit();

            return book;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            session.getTransaction().rollback();
            throw new Error( e );
        }
        finally
        {
            session.close();
        }
    }

    public void update(
        final Book book )
    {
        final Session session = HibernateUtil.getSession();
        try
        {
            session.beginTransaction();
            session.merge( book );
            session.getTransaction().commit();
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            session.getTransaction().rollback();
            throw new Error( e );
        }
        finally
        {
            session.close();
        }
    }
}
