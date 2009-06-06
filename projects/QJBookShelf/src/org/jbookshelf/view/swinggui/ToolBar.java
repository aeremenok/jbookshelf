package org.jbookshelf.view.swinggui;

import images.IMG;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.widgets.BookShelfActions;
import org.jbookshelf.view.swinggui.widgets.TranslatableAction;
import org.jbookshelf.view.swinggui.widgets.UniqueActions;
import org.jbookshelf.view.swinggui.widgets.dialog.BookAdditionDialog;
import org.jbookshelf.view.swinggui.widgets.dialog.JBSAboutDialog;
import org.jbookshelf.view.swinggui.widgets.dialog.SettingsDialog;

public class ToolBar
    extends JToolBar
    implements
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
            new JBSAboutDialog( ).setVisible( true );
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
            new BookAdditionDialog( ).setVisible( true );
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
            new SettingsDialog( ).setVisible( true );
        }
    }

    private final Action                   addAction           = new AddAction();
    private final Action                   aboutAction         = new AboutAction();
    private final Action                   settingsAction      = new SettingsAction();

    private final List<TranslatableAction> translatableActions = new ArrayList<TranslatableAction>();

    @Override
    public JButton add(
        final Action a )
    {
        if ( a instanceof TranslatableAction )
        {
            translatableActions.add( (TranslatableAction) a );
        }

        final JButton add = super.add( a );
        add.setHideActionText( false );
        return add;
    }

    @PostConstruct
    public void initSingleton()
    {
        Translator.addTranslatable( this );

        final UniqueActions uniqueActions = Single.instance( UniqueActions.class );
        add( addAction );
        add( uniqueActions.removeAction );
        add( uniqueActions.editAction );
        addSeparator();
        add( uniqueActions.openAction );
        add( uniqueActions.openDirAction );
        add( uniqueActions.googleAction );
        addSeparator();
        final BookShelfActions collectionActions = Single.instance( BookShelfActions.class );
        add( collectionActions.importAction );
        add( collectionActions.backupAction );
        add( collectionActions.restoreAction );
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
