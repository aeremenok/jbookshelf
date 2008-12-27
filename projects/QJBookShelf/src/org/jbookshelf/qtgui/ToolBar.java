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
package org.jbookshelf.qtgui;

import java.io.File;

import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.dialog.AboutDialog;
import org.jbookshelf.qtgui.widgets.dialog.BookAdditionDialog;
import org.jbookshelf.qtgui.widgets.dialog.BookEditDialog;
import org.jbookshelf.qtgui.widgets.dialog.SettingsDialog;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QInputDialog;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QToolBar;
import com.trolltech.qt.gui.QFileDialog.FileMode;
import com.trolltech.qt.gui.QLineEdit.EchoMode;
import com.trolltech.qt.gui.QMessageBox.StandardButton;

/**
 * @author eav
 */
public class ToolBar
    extends QToolBar
    implements
        JBookShelfConstants,
        UniqueSelectionListener
{
    private static final ToolBar instance = new ToolBar();

    private Unique               selectedUnique;

    private QAction              addAction;
    private QAction              removeAction;
    private QAction              editAction;
    private QAction              openAction;
    private QAction              settingsAction;
    private QAction              aboutAction;
    private QAction              restoreAction;
    private QAction              backupAction;
    private QAction              importAction;

    public static ToolBar getInstance()
    {
        return instance;
    }

    public ToolBar()
    {
        createActions();
        connectActions();
    }

    public void nothingSelected()
    {
        removeAction.setEnabled( false );
        openAction.setEnabled( false );
        editAction.setEnabled( false );
    }

    public void selectedUnique(
        Unique unique )
    {
        this.selectedUnique = unique;
        removeAction.setEnabled( true );
        if ( selectedUnique instanceof ReadingUnit )
        {
            openAction.setEnabled( true );
            editAction.setEnabled( true );
        }
    }

    private void connectActions()
    {
        addAction.triggered.connect( this, "onAdd()" );
        removeAction.triggered.connect( this, "onRemove()" );
        editAction.triggered.connect( this, "onEdit()" );

        openAction.triggered.connect( this, "onOpen()" );

        settingsAction.triggered.connect( this, "onSettings()" );

        importAction.triggered.connect( this, "onImport()" );
        backupAction.triggered.connect( this, "onBackup()" );
        restoreAction.triggered.connect( this, "onRestore()" );

        aboutAction.triggered.connect( this, "onAbout()" );
    }

    private void createActions()
    {
        addAction = addAction( new QIcon( ICONPATH + "list-add.png" ), tr( "&Add" ) );
        removeAction = addAction( new QIcon( ICONPATH + "list-remove.png" ), tr( "&Remove" ) );
        editAction = addAction( new QIcon( ICONPATH + "document-properties.png" ), tr( "&Edit" ) );

        addSeparator();

        openAction = addAction( new QIcon( ICONPATH + "document-preview.png" ), tr( "&Open" ) );

        addSeparator();

        settingsAction = addAction( new QIcon( ICONPATH + "configure.png" ), tr( "&Settings" ) );

        addSeparator();

        importAction = addAction( new QIcon( ICONPATH + "document-import.png" ), tr( "&Import" ) );
        backupAction = addAction( new QIcon( ICONPATH + "document-save-as.png" ), tr( "&Backup" ) );
        restoreAction = addAction( new QIcon( ICONPATH + "document-open.png" ), tr( "&Restore" ) );

        addSeparator();

        aboutAction = addAction( new QIcon( ICONPATH + "help-about.png" ), tr( "&About" ) );
    }

    @SuppressWarnings( "unused" )
    private void onAbout()
    {
        new AboutDialog( MainWindow.getInstance() ).setVisible( true );
    }

    @SuppressWarnings( "unused" )
    private void onAdd()
    {
        new BookAdditionDialog( MainWindow.getInstance() ).setVisible( true );
    }

    @SuppressWarnings( "unused" )
    private void onBackup()
    {
        FileDialog fileDialog = new FileDialog( this, tr( "Select backup file" ) )
        {
            @Override
            protected void filesSelected()
            {
                Storage.backupCollection( getSelectedFile() );
            }
        };

        SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        fileDialog.setSelectedFile( storage.getCollectionStorageFile() );

        fileDialog.show();
    }

    @SuppressWarnings( "unused" )
    private void onEdit()
    {
        new BookEditDialog( MainWindow.getInstance(), (ReadingUnit) selectedUnique ).setVisible( true );
    }

    @SuppressWarnings( "unused" )
    private void onImport()
    {
        FileDialog fileDialog = new FileDialog( this, tr( "Select a directory to import" ) )
        {
            @Override
            protected void filesSelected()
            {
                FileImporter importer = new FileImporter()
                {
                    @Override
                    protected void onImportFailure(
                        File file,
                        Exception e )
                    {
                        // todo
                        System.out.println( "- cannot import file " + file.getAbsolutePath() + " cause:" +
                            e.getMessage() );
                        // e.printStackTrace();
                    }

                    @Override
                    protected void onImportSuccess(
                        ReadingUnit book )
                    {
                        // todo
                        System.out.println( "+ imported " + book.getAuthors().get( 0 ).getName() + ". " +
                            book.getName() );
                    }
                };

                String mask = Settings.getInstance().getProperty( JBookShelfSettings.IMPORT_MASK );
                String result =
                    QInputDialog.getText( this, tr( "Enter" ), tr( "Enter import mask" ), EchoMode.Normal, mask );
                if ( result != null && !result.equals( "" ) )
                {
                    mask = result;
                    importer.importFiles( getSelectedFileArray(), mask, Storage.getBookShelf() );
                }

                CollectionPanel.getInstance().updateTree();
            }
        };

        fileDialog.setFileMode( FileMode.DirectoryOnly );
        fileDialog.show();
    }

    @SuppressWarnings( "unused" )
    private void onOpen()
    {
        ((ReadingUnit) selectedUnique).getPhysical().openUnit();
    }

    @SuppressWarnings( "unused" )
    private void onRemove()
    {
        String title = tr( "Confirm" );
        String message = tr( "Remove" ) + " " + selectedUnique.getName() + "?";
        if ( QMessageBox.question( this, title, message, StandardButton.Yes, StandardButton.No ) == 0 )
        {
            Storage.getBookShelf().removeUnique( selectedUnique );
            CollectionPanel.getInstance().removeSelectedItem();
            QMessageBox.information( this, tr( "Removed" ), selectedUnique.getName() + " " + tr( "removed" ) );
        }
    }

    @SuppressWarnings( "unused" )
    private void onRestore()
    {
        FileDialog fileDialog = new FileDialog( this, tr( "Select backup file" ) )
        {
            @Override
            protected void filesSelected()
            {
                Storage.restoreCollection( getSelectedFile() );
                CollectionPanel.getInstance().updateTree();
            }
        };

        SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        fileDialog.setSelectedFile( storage.getCollectionStorageFile() );

        fileDialog.show();
    }

    @SuppressWarnings( "unused" )
    private void onSettings()
    {
        new SettingsDialog( MainWindow.getInstance() ).setVisible( true );
    }
}
