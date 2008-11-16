/*
 * MainWindow.java Created on 15 Ноябрь 2008 г., 12:41
 */

package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSplitPane;

import org.util.settings.Settings;
import org.util.storage.SingleFileStorageImpl;
import org.util.storage.Storage;

/**
 * @author eav
 */
public class MainWindow
    extends javax.swing.JFrame
{
    private static MainWindow instance;

    public static MainWindow getInstance()
    {
        if ( instance == null )
        {
            instance = new MainWindow();
        }
        return instance;
    }

    private final JSplitPane   splitPane    = new JSplitPane();
    private final RelatedPanel relatedPanel = new RelatedPanel();

    private final Settings     settings     = Settings.getInstance();

    /** Creates new form MainWindow */
    public MainWindow()
    {
        initComponents();

        ((SingleFileStorageImpl) Storage.getImpl()).setCollectionStorageFile( settings.getCollectionFile() );
        Storage.loadCollection();

        initCustomComponents();
    }

    private void initCustomComponents()
    {
        add( ToolBar.getInstance(), BorderLayout.NORTH );
        add( splitPane, BorderLayout.SOUTH );

        splitPane.setLeftComponent( CollectionPanel.getInstance() );
        splitPane.setRightComponent( relatedPanel );
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

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(
        String args[] )
    {
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                MainWindow.getInstance().setVisible( true );
            }
        } );
    }

    public void collectionChanged()
    {
        CollectionPanel.getInstance().updateTree();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
