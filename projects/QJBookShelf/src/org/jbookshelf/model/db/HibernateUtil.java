/**
 * 
 */
package org.jbookshelf.model.db;

import org.apache.log4j.PropertyConfigurator;
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
            PropertyConfigurator.configure( HibernateUtil.class.getResource( "log4j.properties" ) );

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
        System.out.println( "HibernateUtil.main()" );
    }
}
