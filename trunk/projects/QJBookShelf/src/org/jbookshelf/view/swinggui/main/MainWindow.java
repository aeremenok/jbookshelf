package org.jbookshelf.view.swinggui.main;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.util.HibernateUtil;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.additional.AdditionalPanel;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.widget.LookAndFeelComboBoxModel;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.error.ErrorInfo;
import org.xnap.commons.util.AWTExceptionHandler;

/**
 * JBookShelf main window and entry point
 * 
 * @author eav 2009
 */
public class MainWindow
    extends JXFrame
    implements
    PropertyChangeListener,
    UncaughtExceptionHandler
{
    public static final String APP_NAME = "JBookShelf";
    public static final String VERSION  = "0.6b2";
    private static Logger      log;
    public static String[]     args;

    public static void main(
        final String[] args )
    {
        SplashScreenManager.start();

        MainWindow.args = args;
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
        SplashScreenManager.setProgress( 10 );

        log = Logger.getLogger( MainWindow.class );
        SplashScreenManager.setProgress( 20 );

        HibernateUtil.main( args );
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

    @PostConstruct
    public void initSingleton()
    {
        Thread.setDefaultUncaughtExceptionHandler( this );
        AWTExceptionHandler.install();

        setTitle( APP_NAME );
        setIconImage( IMG.img( IMG.LOGO_PNG, 64 ) );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( new JPanel( new BorderLayout() ) );

        final Settings settings = Single.instance( Settings.class );
        settings.addPropertyChangeListener( settings.LAF.getKey(), this );
        propertyChange( null );

        add( Single.instance( ToolBar.class ), BorderLayout.NORTH );

        final JSplitPane split = new JSplitPane();
        add( split, BorderLayout.CENTER );
        split.setOneTouchExpandable( true );
        split.setLeftComponent( Single.instance( CollectionPanel.class ) );
        split.setRightComponent( Single.instance( AdditionalPanel.class ) );

        final JXStatusBar statusBar = new JXStatusBar();
        statusBar.add( Single.instance( ProgressBar.class ) );
        statusBar.setResizeHandleEnabled( false );
        setStatusBar( statusBar );

        pack();
        setExtendedState( MAXIMIZED_BOTH );

        split.setDividerLocation( 0.8 );
    }

    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        try
        {
            final String laf = Single.instance( Settings.class ).LAF.getValue();
            UIManager.setLookAndFeel( LookAndFeelComboBoxModel.fromName( laf ) );
            SwingUtilities.updateComponentTreeUI( this );
            for ( final Window window : getOwnedWindows() )
            {
                SwingUtilities.updateComponentTreeUI( window );
                window.pack();
            }
        }
        catch ( final UnsupportedLookAndFeelException e )
        {
            throw new Error( e );
        }
    }

    /**
     * restarts the app. restart can only be performed from the jar-file<br>
     * copied from {@link "http://cplusadd.blogspot.com/2009/04/java-application-and-self-restart.html"}
     * 
     * @return false if not restarted
     */
    public boolean restartApplication()
    {
        final String javaBin = System.getProperty( "java.home" ) + "/bin/java";
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

                for ( final String arg : args )
                {
                    toExec.add( arg );
                }

                Runtime.getRuntime().exec( toExec.toArray( new String[toExec.size()] ) );

                System.exit( 0 );
                // not reachable - here the app dies
            }
        }
        catch ( final Throwable e )
        {
            log.error( e, e );
        }

        // probably debugging with an IDE
        JOptionPane.showMessageDialog( null, I18N.tr( "Unable to restart automatically. Please restart manually." ) );
        return false;
    }

    @Override
    public void uncaughtException(
        final Thread t,
        final Throwable e )
    {
        log.error( e, e );
        final String message = I18N.tr( "Unexpected error" );
        final ErrorInfo info = new ErrorInfo( null, message, null, null, e, null, null );
        JXErrorPane.showDialog( null, info );
    }
}
