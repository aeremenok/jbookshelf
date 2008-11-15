/*
 * MainWindow.java Created on 15 Ноябрь 2008 г., 12:41
 */

package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSplitPane;

import org.jbookshelf.BookShelf;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.SingleFileStorage;
import org.util.settings.Settings;

/**
 * @author eav
 */
public class MainWindow
    extends javax.swing.JFrame
{
    private final ToolBar         toolBar         = new ToolBar( this );
    private final JSplitPane      splitPane       = new JSplitPane();
    private final RelatedPanel    relatedPanel    = new RelatedPanel();

    private final BookShelf       bookShelf       = JbookshelfFactory.eINSTANCE.createBookShelf();
    private final CollectionPanel collectionPanel = new CollectionPanel( bookShelf );

    private final Settings        settings        = Settings.getInstance();

    public BookShelf getBookShelf()
    {
        return bookShelf;
    }

    /** Creates new form MainWindow */
    public MainWindow()
    {
        initCustomComponents();
        initComponents();

        initModel();

        addWindowListener( new WindowAdapter()
        {
            @SuppressWarnings( "synthetic-access" )
            @Override
            public void windowClosing(
                WindowEvent e )
            {
                bookShelf.getStorage().saveCollection();
            }
        } );
    }

    private void initModel()
    {
        SingleFileStorage bookShelfStorage = JbookshelfFactory.eINSTANCE.createSingleFileStorage();
        bookShelf.setStorage( bookShelfStorage );
        bookShelfStorage.setBookShelf( bookShelf );
        bookShelfStorage.setCollectionStorageFile( settings.getCollectionFile() );
        bookShelfStorage.loadCollection();

        collectionPanel.updateTree();
    }

    private void initCustomComponents()
    {
        add( toolBar, BorderLayout.NORTH );
        add( splitPane, BorderLayout.SOUTH );
        splitPane.setLeftComponent( collectionPanel );
        splitPane.setRightComponent( relatedPanel );
        splitPane.setResizeWeight( 0.7 );
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
                new MainWindow().setVisible( true );
            }
        } );
    }

    public void collectionChanged()
    {
        collectionPanel.updateTree();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
