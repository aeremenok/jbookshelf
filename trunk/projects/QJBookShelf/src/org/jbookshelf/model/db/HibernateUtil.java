/**
 * 
 */
package org.jbookshelf.model.db;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.event.PersistEventListener;

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
            cfg.getEventListeners().setPersistEventListeners( new PersistEventListener[]
            { new Timestamper() } );
            factory = cfg.configure().buildSessionFactory();
        }
        catch ( final Exception e )
        {
            throw new ExceptionInInitializerError( e );
        }
    }

    @SuppressWarnings( "deprecation" )
    public static Connection connection()
    {
        return getSession().connection();
    }

    public static Session getSession()
    {
        return factory.openSession();
    }

    public static void main(
        final String[] args )
    {
        System.out.println( "HibernateUtil.main()" );
    }
}
