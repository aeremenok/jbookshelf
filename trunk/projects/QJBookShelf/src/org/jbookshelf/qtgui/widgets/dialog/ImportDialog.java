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
import org.jbookshelf.model.Book;
import org.jbookshelf.qtgui.MainWindow;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.ext.CyclicProgressBar;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.tree.CollectionTree;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTreeWidget;
import com.trolltech.qt.gui.QTreeWidgetItem;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;
import com.trolltech.qt.gui.QFileDialog.FileMode;
import com.trolltech.qt.gui.QTreeWidgetItem.ChildIndicatorPolicy;

/**
 * allows to select the import options and displays how the import goes on
 * 
 * @author eav
 */
public class ImportDialog
    extends QDialogExt
    implements
        JBookShelfConstants
{
    private class AddButton
        extends QPushButton
    {
        public AddButton()
        {
            super( ImportDialog.this.tr( "Add" ), ImportDialog.this );
            released.connect( this, "addBook()" );
        }

        @SuppressWarnings( "unused" )
        private void addBook()
        {
            QTreeWidgetItem item = importProcessTree.selectedItems().get( 0 );
            File file = failure.get( failureNode.indexOfChild( item ) );
            new BookAdditionDialog( ImportDialog.this, file ).show();
        }
    }

    private class EditButton
        extends QPushButton
    {
        public EditButton()
        {
            super( ImportDialog.this.tr( "Edit" ), ImportDialog.this );
            released.connect( this, "editBook()" );
        }

        @SuppressWarnings( "unused" )
        private void editBook()
        {
            QTreeWidgetItem item = importProcessTree.selectedItems().get( 0 );
            Book book = success.get( successNode.indexOfChild( item ) );
            new BookEditDialog( ImportDialog.this, book ).show();
        }
    }

    private static final String     MASK_SEPARATOR    = "/";

    private final CyclicProgressBar progressBar       = new CyclicProgressBar( this );

    private final QLabel            maskLabel         = new QLabel( this );
    private final QLabel            folderLabel       = new QLabel( this );

    private final QPushButton       ok                = new QPushButton( this );
    private final QPushButton       cancel            = new QPushButton( this );

    private final QLineEdit         maskEdit          = new QLineEdit( this );
    private final FilePathEdit      pathEdit          = new FilePathEdit( this );

    private final QTreeWidget       importProcessTree = new QTreeWidget( this );
    private final QTreeWidgetItem   successNode       = new QTreeWidgetItem( importProcessTree );
    private final QTreeWidgetItem   failureNode       = new QTreeWidgetItem( importProcessTree );

    private final AddButton         addButton         = new AddButton();
    private final EditButton        editButton        = new EditButton();

    private final List<File>        failure           = new ArrayList<File>();

    private final List<Book>        success           = new ArrayList<Book>();

    public ImportDialog(
        final QWidget parent )
    {
        super( parent );

        initComponents();
        initListeners();
        retranslate();
    }

    public void retranslate()
    {
        setWindowTitle( tr( "Import files" ) );

        final String help =
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
        list.add( "" );
        list.add( tr( "Name" ) );
        list.add( tr( "Count" ) );
        importProcessTree.setHeaderLabels( list );
    }

    private void initComponents()
    {
        setModal( true );

        final QRect geometry = geometry();
        geometry.setWidth( 770 );
        geometry.setHeight( 400 );
        geometry.moveCenter( MainWindow.getInstance().geometry().center() );
        setGeometry( geometry );

        final QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( maskLabel, 0, 0 );
        layout.addWidget( maskEdit, 0, 1, 1, 3 );

        layout.addWidget( folderLabel, 1, 0 );
        layout.addWidget( pathEdit, 1, 1, 1, 3 );

        layout.addWidget( addButton, 2, 0, 1, 2 );
        layout.addWidget( editButton, 2, 2, 1, 2 );

        layout.addWidget( importProcessTree, 3, 0, 1, 4 );

        layout.addWidget( progressBar, 4, 0, 1, 2 );
        layout.addWidget( ok, 4, 2 );
        layout.addWidget( cancel, 4, 3 );

        pathEdit.setFileMode( FileMode.DirectoryOnly );
        maskEdit.setText( Settings.getInstance().getProperty( JBookShelfSettings.IMPORT_MASK ) );

        progressBar.setVisible( false );
        progressBar.setMaximum( 100 );

        importProcessTree.setColumnCount( 3 );
        importProcessTree.setColumnWidth( 0, 40 );
        importProcessTree.setColumnWidth( 1, 600 );
        importProcessTree.setColumnWidth( 2, 40 );
        importProcessTree.setSelectionMode( SelectionMode.SingleSelection );

        failureNode.setIcon( 0, new QIcon( ICONPATH + "edit-delete.png" ) );
        successNode.setIcon( 0, new QIcon( ICONPATH + "dialog-ok-apply.png" ) );
        failureNode.setText( 1, tr( "Failed to import" ) );
        successNode.setText( 1, tr( "Imported successfully" ) );
        failureNode.setChildIndicatorPolicy( ChildIndicatorPolicy.ShowIndicator );
        successNode.setChildIndicatorPolicy( ChildIndicatorPolicy.ShowIndicator );
    }

    private void initListeners()
    {
        cancel.released.connect( this, "close()" );
        ok.released.connect( this, "onImport()" );
        importProcessTree.itemExpanded.connect( this, "onExpand(QTreeWidgetItem)" );
        importProcessTree.itemExpanded.connect( importProcessTree, "clearSelection()" );
        importProcessTree.itemCollapsed.connect( importProcessTree, "clearSelection()" );
        importProcessTree.itemSelectionChanged.connect( this, "selected()" );

        selected();
    }

    @SuppressWarnings( "unused" )
    private void onExpand(
        QTreeWidgetItem item )
    {
        // preparing job
        progressBar.setVisible( true );
        progressBar.reset();

        // updating the expanded node
        if ( item.equals( successNode ) && successNode.childCount() == 0 )
        {
            for ( Book book : success )
            {
                QTreeWidgetItem leaf = new QTreeWidgetItem( successNode );
                leaf.setText( 1, book.getName() );
                // importProcessTree.setItemWidget( leaf, 2, new EditButton( book ) );
                progressBar.increment();
            }
        }
        else if ( item.equals( failureNode ) && failureNode.childCount() == 0 )
        {
            for ( File file : failure )
            {
                QTreeWidgetItem leaf = new QTreeWidgetItem( failureNode );
                leaf.setText( 1, file.getAbsolutePath() );
                // importProcessTree.setItemWidget( leaf, 2, new AddButton( file ) );
                progressBar.increment();
            }
        }

        // job's done
        progressBar.setVisible( false );
    }

    @SuppressWarnings( "unused" )
    private void onImport()
    {
        // prepare the importer
        final FileImporter importer = new FileImporter()
        {
            @Override
            protected void onImportFailure(
                final File file,
                final Exception e )
            {
                failure.add( file );
                progressBar.increment();
            }

            @Override
            protected void onImportSuccess(
                final Book book )
            {
                success.add( book );
                progressBar.increment();
            }
        };

        final File file = new File( pathEdit.text() );
        if ( file.exists() && file.isDirectory() )
        {
            // preparing job
            progressBar.setVisible( true );
            progressBar.reset();

            success.clear();
            failure.clear();
            final String[] masks = maskEdit.text().split( MASK_SEPARATOR );
            importer.importFiles( masks, Storage.getBookShelf(), file );

            // updating views
            CollectionPanel.getInstance().updateTree();
            CollectionTree.removeChildren( failureNode );
            CollectionTree.removeChildren( successNode );
            successNode.setText( 2, success.size() + " " + tr( "books" ) );
            failureNode.setText( 2, failure.size() + " " + tr( "files" ) );

            // job's done
            progressBar.setVisible( false );

            QMessageBox.information( this, tr( "Done" ), tr( "Expand tree nodes to see results" ) );
        }
        else
        {
            QMessageBox.critical( this, tr( "Error" ), tr( "File does not exist: " ) + file.getName() );
        }
    }

    private void selected()
    {
        List<QTreeWidgetItem> selectedItems = importProcessTree.selectedItems();
        if ( selectedItems.size() == 1 )
        {
            QTreeWidgetItem parent = selectedItems.get( 0 ).parent();
            if ( successNode.equals( parent ) )
            {
                editButton.setEnabled( true );
                addButton.setEnabled( false );
                return;
            }
            else if ( failureNode.equals( parent ) )
            {
                addButton.setEnabled( true );
                editButton.setEnabled( false );
                return;
            }
        }

        addButton.setEnabled( false );
        editButton.setEnabled( false );
    }

    @Override
    protected void closeEvent(
        final QCloseEvent arg__1 )
    {
        Settings.getInstance().setProperty( JBookShelfSettings.IMPORT_MASK, maskEdit.text() );
        Settings.getInstance().save();

        super.closeEvent( arg__1 );
    }
}
