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
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

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
    private QLabel       maskLabel          = new QLabel( this );
    private QLabel       folderLabel        = new QLabel( this );

    private QPushButton  ok                 = new QPushButton( this );
    private QPushButton  cancel             = new QPushButton( this );

    private QLineEdit    maskEdit           = new QLineEdit( this );
    private FilePathEdit pathEdit           = new FilePathEdit( this );

    private QTableWidget importProcessTable = new QTableWidget( 0, 3 );

    public ImportDialog(
        QWidget parent )
    {
        super( parent );
        initComponents();
        initListeners();
        Translator.addTranslatable( this );
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
        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( maskLabel, 0, 0 );
        layout.addWidget( maskEdit, 0, 1 );

        layout.addWidget( folderLabel, 1, 0 );
        layout.addWidget( pathEdit, 1, 1 );

        layout.addWidget( importProcessTable, 2, 0, 1, 2 );

        layout.addWidget( ok, 3, 0 );
        layout.addWidget( cancel, 3, 1 );

        pathEdit.setFileMode( FileMode.DirectoryOnly );
        maskEdit.setText( Settings.getInstance().getProperty( JBookShelfSettings.IMPORT_MASK ) );
    }

    private void initListeners()
    {
        cancel.released.connect( this, "close()" );
        ok.released.connect( this, "onImport()" );
    }

    @SuppressWarnings( "unused" )
    private void onImport()
    {
        final List<QTableWidgetItem> counter = new ArrayList<QTableWidgetItem>();
        FileImporter importer = new FileImporter()
        {
            @Override
            protected void onImportFailure(
                File file,
                Exception e )
            {
                importProcessTable.setRowCount( importProcessTable.rowCount() + 1 );

                QTableWidgetItem icon = new QTableWidgetItem( new QIcon( ICONPATH + "edit-delete.png" ), "" );
                importProcessTable.setItem( counter.size(), 0, icon );

                QTableWidgetItem name = new QTableWidgetItem( file.getName() );
                importProcessTable.setItem( counter.size(), 1, name );

                QTableWidgetItem edit = new QTableWidgetItem( tr( "Add" ) );
                importProcessTable.setItem( counter.size(), 2, edit );
                // todo add action

                counter.add( icon );
            }

            @Override
            protected void onImportSuccess(
                ReadingUnit book )
            {
                importProcessTable.setRowCount( importProcessTable.rowCount() + 1 );

                QTableWidgetItem icon = new QTableWidgetItem( new QIcon( ICONPATH + "dialog-ok-apply.png" ), "" );
                importProcessTable.setItem( counter.size(), 0, icon );

                QTableWidgetItem name = new QTableWidgetItem( book.getName() );
                importProcessTable.setItem( counter.size(), 1, name );

                QTableWidgetItem edit = new QTableWidgetItem( tr( "Edit" ) );
                importProcessTable.setItem( counter.size(), 2, edit );
                // todo add action

                counter.add( icon );
            }
        };

        File file = new File( pathEdit.text() );
        if ( file.exists() && file.isDirectory() )
        {
            importer.importFiles( maskEdit.text(), Storage.getBookShelf(), file.listFiles() );
            // todo progressbar
            CollectionPanel.getInstance().updateTree();
        }
        else
        {
            QMessageBox.critical( this, tr( "Error" ), tr( "File does not exist: " ) + file.getName() );
        }
    }
}
