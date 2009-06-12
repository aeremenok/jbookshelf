package org.jbookshelf.view.swinggui;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.HibernateUtil;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.JBookShelfConstants;
import org.jbookshelf.view.swinggui.widgets.LookAndFeelComboBoxModel;
import org.jbookshelf.view.swinggui.widgets.ProgressBar;
import org.jbookshelf.view.swinggui.widgets.panel.CollectionPanel;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.error.ErrorInfo;

public class MainWindow
    extends JXFrame
    implements
    JBookShelfConstants,
    PropertyChangeListener,
    UncaughtExceptionHandler
{
    public static final String APP_NAME = "JBookShelf";
    public static final String VERSION  = "0.5b0";
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
        SplashScreenManager.setProgress( 25 );

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
        setTitle( APP_NAME );
        setIconImage( IMG.img( "logo-64.png" ) );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        Single.instance( Settings.class );

        setContentPane( new JPanel( new BorderLayout() ) );
        add( Single.instance( ToolBar.class ), BorderLayout.NORTH );

        final JSplitPane split = new JSplitPane();
        add( split, BorderLayout.CENTER );
        split.setOneTouchExpandable( true );
        split.setLeftComponent( Single.instance( CollectionPanel.class ) );
        split.setRightComponent( new JLabel( "todo" ) );

        final JXStatusBar statusBar = new JXStatusBar();
        statusBar.add( Single.instance( ProgressBar.class ) );
        statusBar.setResizeHandleEnabled( false );
        setStatusBar( statusBar );

        pack();
        setExtendedState( MAXIMIZED_BOTH );

        Thread.setDefaultUncaughtExceptionHandler( this );
    }

    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        try
        {
            UIManager.setLookAndFeel( LookAndFeelComboBoxModel.fromName( evt.getNewValue().toString() ) );
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

    @Override
    public void uncaughtException(
        final Thread t,
        final Throwable e )
    {
        log.error( e, e );
        final ErrorInfo info = new ErrorInfo( null, I18N.tr( "Unexpected error" ), null, null, e, null, null );
        JXErrorPane.showDialog( null, info );
    }
}
