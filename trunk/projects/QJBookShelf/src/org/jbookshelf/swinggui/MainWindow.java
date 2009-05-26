package org.jbookshelf.swinggui;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.completion.CompletionDictionary;
import org.jbookshelf.settings.Settings;
import org.jbookshelf.swinggui.widgets.LookAndFeelComboBoxModel;
import org.jbookshelf.swinggui.widgets.panel.CollectionPanel;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QApplication;

public class MainWindow
    extends JFrame
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
                QApplication.initialize( args );

                QCoreApplication.setApplicationVersion( VERSION );
                QCoreApplication.setApplicationName( APP_NAME );

                Single.instance( MainWindow.class ).setVisible( true );
            }
        } );
    }

    @PostConstruct
    public void initSingleton()
    {
        initCollection();
        initComponents();
    }

    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        try
        {
            UIManager.setLookAndFeel( LookAndFeelComboBoxModel.fromName( evt.getNewValue().toString() ) );
            SwingUtilities.updateComponentTreeUI( this );
            final Window[] windows = getOwnedWindows();
            for ( final Window window : windows )
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

    private void initCollection()
    {
        final SingleFileStorageImpl singleFileStorageImpl = (SingleFileStorageImpl) Storage.getImpl();
        singleFileStorageImpl.setCollectionStorageFile( Single.instance( Settings.class ).getCollectionFile() );
        Storage.loadCollection();
    }

    private void initComponents()
    {
        setTitle( APP_NAME );
        setIconImage( IMG.img( "logo-64.png" ) );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( new JPanel( new BorderLayout() ) );
        add( Single.instance( ToolBar.class ), BorderLayout.NORTH );

        final JSplitPane split = new JSplitPane();
        add( split, BorderLayout.CENTER );
        split.setOneTouchExpandable( true );
        split.setLeftComponent( Single.instance( CollectionPanel.class ) );
        split.setRightComponent( new JLabel( "777" ) );

        addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosed(
                final WindowEvent e )
            {
                onClose();
            }
        } );

        pack();
        setExtendedState( MAXIMIZED_BOTH );
    }

    protected void onClose()
    {
        try
        {
            Storage.saveCollection();
            Single.instance( CompletionDictionary.class ).save();
        }
        catch ( final Throwable e1 )
        { // todo catch uncaught exceptions
            throw new Error( e1 );
        }
    }
}
