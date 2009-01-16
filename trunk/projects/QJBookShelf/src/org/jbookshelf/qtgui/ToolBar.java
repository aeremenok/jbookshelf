/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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

import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.dialog.AboutDialog;
import org.jbookshelf.qtgui.widgets.dialog.BookAdditionDialog;
import org.jbookshelf.qtgui.widgets.dialog.BookEditDialog;
import org.jbookshelf.qtgui.widgets.dialog.ImportDialog;
import org.jbookshelf.qtgui.widgets.dialog.SettingsDialog;
import org.jbookshelf.qtgui.widgets.ext.QFileDialogExt;
import org.jbookshelf.qtgui.widgets.ext.QToolBarExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QMessageBox.StandardButton;
import com.trolltech.qt.gui.QMessageBox.StandardButtons;

/**
 * Contains the main actions of the book collection
 * 
 * @author eav
 */
public class ToolBar
    extends QToolBarExt
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

        Translator.addTranslatable( this );
    }

    public QAction getEditAction()
    {
        return editAction;
    }

    public QAction getOpenAction()
    {
        return openAction;
    }

    public QAction getRemoveAction()
    {
        return removeAction;
    }

    public void nothingSelected()
    {
        removeAction.setEnabled( false );
        openAction.setEnabled( false );
        editAction.setEnabled( false );
    }

    public void retranslate()
    {
        addAction.setText( tr( "&Add" ) );
        removeAction.setText( tr( "&Remove" ) );
        editAction.setText( tr( "&Edit" ) );

        openAction.setText( tr( "&Open" ) );

        settingsAction.setText( tr( "&Settings" ) );

        importAction.setText( tr( "&Import" ) );
        backupAction.setText( tr( "&Backup" ) );
        restoreAction.setText( tr( "&Restore" ) );

        aboutAction.setText( tr( "&About" ) );
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
        addAction = addAction( new QIcon( ICONPATH + "list-add.png" ), "" );
        removeAction = addAction( new QIcon( ICONPATH + "list-remove.png" ), "" );
        editAction = addAction( new QIcon( ICONPATH + "document-properties.png" ), "" );

        addSeparator();

        openAction = addAction( new QIcon( ICONPATH + "document-preview.png" ), "" );

        addSeparator();

        settingsAction = addAction( new QIcon( ICONPATH + "configure.png" ), "" );

        addSeparator();

        importAction = addAction( new QIcon( ICONPATH + "document-import.png" ), "" );
        backupAction = addAction( new QIcon( ICONPATH + "document-save-as.png" ), "" );
        restoreAction = addAction( new QIcon( ICONPATH + "document-open.png" ), "" );

        addSeparator();

        aboutAction = addAction( new QIcon( ICONPATH + "help-about.png" ), "" );
    }

    @SuppressWarnings( "unused" )
    private void onAbout()
    {
        new AboutDialog( MainWindow.getInstance() ).show();
    }

    @SuppressWarnings( "unused" )
    private void onAdd()
    {
        new BookAdditionDialog( MainWindow.getInstance() ).show();
    }

    @SuppressWarnings( "unused" )
    private void onBackup()
    {
        QFileDialogExt fileDialog = new QFileDialogExt( this, tr( "Select backup file" ) )
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
        new BookEditDialog( MainWindow.getInstance(), (ReadingUnit) selectedUnique ).show();
    }

    @SuppressWarnings( "unused" )
    private void onImport()
    {
        new ImportDialog( MainWindow.getInstance() ).show();
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
        StandardButtons buttons = new StandardButtons( StandardButton.Yes, StandardButton.No );
        StandardButton button = QMessageBox.question( this, title, message, buttons, StandardButton.Yes );
        if ( StandardButton.Yes.equals( button ) )
        {
            Storage.getBookShelf().removeUnique( selectedUnique );
            QMessageBox.information( this, tr( "Removed" ), selectedUnique.getName() + " " + tr( "removed" ) );
            CollectionPanel.getInstance().removeSelectedItem();
        }
    }

    @SuppressWarnings( "unused" )
    private void onRestore()
    {
        QFileDialogExt fileDialog = new QFileDialogExt( this, tr( "Select backup file" ) )
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
