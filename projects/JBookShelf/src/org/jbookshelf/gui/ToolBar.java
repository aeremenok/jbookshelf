/*
 * ToolBar.java Created on 15 Ноябрь 2008 г., 12:38
 */

package org.jbookshelf.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;
import org.util.FileImporter;
import org.util.settings.JBookShelfSettings;
import org.util.settings.Settings;
import org.util.storage.SingleFileStorageImpl;
import org.util.storage.Storage;

/**
 * @author eav
 */
public class ToolBar
    extends javax.swing.JPanel
{
    private static ToolBar instance;

    /**
     * Creates new form ToolBar
     * 
     * @param mainWindow
     */
    private ToolBar()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jToolBar1 = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        openButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        settingsButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        importButton = new javax.swing.JButton();
        backupButton = new javax.swing.JButton();
        restoreButton = new javax.swing.JButton();

        jToolBar1.setRollover( true );

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        addButton.setText( bundle.getString( "ToolBar.addButton.text" ) ); // NOI18N
        addButton.setFocusable( false );
        addButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        addButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        addButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                addButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( addButton );

        removeButton.setText( bundle.getString( "ToolBar.removeButton.text" ) ); // NOI18N
        removeButton.setFocusable( false );
        removeButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        removeButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        removeButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                removeButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( removeButton );

        editButton.setText( bundle.getString( "ToolBar.editButton.text" ) ); // NOI18N
        editButton.setFocusable( false );
        editButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        editButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        editButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                editButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( editButton );
        jToolBar1.add( jSeparator1 );

        openButton.setText( bundle.getString( "ToolBar.openButton.text" ) ); // NOI18N
        openButton.setFocusable( false );
        openButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        openButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        openButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                openButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( openButton );
        jToolBar1.add( jSeparator2 );

        settingsButton.setText( bundle.getString( "ToolBar.settingsButton.text" ) ); // NOI18N
        settingsButton.setFocusable( false );
        settingsButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        settingsButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        settingsButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                settingsButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( settingsButton );
        jToolBar1.add( jSeparator3 );

        importButton.setText( bundle.getString( "ToolBar.importButton.text" ) ); // NOI18N
        importButton.setFocusable( false );
        importButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        importButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        importButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                importButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( importButton );

        backupButton.setText( bundle.getString( "ToolBar.backupButton.text" ) ); // NOI18N
        backupButton.setFocusable( false );
        backupButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        backupButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        backupButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                backupButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( backupButton );

        restoreButton.setText( bundle.getString( "ToolBar.restoreButton.text" ) ); // NOI18N
        restoreButton.setFocusable( false );
        restoreButton.setHorizontalTextPosition( javax.swing.SwingConstants.CENTER );
        restoreButton.setVerticalTextPosition( javax.swing.SwingConstants.BOTTOM );
        restoreButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                restoreButtonActionPerformed( evt );
            }
        } );
        jToolBar1.add( restoreButton );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
        this.setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addComponent( jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE ) );
        layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent(
            jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE ) );
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_editButtonActionPerformed
        new BookEditDialog( MainWindow.getInstance(), true, (ReadingUnit) selectedUnique ).setVisible( true );
    }// GEN-LAST:event_editButtonActionPerformed

    private void addButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_addButtonActionPerformed
        new BookAdditionDialog( MainWindow.getInstance(), true ).setVisible( true );
    }// GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_removeButtonActionPerformed
        if ( JOptionPane.showConfirmDialog( this, "Remove " + selectedUnique.getName() + "?" ) == JOptionPane.YES_OPTION )
        {
            Storage.getBookShelf().removeUnique( selectedUnique );
            CollectionPanel.getInstance().removeSelectedItem();
            JOptionPane.showMessageDialog( this, selectedUnique.getName() + " removed" );
        }
    }// GEN-LAST:event_removeButtonActionPerformed

    private void openButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_openButtonActionPerformed
        ((ReadingUnit) selectedUnique).getPhysical().openUnit();
    }// GEN-LAST:event_openButtonActionPerformed

    private void settingsButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_settingsButtonActionPerformed
        new SettingsDialog( MainWindow.getInstance(), true ).setVisible( true );
    }// GEN-LAST:event_settingsButtonActionPerformed

    private void importButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_importButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled( false );
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            FileImporter importer = new FileImporter()
            {
                @Override
                protected void onImportFailure(
                    File file,
                    Exception e )
                {
                    // todo
                    System.out.println( "-cannot import file " + file.getAbsolutePath() + " cause:" + e.getMessage() );
                    // e.printStackTrace();
                }

                @Override
                protected void onImportSuccess(
                    ReadingUnit book )
                {
                    // todo
                    System.out.println( "+imported " + book.getAuthors().get( 0 ).getName() + ". " + book.getName() );
                }
            };

            String mask = Settings.getInstance().getProperty( JBookShelfSettings.IMPORT_MASK );
            String result = JOptionPane.showInputDialog( "Enter import mask", mask ); // todo message
            if ( result != null && !result.equals( "" ) )
            {
                mask = result;
            }
            importer.importFiles( chooser.getSelectedFile().listFiles(), mask, Storage.getBookShelf() );

            CollectionPanel.getInstance().updateTree();
        }
    }// GEN-LAST:event_importButtonActionPerformed

    private void backupButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_backupButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled( false );
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );

        SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        chooser.setSelectedFile( storage.getCollectionStorageFile() );

        if ( chooser.showSaveDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            Storage.backupCollection( chooser.getSelectedFile() );
        }
    }// GEN-LAST:event_backupButtonActionPerformed

    private void restoreButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_restoreButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled( false );
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );

        SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        chooser.setSelectedFile( storage.getCollectionStorageFile() );

        if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            Storage.restoreCollection( chooser.getSelectedFile() );
            CollectionPanel.getInstance().updateTree();
        }
    }// GEN-LAST:event_restoreButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton            addButton;
    private javax.swing.JButton            backupButton;
    private javax.swing.JButton            editButton;
    private javax.swing.JButton            importButton;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar           jToolBar1;
    private javax.swing.JButton            openButton;
    private javax.swing.JButton            removeButton;
    private javax.swing.JButton            restoreButton;
    private javax.swing.JButton            settingsButton;

    // End of variables declaration//GEN-END:variables

    public static ToolBar getInstance()
    {
        if ( instance == null )
        {
            instance = new ToolBar();
        }
        return instance;
    }

    public void nothingSelected()
    {
        removeButton.setEnabled( false );
        openButton.setEnabled( false );
        editButton.setEnabled( false );
    }

    private Unique selectedUnique;

    public void selectedUnique(
        Unique unique )
    {
        this.selectedUnique = unique;
        removeButton.setEnabled( true );
        if ( selectedUnique instanceof ReadingUnit )
        {
            openButton.setEnabled( true );
            editButton.setEnabled( true );
        }
    }
}
