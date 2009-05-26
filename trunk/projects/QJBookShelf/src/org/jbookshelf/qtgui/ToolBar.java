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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.reader.ReaderWindow;
import org.jbookshelf.qtgui.widgets.dialog.AboutDialog;
import org.jbookshelf.qtgui.widgets.dialog.BookAdditionDialog;
import org.jbookshelf.qtgui.widgets.dialog.BookEditDialog;
import org.jbookshelf.qtgui.widgets.dialog.ImportDialog;
import org.jbookshelf.qtgui.widgets.dialog.SettingsDialog;
import org.jbookshelf.qtgui.widgets.ext.QFileDialogExt;
import org.jbookshelf.qtgui.widgets.ext.QToolBarExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

import com.trolltech.qt.core.Qt.ToolButtonStyle;
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
    public static List<Book> hasBooks(
        final List<? extends Unique> uniques )
    {
        final List<Book> books = new ArrayList<Book>();
        for ( final Unique unique : uniques )
        {
            if ( unique instanceof Book )
            {
                books.add( (Book) unique );
            }
        }
        return books;
    }

    private QAction      addAction;
    private QAction      removeAction;
    private QAction      editAction;
    private QAction      openAction;
    private QAction      openFolderAction;
    private QAction      googleAction;
    private QAction      settingsAction;
    private QAction      aboutAction;
    private QAction      restoreAction;
    private QAction      backupAction;

    private QAction      importAction;

    private List<Unique> selectedUniques;

    public QAction getEditAction()
    {
        return editAction;
    }

    public QAction getGoogleAction()
    {
        return googleAction;
    }

    public QAction getOpenAction()
    {
        return openAction;
    }

    public QAction getOpenFolderAction()
    {
        return openFolderAction;
    }

    public QAction getRemoveAction()
    {
        return removeAction;
    }

    @PostConstruct
    public void initSingleton()
    {
        createActions();
        connectActions();

        setToolButtonStyle( ToolButtonStyle.ToolButtonTextUnderIcon );
        selectedUniques( new ArrayList<Unique>() );

        Translator.addTranslatable( this );
    }

    public void retranslate()
    {
        addAction.setText( tr( "&Add" ) );
        removeAction.setText( tr( "&Remove" ) );
        editAction.setText( tr( "&Edit" ) );

        openAction.setText( tr( "&Open" ) );
        openFolderAction.setText( tr( "Open &Folder" ) );

        settingsAction.setText( tr( "&Settings" ) );

        importAction.setText( tr( "&Import" ) );
        backupAction.setText( tr( "&Backup" ) );
        restoreAction.setText( tr( "&Restore" ) );

        googleAction.setText( tr( "&Google this" ) );
        aboutAction.setText( tr( "&About" ) );
    }

    public void selectedUniques(
        final List<Unique> uniques )
    {
        this.selectedUniques = uniques;
        removeAction.setEnabled( uniques.size() > 0 );
        googleAction.setEnabled( uniques.size() > 0 );

        final List<Book> books = hasBooks( uniques );
        // all books and their folders can be opened
        openAction.setEnabled( books.size() > 0 );
        openFolderAction.setEnabled( books.size() > 0 );
        // only a single book can be edited
        editAction.setEnabled( books.size() == 1 );
    }

    private void connectActions()
    {
        addAction.triggered.connect( this, "onAdd()" );
        removeAction.triggered.connect( this, "onRemove()" );
        editAction.triggered.connect( this, "onEdit()" );

        openFolderAction.triggered.connect( this, "onOpenFolder()" );
        openAction.triggered.connect( this, "onOpen()" );
        googleAction.triggered.connect( this, "onGoogle()" );

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
        openFolderAction = addAction( new QIcon( ICONPATH + "document-open-folder.png" ), "" );
        googleAction = addAction( new QIcon( ICONPATH + "google.png" ), "" );

        addSeparator();

        importAction = addAction( new QIcon( ICONPATH + "document-import.png" ), "" );
        backupAction = addAction( new QIcon( ICONPATH + "document-save.png" ), "" );
        restoreAction = addAction( new QIcon( ICONPATH + "document-revert.png" ), "" );

        addSeparator();

        settingsAction = addAction( new QIcon( ICONPATH + "configure.png" ), "" );

        addSeparator();

        aboutAction = addAction( new QIcon( ICONPATH + "help-about.png" ), "" );
    }

    @SuppressWarnings( "unused" )
    private void onAbout()
    {
        new AboutDialog( Single.instance( MainWindow.class ) ).show();
    }

    @SuppressWarnings( "unused" )
    private void onAdd()
    {
        new BookAdditionDialog( Single.instance( MainWindow.class ) ).show();
    }

    @SuppressWarnings( "unused" )
    private void onBackup()
    {
        final QFileDialogExt fileDialog = new QFileDialogExt( this, tr( "Select backup file" ) )
        {
            @Override
            protected void filesSelected()
            {
                Storage.backupCollection( getSelectedFile() );
            }
        };

        final SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        fileDialog.setSelectedFile( storage.getCollectionStorageFile() );

        fileDialog.show();
    }

    @SuppressWarnings( "unused" )
    private void onEdit()
    {
        // todo edit multiple books
        new BookEditDialog( Single.instance( MainWindow.class ), hasBooks( selectedUniques ).get( 0 ) ).show();
    }

    @SuppressWarnings( "unused" )
    private void onGoogle()
    {
        for ( final Unique unique : selectedUniques )
        {
            URIUtil.google( unique.getName() );
        }
    }

    @SuppressWarnings( "unused" )
    private void onImport()
    {
        new ImportDialog( Single.instance( MainWindow.class ) ).show();
    }

    @SuppressWarnings( "unused" )
    private void onOpen()
    {
        for ( final Unique unique : selectedUniques )
        {
            if ( unique instanceof Book )
            {
                ReaderWindow.open( this, (Book) unique );
            }
        }
    }

    @SuppressWarnings( "unused" )
    private void onOpenFolder()
    {
        for ( final Unique unique : selectedUniques )
        {
            if ( unique instanceof Book )
            {
                final File directory = ((Book) unique).getPhysical().getDirectory();
                URIUtil.openFolder( directory );
            }
        }
    }

    @SuppressWarnings( "unused" )
    private void onRemove()
    {
        final String title = tr( "Confirm" );
        final String message = tr( "Remove selected?" );
        final StandardButtons buttons = new StandardButtons( StandardButton.Yes, StandardButton.No );
        final StandardButton button = QMessageBox.question( this, title, message, buttons, StandardButton.Yes );
        if ( StandardButton.Yes.equals( button ) )
        {
            Single.instance( CollectionPanel.class ).removeSelectedItems();
        }
    }

    @SuppressWarnings( "unused" )
    private void onRestore()
    {
        final QFileDialogExt fileDialog = new QFileDialogExt( this, tr( "Select backup file" ) )
        {
            @Override
            protected void filesSelected()
            {
                Storage.restoreCollection( getSelectedFile() );
                Single.instance( CollectionPanel.class ).updateTree();
            }
        };

        final SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        fileDialog.setSelectedFile( storage.getCollectionStorageFile() );

        fileDialog.show();
    }

    @SuppressWarnings( "unused" )
    private void onSettings()
    {
        new SettingsDialog( Single.instance( MainWindow.class ) ).setVisible( true );
    }
}
