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
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.ConnectionProvider;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;

/**
 * loads the database using hibernate
 * 
 * @author eav
 */
public class HibernateUtil
{
    private static final Logger       log = Logger.getLogger( HibernateUtil.class );

    private static SessionFactory     factory;
    private static Properties         properties;
    private static ConnectionProvider connectionProvider;

    private static Session            singleSession;

    public static void close()
    {
        if ( singleSession != null )
        {
            singleSession.close();
            singleSession = null;
        }
        factory.close();
        connectionProvider.close();
        log.debug( "db closed" );
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

    public static Session getCurrentSession()
    {
        return factory.getCurrentSession();
    }

    public static Properties getProperties()
    {
        return properties;
    }

    public static Session getSession()
    {
        return factory.openSession();
    }

    public static void open(
        final File dbDir )
    {
        try
        {
            final Configuration cfg = new AnnotationConfiguration().configure();
            properties = cfg.getProperties();

            setupStorageDir( dbDir );
            cfg.addProperties( properties );

            factory = cfg.buildSessionFactory();
            connectionProvider = cfg.buildSettings().getConnectionProvider();

            log.debug( "db opened" );
        }
        catch ( final Exception e )
        {
            throw new ExceptionInInitializerError( e );
        }
    }

    public static Session singleSession()
    {
        if ( singleSession == null )
        {
            singleSession = getSession();
        }
        return singleSession;
    }

    private static void setupStorageDir(
        final File dbDir )
    {
        final String dir;
        if ( dbDir != null )
        {
            dir = dbDir.getAbsolutePath();
        }
        else
        {
            dir = Single.instance( Settings.class ).JBS_DIR.getValue() + "/db";
        }
        String url = properties.getProperty( Environment.URL );
        url = url.replaceFirst( "/opt/jbookshelf/db", dir );
        log.debug( "db storage url: " + url );
        properties.put( Environment.URL, url );
    }
}
