package org.jbookshelf.view.swinggui.actions;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.jbookshelf.view.swinggui.dialog.importer.FileImportDialog;

/**
 * applied to the whole collection
 * 
 * @author eav 2009
 */
public class BookShelfActions
{
    private class BackupAction
        extends TranslatableAction
    {
        public BackupAction()
        {
            super( tr( "Backup" ), IMG.icon( IMG.DOCUMENT_SAVE_PNG, 32 ) );
            setEnabled( false );
        }

        public void actionPerformed(
            final ActionEvent e )
        {}
    }

    private class ImportAction
        extends TranslatableAction
    {
        public ImportAction()
        {
            super( tr( "Import" ), IMG.icon( IMG.DOCUMENT_IMPORT_PNG, 32 ) );
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
            super( tr( "Restore" ), IMG.icon( IMG.DOCUMENT_REVERT_PNG, 32 ) );
            setEnabled( false );
        }

        public void actionPerformed(
            final ActionEvent e )
        {}
    }

    public final Action restoreAction = new RestoreAction();
    public final Action backupAction  = new BackupAction();
    public final Action importAction  = new ImportAction();
}
