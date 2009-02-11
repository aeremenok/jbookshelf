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
package org.jbookshelf.qtgui.widgets.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.qtgui.MainWindow;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.ext.CyclicProgressBar;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QFileDialog.FileMode;

public class ImportDialog
    extends QDialogExt
    implements
        JBookShelfConstants
{
    private class AddButton
        extends QPushButton
    {
        private final File file;

        public AddButton(
            File file )
        {
            super( ImportDialog.this.tr( "Add" ) );
            this.file = file;
            released.connect( this, "addBook()" );
        }

        @SuppressWarnings( "unused" )
        private void addBook()
        {
            new BookAdditionDialog( ImportDialog.this, file ).show();
        }
    }

    private class EditButton
        extends QPushButton
    {
        private final ReadingUnit book;

        public EditButton(
            ReadingUnit book )
        {
            super( ImportDialog.this.tr( "Edit" ) );
            this.book = book;
            released.connect( this, "editBook()" );
        }

        @SuppressWarnings( "unused" )
        private void editBook()
        {
            new BookEditDialog( ImportDialog.this, book ).show();
        }
    }

    private static final String MASK_SEPARATOR     = "/";

    private CyclicProgressBar   progressBar        = new CyclicProgressBar( this );

    private QLabel              maskLabel          = new QLabel( this );
    private QLabel              folderLabel        = new QLabel( this );

    private QPushButton         ok                 = new QPushButton( this );
    private QPushButton         cancel             = new QPushButton( this );

    private QLineEdit           maskEdit           = new QLineEdit( this );
    private FilePathEdit        pathEdit           = new FilePathEdit( this );

    private QTableWidget        importProcessTable = new QTableWidget( 0, 3 );

    public ImportDialog(
        QWidget parent )
    {
        super( parent );

        initComponents();
        initListeners();
        retranslate();
    }

    public void retranslate()
    {
        setWindowTitle( tr( "Import files" ) );

        String help =
            tr( "Specify multiple masks separated with /. If JBookShelf fails to parse a filename using the mask - it will try the next one.\n"
                + "Use:\n%a - to mask an author\n%b - to mask a book\n%c - to mask a category" );
        maskLabel.setWhatsThis( help );
        maskLabel.setToolTip( help );
        maskEdit.setWhatsThis( help );
        maskEdit.setToolTip( help );

        maskLabel.setText( tr( "Import mask" ) );
        folderLabel.setText( tr( "Import folder" ) );

        ok.setText( tr( "Import" ) );
        cancel.setText( tr( "Close" ) );

        pathEdit.setCaption( tr( "Select a directory to import" ) );

        List<String> list = new ArrayList<String>();
        list.add( tr( "Result" ) );
        list.add( tr( "Name" ) );
        list.add( tr( "Action" ) );
        importProcessTable.setHorizontalHeaderLabels( list );

    }

    private void initComponents()
    {
        setModal( true );

        QRect geometry = geometry();
        geometry.setWidth( 770 );
        geometry.setHeight( 400 );
        geometry.moveCenter( MainWindow.getInstance().geometry().center() );
        setGeometry( geometry );

        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( maskLabel, 0, 0 );
        layout.addWidget( maskEdit, 0, 1, 1, 3 );

        layout.addWidget( folderLabel, 1, 0 );
        layout.addWidget( pathEdit, 1, 1, 1, 3 );

        layout.addWidget( importProcessTable, 2, 0, 1, 4 );

        layout.addWidget( progressBar, 3, 0, 1, 2 );
        layout.addWidget( ok, 3, 2 );
        layout.addWidget( cancel, 3, 3 );

        pathEdit.setFileMode( FileMode.DirectoryOnly );
        maskEdit.setText( Settings.getInstance().getProperty( JBookShelfSettings.IMPORT_MASK ) );

        importProcessTable.setColumnWidth( 1, 500 );

        progressBar.setVisible( false );
        progressBar.setMaximum( 100 );
    }

    private void initListeners()
    {
        cancel.released.connect( this, "close()" );
        ok.released.connect( this, "onImport()" );
    }

    @SuppressWarnings( "unused" )
    private void onImport()
    {
        importProcessTable.setSortingEnabled( false );
        importProcessTable.clearContents();
        // todo introduce a paginator or a tree - large collections are imported very slowly

        final List<QTableWidgetItem> counter = new ArrayList<QTableWidgetItem>();
        FileImporter importer = new FileImporter()
        {
            @Override
            protected void onImportFailure(
                File file,
                Exception e )
            {
                importProcessTable.setRowCount( importProcessTable.rowCount() + 1 );

                QTableWidgetItem icon = new QTableWidgetItem( new QIcon( ICONPATH + "edit-delete.png" ), null );
                importProcessTable.setItem( counter.size(), 0, icon );
                importProcessTable.setItem( counter.size(), 1, new QTableWidgetItem( file.getAbsolutePath() ) );
                importProcessTable.setCellWidget( counter.size(), 2, new AddButton( file ) );

                counter.add( icon );
                progressBar.increment();
            }

            @Override
            protected void onImportSuccess(
                ReadingUnit book )
            {
                importProcessTable.setRowCount( importProcessTable.rowCount() + 1 );

                QTableWidgetItem icon = new QTableWidgetItem( new QIcon( ICONPATH + "dialog-ok-apply.png" ), null );
                importProcessTable.setItem( counter.size(), 0, icon );
                importProcessTable.setItem( counter.size(), 1, new QTableWidgetItem( book.getName() ) );
                importProcessTable.setCellWidget( counter.size(), 2, new EditButton( book ) );

                counter.add( icon );
                progressBar.increment();
            }
        };

        File file = new File( pathEdit.text() );
        if ( file.exists() && file.isDirectory() )
        {
            progressBar.setVisible( true );

            String[] masks = maskEdit.text().split( MASK_SEPARATOR );
            importer.importFiles( masks, Storage.getBookShelf(), file );

            progressBar.setVisible( false );
            importProcessTable.setSortingEnabled( true );

            CollectionPanel.getInstance().updateTree();
        }
        else
        {
            QMessageBox.critical( this, tr( "Error" ), tr( "File does not exist: " ) + file.getName() );
        }
    }

    @Override
    protected void closeEvent(
        QCloseEvent arg__1 )
    {
        Settings.getInstance().setProperty( JBookShelfSettings.IMPORT_MASK, maskEdit.text() );
        Settings.getInstance().save();

        super.closeEvent( arg__1 );
    }
}
