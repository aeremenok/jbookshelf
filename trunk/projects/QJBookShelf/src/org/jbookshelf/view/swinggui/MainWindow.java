package org.jbookshelf.view.swinggui;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.logic.JBookShelfConstants;
import org.jbookshelf.view.swinggui.widgets.LookAndFeelComboBoxModel;
import org.jbookshelf.view.swinggui.widgets.ProgressBar;
import org.jbookshelf.view.swinggui.widgets.panel.CollectionPanel;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXStatusBar;

public class MainWindow
    extends JXFrame
    implements
    JBookShelfConstants,
    PropertyChangeListener
{
    public static final String APP_NAME = "JBookShelf";
    public static final String VERSION  = "0.5b0";

    public static void main(
        final String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                //                QApplication.initialize( args );
                //
                //                QCoreApplication.setApplicationVersion( VERSION );
                //                QCoreApplication.setApplicationName( APP_NAME );

                PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
                Single.instance( MainWindow.class ).setVisible( true );
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
}
