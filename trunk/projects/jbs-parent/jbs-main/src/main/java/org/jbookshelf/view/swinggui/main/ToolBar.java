package org.jbookshelf.view.swinggui.main;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.actions.NamedIdentifieableActions;
import org.jbookshelf.view.swinggui.dialog.BugReportDialog;
import org.jbookshelf.view.swinggui.dialog.JBSAboutDialog;
import org.jbookshelf.view.swinggui.dialog.SettingsDialog;
import org.jbookshelf.view.swinggui.dialog.book.BookAdditionDialog;
import org.jbookshelf.view.swinggui.dialog.importer.FileImportDialog;

/**
 * main window toolbar. dispatches the main actions
 * 
 * @author eav 2009
 */
public class ToolBar
    extends JToolBar
{
    private class AboutAction
        extends TranslatableAction
    {
        public AboutAction()
        {
            super( tr( "About" ), IMG.icon( IMG.HELP_ABOUT_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new JBSAboutDialog().setVisible( true );
        }
    }

    private class AddAction
        extends TranslatableAction
    {
        public AddAction()
        {
            super( tr( "Add" ), IMG.icon( IMG.BOOK_NEW, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new BookAdditionDialog().setVisible( true );
        }
    }

    private class BugReportAction
        extends TranslatableAction
    {
        public BugReportAction()
        {
            super( tr( "Report a bug" ), IMG.icon( IMG.BUG_REPORT_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new BugReportDialog().setVisible( true );
        }
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

    private class SettingsAction
        extends TranslatableAction
    {
        public SettingsAction()
        {
            super( tr( "Settings" ), IMG.icon( IMG.CONFIGURE_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new SettingsDialog().setVisible( true );
        }
    }

    private final Action addAction       = new AddAction();
    private final Action aboutAction     = new AboutAction();
    private final Action bugReportAction = new BugReportAction();
    private final Action settingsAction  = new SettingsAction();
    private final Action importAction    = new ImportAction();

    @Override
    public JButton add(
        final Action a )
    {
        final JButton add = super.add( a );
        add.setHideActionText( false );
        return add;
    }

    @PostConstruct
    public void initSingleton()
    {
        final NamedIdentifieableActions uniqueActions = Single.instance( NamedIdentifieableActions.class );
        add( addAction );
        add( uniqueActions.removeAction );
        add( uniqueActions.editAction );
        addSeparator();
        add( uniqueActions.openAction );
        add( uniqueActions.openDirAction );
        add( uniqueActions.googleAction );
        addSeparator();
        add( importAction );
        addSeparator();
        add( settingsAction );
        addSeparator();
        add( aboutAction );
        add( bugReportAction );
    }
}
