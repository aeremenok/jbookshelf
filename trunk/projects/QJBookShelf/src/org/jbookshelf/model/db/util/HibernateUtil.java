/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.connection.ConnectionProvider;

/**
 * @author eav
 */
public class HibernateUtil
{
    private static final SessionFactory     factory;
    private static final Properties         properties;
    private static final Logger             log = Logger.getLogger( HibernateUtil.class );
    private static final ConnectionProvider connectionProvider;

    static
    {
        try
        {
            final AnnotationConfiguration cfg = new AnnotationConfiguration();
            final AnnotationConfiguration configure = cfg.configure();
            properties = configure.getProperties();
            factory = configure.buildSessionFactory();
            connectionProvider = configure.buildSettings().getConnectionProvider();
        }
        catch ( final Exception e )
        {
            throw new ExceptionInInitializerError( e );
        }
    }

    public static Connection connection()
    {
        try
        {
            return connectionProvider.getConnection();
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
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
        log.debug( "db initialized" );
    }
}