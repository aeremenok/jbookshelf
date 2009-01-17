package org.jbookshelf.qtgui.widgets.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.ext.CyclicProgressBar;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

import com.trolltech.qt.core.QRect;
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

    private CyclicProgressBar progressBar        = new CyclicProgressBar( this );

    private QLabel            maskLabel          = new QLabel( this );
    private QLabel            folderLabel        = new QLabel( this );

    private QPushButton       ok                 = new QPushButton( this );
    private QPushButton       cancel             = new QPushButton( this );

    private QLineEdit         maskEdit           = new QLineEdit( this );
    private FilePathEdit      pathEdit           = new FilePathEdit( this );

    private QTableWidget      importProcessTable = new QTableWidget( 0, 3 );

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
        QRect geometry = geometry();
        geometry.setWidth( 770 );
        geometry.setHeight( 400 );
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
        // todo introduce paginator - large collections are imported very slowly

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

            importer.importFiles( maskEdit.text(), Storage.getBookShelf(), file.listFiles() );

            progressBar.setVisible( false );
            importProcessTable.setSortingEnabled( true );

            CollectionPanel.getInstance().updateTree();
        }
        else
        {
            QMessageBox.critical( this, tr( "Error" ), tr( "File does not exist: " ) + file.getName() );
        }
    }
}
