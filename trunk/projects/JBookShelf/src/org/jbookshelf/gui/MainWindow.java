package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.jbookshelf.gui.widgets.panel.CollectionPanel;
import org.jbookshelf.gui.widgets.panel.RelatedPanel;
import org.util.settings.JBookShelfSettings;
import org.util.settings.Settings;
import org.util.storage.SingleFileStorageImpl;
import org.util.storage.Storage;

/**
 * @author eav
 */
public class MainWindow
    extends JFrame
{
    private static MainWindow instance;

    private final JSplitPane  splitPane = new JSplitPane();
    private final Settings    settings  = Settings.getInstance();

    public static MainWindow getInstance()
    {
        if ( instance == null )
        {
            instance = new MainWindow();
        }
        return instance;
    }

    public static void main(
        String args[] )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                MainWindow.getInstance().setVisible( true );
            }
        } );
    }

    public MainWindow()
    {
        ((SingleFileStorageImpl) Storage.getImpl()).setCollectionStorageFile( settings.getCollectionFile() );
        Storage.loadCollection();

        try
        {
            UIManager.setLookAndFeel( Resourses.getLAFClassName( settings.getProperty( JBookShelfSettings.LAF ) ) );
            SwingUtilities.updateComponentTreeUI( this );
        }
        catch ( Exception e )
        {
            throw new Error( e );
        }

        initComponents();
    }

    private void initComponents()
    {
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        add( ToolBar.getInstance(), BorderLayout.NORTH );
        add( splitPane, BorderLayout.CENTER );

        splitPane.setLeftComponent( CollectionPanel.getInstance() );
        splitPane.setRightComponent( RelatedPanel.getInstance() );
        splitPane.setResizeWeight( 0.7 );

        addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(
                WindowEvent e )
            {
                try
                {
                    Storage.saveCollection();
                }
                catch ( Throwable e1 )
                {
                    e1.printStackTrace();
                }
            }
        } );

        pack();
    }
}
