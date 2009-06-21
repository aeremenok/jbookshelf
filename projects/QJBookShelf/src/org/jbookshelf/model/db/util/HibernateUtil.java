/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.ConnectionProvider;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;

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
            final AnnotationConfiguration cfg = new AnnotationConfiguration().configure();
            properties = cfg.getProperties();

            setupStorageDir();
            cfg.addProperties( properties );

            factory = cfg.buildSessionFactory();
            connectionProvider = cfg.buildSettings().getConnectionProvider();
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

    private static void setupStorageDir()
    {
        final String dir = Single.instance( Settings.class ).JBS_DIR.getValue() + File.separator + "db";
        String url = properties.getProperty( Environment.URL );
        url = url.replaceFirst( "/opt/jbookshelf/db", dir );
        log.debug( "db storage url: " + url );
        properties.put( Environment.URL, url );
    }
}
