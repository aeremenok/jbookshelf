package org.jbookshelf.swinggui;

import images.IMG;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.swinggui.widgets.dialog.BookAdditionDialog;
import org.jbookshelf.swinggui.widgets.dialog.JBSAboutDialog;

public class ToolBar
    extends JToolBar
    implements
        Singleton
{
    private class AboutAction
        extends AbstractAction
    {

        public AboutAction()
        {
            super( I18N.tr( "About" ), IMG.icon( IMG.HELP_ABOUT_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new JBSAboutDialog( Singletons.instance( MainWindow.class ) ).setVisible( true );
        }
    }

    private class AddAction
        extends AbstractAction
    {
        public AddAction()
        {
            super( I18N.tr( "Add" ), IMG.icon( IMG.LIST_ADD_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new BookAdditionDialog( Singletons.instance( MainWindow.class ) ).setVisible( true );
        }
    }

    private class BackupAction
        extends AbstractAction
    {

        public BackupAction()
        {
            super( I18N.tr( "Backup" ), IMG.icon( IMG.DOCUMENT_SAVE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class EditAction
        extends AbstractAction
    {

        public EditAction()
        {
            super( I18N.tr( "Edit" ), IMG.icon( IMG.DOCUMENT_PROPERTIES_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class GoogleAction
        extends AbstractAction
    {

        public GoogleAction()
        {
            super( I18N.tr( "Google" ), IMG.icon( IMG.GOOGLE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class ImportAction
        extends AbstractAction
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

    private class OpenAction
        extends AbstractAction
    {

        public OpenAction()
        {
            super( I18N.tr( "Open" ), IMG.icon( IMG.DOCUMENT_PREVIEW_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class OpenDirAction
        extends AbstractAction
    {

        public OpenDirAction()
        {
            super( I18N.tr( "Open Directory" ), IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class RemoveAction
        extends AbstractAction
    {

        public RemoveAction()
        {
            super( I18N.tr( "Remove" ), IMG.icon( IMG.LIST_REMOVE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class RestoreAction
        extends AbstractAction
    {

        public RestoreAction()
        {
            super( I18N.tr( "Restore" ), IMG.icon( IMG.DOCUMENT_REVERT_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private class SettingsAction
        extends AbstractAction
    {
        public SettingsAction()
        {
            super( I18N.tr( "Settings" ), IMG.icon( IMG.CONFIGURE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            // TODO Auto-generated method stub
        }
    }

    private final Action addAction      = new AddAction();
    private final Action removeAction   = new RemoveAction();
    private final Action editAction     = new EditAction();

    private final Action openAction     = new OpenAction();
    private final Action openDirAction  = new OpenDirAction();
    private final Action googleAction   = new GoogleAction();

    private final Action importAction   = new ImportAction();
    private final Action backupAction   = new BackupAction();
    private final Action restoreAction  = new RestoreAction();

    private final Action settingsAction = new SettingsAction();

    private final Action aboutAction    = new AboutAction();

    @Override
    public JButton add(
        final Action a )
    {
        final JButton add = super.add( a );
        add.setHideActionText( false );
        return add;
    }

    public void initSingleton()
    {
        add( addAction );
        add( removeAction );
        add( editAction );
        addSeparator();
        add( openAction );
        add( openDirAction );
        add( googleAction );
        addSeparator();
        add( importAction );
        add( backupAction );
        add( restoreAction );
        addSeparator();
        add( settingsAction );
        addSeparator();
        add( aboutAction );
    }

}
