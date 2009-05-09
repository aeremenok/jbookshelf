package org.jbookshelf.swinggui;

import images.IMG;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.swinggui.widgets.BookShelfActions;
import org.jbookshelf.swinggui.widgets.TranslatableAction;
import org.jbookshelf.swinggui.widgets.dialog.BookAdditionDialog;
import org.jbookshelf.swinggui.widgets.dialog.JBSAboutDialog;
import org.jbookshelf.swinggui.widgets.dialog.SettingsDialog;

public class ToolBar
    extends JToolBar
    implements
        Singleton,
        Translatable
{
    private class AboutAction
        extends TranslatableAction
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
        extends TranslatableAction
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

    private class EditAction
        extends TranslatableAction
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
        extends TranslatableAction
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

    private class OpenAction
        extends TranslatableAction
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
        extends TranslatableAction
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
        extends TranslatableAction
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

    private class SettingsAction
        extends TranslatableAction
    {
        public SettingsAction()
        {
            super( I18N.tr( "Settings" ), IMG.icon( IMG.CONFIGURE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new SettingsDialog( Singletons.instance( MainWindow.class ) ).setVisible( true );
        }
    }

    private final Action                   addAction           = new AddAction();
    private final Action                   removeAction        = new RemoveAction();
    private final Action                   editAction          = new EditAction();

    private final Action                   openAction          = new OpenAction();
    private final Action                   openDirAction       = new OpenDirAction();
    private final Action                   googleAction        = new GoogleAction();

    private final Action                   aboutAction         = new AboutAction();

    private final Action                   settingsAction      = new SettingsAction();

    private final List<TranslatableAction> translatableActions = new ArrayList<TranslatableAction>();

    @Override
    public JButton add(
        final Action a )
    {
        final JButton add = super.add( a );
        add.setHideActionText( false );
        if ( a instanceof TranslatableAction )
        {
            translatableActions.add( (TranslatableAction) a );
        }
        return add;
    }

    public void initSingleton()
    {
        Translator.addTranslatable( this );

        add( addAction );
        add( removeAction );
        add( editAction );
        addSeparator();
        add( openAction );
        add( openDirAction );
        add( googleAction );
        addSeparator();
        final BookShelfActions collectionActions = Singletons.instance( BookShelfActions.class );
        add( collectionActions.IMPORT_ACTION );
        add( collectionActions.BACKUP_ACTION );
        add( collectionActions.RESTORE_ACTION );
        addSeparator();
        add( settingsAction );
        addSeparator();
        add( aboutAction );
    }

    public void retranslate()
    {
        for ( final TranslatableAction action : translatableActions )
        {
            action.retranslate();
        }
    }

}
