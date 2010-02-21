package org.jbookshelf.view.swinggui.main;

import static org.jbookshelf.controller.singleton.Single.instance;
import icons.IMG;

import java.awt.BorderLayout;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.annotation.PostConstruct;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.additional.AdditionalPanel;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.dialog.BugReportDialog;
import org.jbookshelf.view.swinggui.widget.model.LookAndFeelComboBoxModel;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.error.ErrorInfo;
import org.jdesktop.swingx.error.ErrorReporter;
import org.xnap.commons.util.AWTExceptionHandler;

/**
 * JBookShelf main window and entry point
 * 
 * @author eav 2009
 */
public class MainWindow
    extends JXFrame
    implements
    UncaughtExceptionHandler
{
    private static final Logger log = Logger.getLogger( MainWindow.class );

    @PostConstruct
    public void initSingleton()
    {
        Thread.setDefaultUncaughtExceptionHandler( this );
        AWTExceptionHandler.install();

        setTitle( Application.INSTANCE.appName() );
        setIconImage( IMG.img( IMG.LOGO_PNG, 64 ) );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( new JPanel( new BorderLayout() ) );

        listenLAFChangeEvents();

        add( instance( ToolBar.class ), BorderLayout.NORTH );

        final JSplitPane split = new JSplitPane();
        add( split, BorderLayout.CENTER );
        split.setOneTouchExpandable( true );
        split.setLeftComponent( instance( CollectionPanel.class ) );
        split.setRightComponent( instance( AdditionalPanel.class ) );

        final JXStatusBar statusBar = new JXStatusBar();
        statusBar.add( instance( ProgressBar.class ) );
        setStatusBar( statusBar );

        pack();
        setExtendedState( MAXIMIZED_BOTH );
    }

    private void listenLAFChangeEvents()
    {
        final PropertyChangeListener listener = new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                try
                {
                    final String laf = instance( Settings.class ).LAF.getValue();
                    UIManager.setLookAndFeel( LookAndFeelComboBoxModel.fromName( laf ) );
                    if ( evt != null )
                    {
                        SwingUtilities.updateComponentTreeUI( MainWindow.this );
                        for ( final Window window : getOwnedWindows() )
                        {
                            SwingUtilities.updateComponentTreeUI( window );
                            window.pack();
                        }
                    }
                }
                catch ( final UnsupportedLookAndFeelException e )
                {
                    log.error( e, e );
                    throw new Error( e );
                }
            }
        };
        instance( Settings.class ).LAF.addPropertyChangeListener( listener );
        listener.propertyChange( null );
    }

    @Override
    public void uncaughtException(
        final Thread t,
        final Throwable e )
    {
        log.error( e, e );

        final String message = I18N.tr( "Unexpected error" );

        final ErrorInfo info = new ErrorInfo( null, message, null, null, e, null, null );
        final JXErrorPane pane = new JXErrorPane();
        pane.setErrorInfo( info );
        final JDialog errorDialog = JXErrorPane.createDialog( null, pane );

        pane.setErrorReporter( new ErrorReporter()
        {
            public void reportError(
                final ErrorInfo info1 )
                throws NullPointerException
            {
                new BugReportDialog( errorDialog, info ).setVisible( true );
            }
        } );

        errorDialog.setVisible( true );
    }
}
