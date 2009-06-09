/**
 * 
 */
package org.jbookshelf.model.db;

import java.sql.Connection;
import java.util.Properties;

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
    private static final Properties     properties;
    static
    {
        try
        {
            final AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.getEventListeners().setPersistEventListeners( new PersistEventListener[]
            { new Timestamper() } );
            final AnnotationConfiguration configure = cfg.configure();
            properties = configure.getProperties();
            factory = configure.buildSessionFactory();
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

    /**
     * @return the properties
     */
    public static Properties getProperties()
    {
        return properties;
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
