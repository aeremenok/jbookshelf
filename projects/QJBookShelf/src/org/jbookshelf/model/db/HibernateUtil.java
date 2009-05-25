/**
 * 
 */
package org.jbookshelf.model.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.event.SaveOrUpdateEventListener;

/**
 * @author eav
 */
public class HibernateUtil
{
    private static final SessionFactory factory;
    static
    {
        try
        {
            final AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.getEventListeners().setSaveOrUpdateEventListeners( new SaveOrUpdateEventListener[]
            { new Timestamper() } );
            factory = cfg.configure().buildSessionFactory();
        }
        catch ( final Exception e )
        {
            throw new ExceptionInInitializerError( e );
        }
    }

    public static Session getSession()
    {
        return factory.openSession();
    }

    public static void main(
        final String[] args )
    {
        final Session session = getSession();
        try
        {
            session.beginTransaction();
            final Book book = new Book();
            book.setName( "test" );
            final PhysicalBook physicalBook = new PhysicalBook();
            physicalBook.setFileName( "testFN" );
            book.setPhysicalBook( physicalBook );
            session.saveOrUpdate( book );
            session.saveOrUpdate( physicalBook );
            session.getTransaction().commit();

            System.out.println( book.getName() );
        }
        catch ( final Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

    }
}
