package org.jbookshelf.view.swinggui.widgets;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JFileChooser;

import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.widgets.dialog.FileImportDialog;

public class BookShelfActions
{
    private class BackupAction
        extends TranslatableAction
    {
        public BackupAction()
        {
            super( I18N.tr( "Backup" ), IMG.icon( IMG.DOCUMENT_SAVE_PNG, 32 ) );
            setEnabled( false );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
        //            final SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        //            getFileChooser().setSelectedFile( storage.getCollectionStorageFile() );
        //
        //            if ( getFileChooser().showSaveDialog( Single.instance( MainWindow.class ) ) == JFileChooser.APPROVE_OPTION )
        //            {
        //                Storage.backupCollection( getFileChooser().getSelectedFile() );
        //            }
        }
    }

    private class ImportAction
        extends TranslatableAction
    {
        public ImportAction()
        {
            super( I18N.tr( "Import" ), IMG.icon( IMG.DOCUMENT_IMPORT_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new FileImportDialog().setVisible( true );
        }
    }

    private class RestoreAction
        extends TranslatableAction
    {
        public RestoreAction()
        {
            super( I18N.tr( "Restore" ), IMG.icon( IMG.DOCUMENT_REVERT_PNG, 32 ) );
            setEnabled( false );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
        //            final SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        //            getFileChooser().setSelectedFile( storage.getCollectionStorageFile() );
        //            if ( getFileChooser().showOpenDialog( Single.instance( MainWindow.class ) ) == JFileChooser.APPROVE_OPTION )
        //            {
        //                Storage.restoreCollection( getFileChooser().getSelectedFile() );
        //                Single.instance( CollectionPanel.class ).updateActiveTree();
        //            }
        }
    }

    public final Action  restoreAction = new RestoreAction();
    public final Action  backupAction  = new BackupAction();
    public final Action  importAction  = new ImportAction();

    private JFileChooser fileChooser;

    public JFileChooser getFileChooser()
    {
        if ( fileChooser == null )
        {
            fileChooser = new JFileChooser();
        }
        return fileChooser;
    }
}
