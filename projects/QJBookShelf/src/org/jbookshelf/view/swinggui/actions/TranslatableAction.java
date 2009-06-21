package org.jbookshelf.view.swinggui.actions;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.jbookshelf.view.i18n.I18N;

public abstract class TranslatableAction
    extends AbstractAction
{
    public static String tr(
        final String string )
    {
        return I18N.tr( string );
    }

    public TranslatableAction()
    {
        super();
    }

    public TranslatableAction(
        final String name )
    {
        super( name );
    }

    public TranslatableAction(
        final String name,
        final Icon icon )
    {
        super( name, icon );
    }
}
