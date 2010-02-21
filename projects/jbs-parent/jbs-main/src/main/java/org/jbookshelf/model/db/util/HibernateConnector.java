/**
 * 
 */
package org.jbookshelf.model.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.ConnectionProvider;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;

/**
 * @author eav 2010
 */
public class HibernateConnector
{
    private static final Logger log     = Logger.getLogger( HibernateConnector.class );
    private SessionFactory      factory;
    private ConnectionProvider  connectionProvider;
    private boolean             showSql = false;

    public Connection getConnection()
    {
        try
        {
            return connectionProvider.getConnection();
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new SQLError( e );
        }
    }

    public Session openSession()
    {
        return factory.openSession();
    }

    public boolean showSql()
    {
        return this.showSql;
    }

    public void start()
    {
        try
        {
            final Configuration cfg = new AnnotationConfiguration().configure();
            final Properties properties = cfg.getProperties();

            setupStorageDir( properties );
            cfg.addProperties( properties );

            factory = cfg.buildSessionFactory();
            connectionProvider = cfg.buildSettings().getConnectionProvider();

            showSql = "true".equalsIgnoreCase( properties.getProperty( Environment.SHOW_SQL ) );

            log.debug( "db opened" );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new HibernateException( e );
        }
    }

    public void stop()
    {
        factory.close();
        factory = null;
        connectionProvider = null;
    }

    private void setupStorageDir(
        final Properties properties )
    {
        final String dir = Single.instance( Settings.class ).JBS_DIR.getValue() + "/db";
        String url = properties.getProperty( Environment.URL );
        url = url.replaceFirst( "/opt/jbookshelf/db", dir );
        log.debug( "db storage url: " + url );
        properties.put( Environment.URL, url );
    }
}
