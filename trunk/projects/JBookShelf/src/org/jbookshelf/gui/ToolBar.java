/*
 * ToolBar.java Created on 15 Ноябрь 2008 г., 12:38
 */

package org.jbookshelf.gui;

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;
import org.jbookshelf.gui.logic.UniqueSelectionListener;
import org.util.FileImporter;
import org.util.settings.JBookShelfSettings;
import org.util.settings.Settings;
import org.util.storage.SingleFileStorageImpl;
import org.util.storage.Storage;

/**
 * @author eav
 */
public class ToolBar
    extends JPanel
    implements
        UniqueSelectionListener
{
    private static ToolBar     instance;

    private JButton            aboutButton;
    private JButton            addButton;
    private JButton            backupButton;
    private JButton            editButton;
    private JButton            importButton;
    private JButton            openButton;
    private JButton            removeButton;
    private JButton            restoreButton;
    private JButton            settingsButton;

    private JToolBar.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JToolBar.Separator jSeparator3;
    private JToolBar.Separator jSeparator4;

    private JToolBar           toolBar;

    private Unique             selectedUnique;

    public static ToolBar getInstance()
    {
        if ( instance == null )
        {
            instance = new ToolBar();
        }
        return instance;
    }

    private ToolBar()
    {
        initComponents();
        initListeners();
    }

    public void nothingSelected()
    {
        removeButton.setEnabled( false );
        openButton.setEnabled( false );
        editButton.setEnabled( false );
    }

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

    private void backupButtonActionPerformed()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled( false );
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );

        SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        chooser.setSelectedFile( storage.getCollectionStorageFile() );

        if ( chooser.showSaveDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            Storage.backupCollection( chooser.getSelectedFile() );
        }
    }

    private void importButtonActionPerformed()
    {
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
    }

    private void initComponents()
    {
        toolBar = new JToolBar();
        addButton = new JButton();
        removeButton = new JButton();
        editButton = new JButton();
        jSeparator1 = new JToolBar.Separator();
        openButton = new JButton();
        jSeparator2 = new JToolBar.Separator();
        settingsButton = new JButton();
        jSeparator3 = new JToolBar.Separator();
        importButton = new JButton();
        backupButton = new JButton();
        restoreButton = new JButton();
        jSeparator4 = new JToolBar.Separator();
        aboutButton = new JButton();

        toolBar.setRollover( true );

        addButton.setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/list-add.png" ) ) ); // NOI18N
        ResourceBundle bundle = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        addButton.setText( bundle.getString( "ToolBar.addButton.text" ) ); // NOI18N
        addButton.setFocusable( false );
        int center = SwingConstants.CENTER;
        addButton.setHorizontalTextPosition( center );
        int bottom = SwingConstants.BOTTOM;
        addButton.setVerticalTextPosition( bottom );

        toolBar.add( addButton );

        removeButton.setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/list-remove.png" ) ) ); // NOI18N
        removeButton.setText( bundle.getString( "ToolBar.removeButton.text" ) ); // NOI18N
        removeButton.setFocusable( false );
        removeButton.setHorizontalTextPosition( center );
        removeButton.setVerticalTextPosition( bottom );

        toolBar.add( removeButton );

        editButton.setIcon( new ImageIcon( getClass()
            .getResource( "/org/jbookshelf/gui/images/document-properties.png" ) ) ); // NOI18N
        editButton.setText( bundle.getString( "ToolBar.editButton.text" ) ); // NOI18N
        editButton.setFocusable( false );
        editButton.setHorizontalTextPosition( center );
        editButton.setVerticalTextPosition( bottom );

        toolBar.add( editButton );
        toolBar.add( jSeparator1 );

        openButton
            .setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/document-preview.png" ) ) ); // NOI18N
        openButton.setText( bundle.getString( "ToolBar.openButton.text" ) ); // NOI18N
        openButton.setFocusable( false );
        openButton.setHorizontalTextPosition( center );
        openButton.setVerticalTextPosition( bottom );

        toolBar.add( openButton );
        toolBar.add( jSeparator2 );

        settingsButton.setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/configure.png" ) ) ); // NOI18N
        settingsButton.setText( bundle.getString( "ToolBar.settingsButton.text" ) ); // NOI18N
        settingsButton.setFocusable( false );
        settingsButton.setHorizontalTextPosition( center );
        settingsButton.setVerticalTextPosition( bottom );

        toolBar.add( settingsButton );
        toolBar.add( jSeparator3 );

        importButton
            .setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/document-import.png" ) ) ); // NOI18N
        importButton.setText( bundle.getString( "ToolBar.importButton.text" ) ); // NOI18N
        importButton.setFocusable( false );
        importButton.setHorizontalTextPosition( center );
        importButton.setVerticalTextPosition( bottom );

        toolBar.add( importButton );

        backupButton
            .setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/document-save-as.png" ) ) ); // NOI18N
        backupButton.setText( bundle.getString( "ToolBar.backupButton.text" ) ); // NOI18N
        backupButton.setFocusable( false );
        backupButton.setHorizontalTextPosition( center );
        backupButton.setVerticalTextPosition( bottom );

        toolBar.add( backupButton );

        restoreButton
            .setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/document-open.png" ) ) ); // NOI18N
        restoreButton.setText( bundle.getString( "ToolBar.restoreButton.text" ) ); // NOI18N
        restoreButton.setFocusable( false );
        restoreButton.setHorizontalTextPosition( center );
        restoreButton.setVerticalTextPosition( bottom );

        toolBar.add( restoreButton );
        toolBar.add( jSeparator4 );

        aboutButton.setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/help-about.png" ) ) ); // NOI18N
        aboutButton.setText( bundle.getString( "ToolBar.aboutButton.text" ) ); // NOI18N
        aboutButton.setFocusable( false );
        aboutButton.setHorizontalTextPosition( center );
        aboutButton.setVerticalTextPosition( bottom );
        toolBar.add( aboutButton );

        GroupLayout layout = new GroupLayout( this );
        this.setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent( toolBar,
            GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE ) );
        layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent( toolBar,
            GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE ) );
    }

    private void initListeners()
    {
        backupButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                backupButtonActionPerformed();
            }
        } );

        aboutButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                new AboutDialog( MainWindow.getInstance(), true ).setVisible( true );
            }
        } );

        restoreButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                restoreButtonActionPerformed();
            }
        } );
        importButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                importButtonActionPerformed();
            }
        } );
        openButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                ((ReadingUnit) selectedUnique).getPhysical().openUnit();
            }
        } );

        editButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                new BookEditDialog( MainWindow.getInstance(), true, (ReadingUnit) selectedUnique ).setVisible( true );
            }
        } );
        removeButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                removeButtonActionPerformed();
            }
        } );
        addButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                new BookAdditionDialog( MainWindow.getInstance(), true ).setVisible( true );
            }
        } );
        settingsButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                new SettingsDialog( MainWindow.getInstance(), true ).setVisible( true );
            }
        } );
    }

    private void removeButtonActionPerformed()
    {
        if ( JOptionPane.showConfirmDialog( this, "Remove " + selectedUnique.getName() + "?" ) == JOptionPane.YES_OPTION )
        {
            Storage.getBookShelf().removeUnique( selectedUnique );
            CollectionPanel.getInstance().removeSelectedItem();
            JOptionPane.showMessageDialog( this, selectedUnique.getName() + " removed" );
        }
    }

    private void restoreButtonActionPerformed()
    {
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
    }
}
