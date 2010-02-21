/**
 * 
 */
package org.jbookshelf.view.swinggui.main;

import static org.jbookshelf.controller.singleton.Single.instance;

import java.awt.EventQueue;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.JBSSystem;
import org.jbookshelf.model.db.util.HibernateConnector;
import org.jbookshelf.view.i18n.I18N;

/**
 * @author eav 2010
 */
public class Application
{
    public static final Application INSTANCE = new Application();

    public static void main(
        final String[] args )
    {
        INSTANCE.start();
    }

    public String appName()
    {
        return "JBookShelf";
    }

    public String appVersion()
    {
        return "0.7b1";
    }

    public void start()
    {
        SplashScreenManager.start();

        PropertyConfigurator.configure( Application.class.getResource( "log4j.properties" ) );
        SplashScreenManager.setProgress( 10 );

        SplashScreenManager.setProgress( 20 );

        instance( HibernateConnector.class ).start();
        SplashScreenManager.setProgress( 75 );

        final MainWindow instance = Single.instance( MainWindow.class );
        SplashScreenManager.setProgress( 99 );
        SplashScreenManager.stop();

        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                instance.setVisible( true );
            }
        } );
    }

    public void stop()
    {
    //        instance( HibernateConnector.class ).stop();
    }

    /**
     * restarts the app. restart can only be performed from the jar-file<br>
     * copied from {@link "http://cplusadd.blogspot.com/2009/04/java-application-and-self-restart.html"}
     * 
     * @return false if not restarted
     */
    public boolean restart()
    {
        final String javaBin = instance( JBSSystem.class ).javaHome() + "/bin/java";
        try
        {
            final URI uri = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
            final File jarFile = new File( uri );
            if ( jarFile.getName().endsWith( ".jar" ) )
            { // not debugging with an IDE
                final List<String> toExec = new ArrayList<String>();
                toExec.add( javaBin );
                toExec.add( "-jar" );
                toExec.add( jarFile.getPath() );

                Runtime.getRuntime().exec( toExec.toArray( new String[toExec.size()] ) );

                instance( JBSSystem.class ).exit( 0 );
                // not reachable - here the app dies
            }
        }
        catch ( final Throwable e )
        {
            Logger.getLogger( getClass() ).error( e, e );
        }

        // probably debugging with an IDE
        JOptionPane.showMessageDialog( null, I18N.tr( "Unable to restart automatically. Please restart manually." ) );
        return false;
    }
}
