package org.jbookshelf.qtgui.widgets.dialog;

import java.io.File;

import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QFileDialog.FileMode;

public class ImportDialog
    extends QDialogExt
{
    private QLabel       maskLabel   = new QLabel( this );
    private QLabel       folderLabel = new QLabel( this );

    private QPushButton  ok          = new QPushButton( this );
    private QPushButton  cancel      = new QPushButton( this );

    private QLineEdit    maskEdit    = new QLineEdit( this );
    private FilePathEdit pathEdit    = new FilePathEdit( this );

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

        ok.setText( tr( "OK" ) );
        cancel.setText( tr( "Cancel" ) );

        pathEdit.setCaption( tr( "Select a directory to import" ) );
    }

    private void initComponents()
    {
        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( maskLabel, 0, 0 );
        layout.addWidget( maskEdit, 0, 1 );

        layout.addWidget( folderLabel, 1, 0 );
        layout.addWidget( pathEdit, 1, 1 );

        layout.addWidget( ok, 2, 0 );
        layout.addWidget( cancel, 2, 1 );

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
        FileImporter importer = new FileImporter()
        {
            @Override
            protected void onImportFailure(
                File file,
                Exception e )
            {
                // todo visualize
                System.out.println( "- cannot import file " + file.getAbsolutePath() + " cause:" + e.getMessage() );
                // e.printStackTrace();
            }

            @Override
            protected void onImportSuccess(
                ReadingUnit book )
            {
                // todo visualize
                System.out.println( "+ imported " + book.getAuthors().get( 0 ).getName() + ". " + book.getName() );
            }
        };

        File file = new File( pathEdit.text() );
        if ( file.exists() && file.isDirectory() )
        {
            importer.importFiles( maskEdit.text(), Storage.getBookShelf(), file.listFiles() );
            CollectionPanel.getInstance().updateTree();
            close();
        }
        else
        {
            QMessageBox.critical( this, tr( "Error" ), tr( "File does not exist: " ) + file.getName() );
        }
    }
}
