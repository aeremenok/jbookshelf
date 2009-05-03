package org.jbookshelf.swinggui;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jbookshelf.controller.Settings;
import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.completion.CompletionDictionary;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QApplication;

public class MainWindow
    extends JFrame
    implements
        Singleton,
        JBookShelfConstants
{
    public static void main(
        final String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                QApplication.initialize( args );

                QCoreApplication.setApplicationVersion( "0.4b2" );
                QCoreApplication.setApplicationName( "JBookShelf" );

                Singletons.instance( MainWindow.class ).setVisible( true );
            }
        } );
    }

    public void initSingleton()
    {
        initCollection();
        initLAF();
        initLanguage();
        initComponents();
    }

    private void initCollection()
    {
        final SingleFileStorageImpl singleFileStorageImpl = (SingleFileStorageImpl) Storage.getImpl();
        singleFileStorageImpl.setCollectionStorageFile( Singletons.instance( Settings.class ).getCollectionFile() );
        Storage.loadCollection();
    }

    private void initComponents()
    {
        setTitle( "JBookShelf" );
        setIconImage( IMG.img( "logo-64.png" ) );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setDefaultLookAndFeelDecorated( true );

        setContentPane( new JPanel( new BorderLayout() ) );
        add( Singletons.instance( ToolBar.class ), BorderLayout.NORTH );

        final JSplitPane split = new JSplitPane();
        add( split, BorderLayout.CENTER );
        split.setOneTouchExpandable( true );
        split.setLeftComponent( new JLabel( "777" ) );
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

        split.setDividerLocation( 0.5 );
    }

    private void initLAF()
    {
        try
        {
            final Settings settings = Singletons.instance( Settings.class );
            UIManager.setLookAndFeel( settings.LAF.getValue() );
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        }
        catch ( final UnsupportedLookAndFeelException e )
        {
            throw new Error( e );
        }
    }

    private void initLanguage()
    {
        Translator.retranslate( Singletons.instance( Settings.class ).LANGUAGE.getValue() );
        I18N.setLanguage( Singletons.instance( Settings.class ).LANGUAGE.getValue() );
    }

    protected void onClose()
    {
        try
        {
            Storage.saveCollection();
            Singletons.instance( CompletionDictionary.class ).save();
        }
        catch ( final Throwable e1 )
        { // todo catch uncaught exceptions
            throw new Error( e1 );
        }
    }
}
