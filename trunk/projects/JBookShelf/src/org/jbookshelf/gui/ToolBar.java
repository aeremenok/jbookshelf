/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.gui.logic.UniqueSelectionListener;
import org.jbookshelf.gui.widgets.dialog.AboutDialog;
import org.jbookshelf.gui.widgets.dialog.BookAdditionDialog;
import org.jbookshelf.gui.widgets.dialog.BookEditDialog;
import org.jbookshelf.gui.widgets.dialog.SettingsDialog;
import org.jbookshelf.gui.widgets.panel.CollectionPanel;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.Unique;

/**
 * @author eav
 */
public class ToolBar
    extends JPanel
    implements
        UniqueSelectionListener
{
    private static final ToolBar instance       = new ToolBar();

    private final JButton        aboutButton    = new JButton();
    private final JButton        addButton      = new JButton();
    private final JButton        backupButton   = new JButton();
    private final JButton        editButton     = new JButton();
    private final JButton        importButton   = new JButton();
    private final JButton        openButton     = new JButton();
    private final JButton        removeButton   = new JButton();
    private final JButton        restoreButton  = new JButton();
    private final JButton        settingsButton = new JButton();

    private final JToolBar       toolBar        = new JToolBar();

    private Unique               selectedUnique;

    public static ToolBar getInstance()
    {
        return instance;
    }

    private ToolBar()
    {
        super( new BorderLayout() );
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

    private void addButton(
        JButton button,
        String icon,
        String text )
    {
        button.setName( text );
        Resourses.register( getClass(), button );

        button.setIcon( Resourses.createIcon( icon ) );

        button.setFocusable( false );
        button.setHorizontalTextPosition( SwingConstants.CENTER );
        button.setVerticalTextPosition( SwingConstants.BOTTOM );

        toolBar.add( button );
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
        chooser.setLocale( Resourses.getCurrentLocale() );
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
                importer.importFiles( chooser.getSelectedFile().listFiles(), mask, Storage.getBookShelf() );
            }

            CollectionPanel.getInstance().updateTree();
        }
    }

    private void initComponents()
    {
        add( toolBar, BorderLayout.WEST );
        toolBar.setRollover( true );

        addButton( addButton, "list-add.png", "addButton" );
        addButton( removeButton, "list-remove.png", "removeButton" );
        addButton( editButton, "document-properties.png", "editButton" );

        toolBar.add( new JToolBar.Separator() );

        addButton( openButton, "document-preview.png", "openButton" );

        toolBar.add( new JToolBar.Separator() );

        addButton( settingsButton, "configure.png", "settingsButton" );

        toolBar.add( new JToolBar.Separator() );

        addButton( importButton, "document-import.png", "importButton" );
        addButton( backupButton, "document-save-as.png", "backupButton" );
        addButton( restoreButton, "document-open.png", "restoreButton" );

        toolBar.add( new JToolBar.Separator() );

        addButton( aboutButton, "help-about.png", "aboutButton" );
    }

    private void initListeners()
    {
        backupButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                backupButtonActionPerformed();
            }
        } );

        aboutButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                new AboutDialog( MainWindow.getInstance(), true ).setVisible( true );
            }
        } );

        restoreButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                restoreButtonActionPerformed();
            }
        } );
        importButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                importButtonActionPerformed();
            }
        } );
        openButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                ((ReadingUnit) selectedUnique).getPhysical().openUnit();
            }
        } );

        editButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                new BookEditDialog( MainWindow.getInstance(), true, (ReadingUnit) selectedUnique ).setVisible( true );
            }
        } );
        removeButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                removeButtonActionPerformed();
            }
        } );
        addButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                new BookAdditionDialog( MainWindow.getInstance(), true ).setVisible( true );
            }
        } );
        settingsButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
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
