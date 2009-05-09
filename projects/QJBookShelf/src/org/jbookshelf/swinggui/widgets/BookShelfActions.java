package org.jbookshelf.swinggui.widgets;

import images.IMG;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JFileChooser;

import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.swinggui.MainWindow;
import org.jbookshelf.swinggui.widgets.panel.CollectionPanel;

public class BookShelfActions
    implements
        Singleton
{
    private class BackupAction
        extends TranslatableAction
    {

        public BackupAction()
        {
            super( I18N.tr( "Backup" ), IMG.icon( IMG.DOCUMENT_SAVE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            final SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
            getFileChooser().setSelectedFile( storage.getCollectionStorageFile() );

            if ( getFileChooser().showSaveDialog( Singletons.instance( MainWindow.class ) ) == JFileChooser.APPROVE_OPTION )
            {
                Storage.backupCollection( getFileChooser().getSelectedFile() );
            }
        }
    }

    private class ImportAction
        extends TranslatableAction
    {

        public ImportAction()
        {
            super( I18N.tr( "Import" ), IMG.icon( IMG.DOCUMENT_IMPORT_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class RestoreAction
        extends TranslatableAction
    {

        public RestoreAction()
        {
            super( I18N.tr( "Restore" ), IMG.icon( IMG.DOCUMENT_REVERT_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            final SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
            getFileChooser().setSelectedFile( storage.getCollectionStorageFile() );
            if ( getFileChooser().showOpenDialog( Singletons.instance( MainWindow.class ) ) == JFileChooser.APPROVE_OPTION )
            {
                Storage.restoreCollection( getFileChooser().getSelectedFile() );
                Singletons.instance( CollectionPanel.class ).updateActiveTree();
            }
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

    public void initSingleton()
    {
    }
}
