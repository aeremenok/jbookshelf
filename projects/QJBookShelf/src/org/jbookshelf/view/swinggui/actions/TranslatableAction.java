package org.jbookshelf.view.swinggui.actions;

import javax.swing.Icon;

import org.jbookshelf.view.i18n.I18N;
import org.jdesktop.swingx.action.AbstractActionExt;

public abstract class TranslatableAction
    extends AbstractActionExt
{
    public TranslatableAction()
    {
        super();
    }

    public TranslatableAction(
        final String name )
    {
        putValue( NAME, I18N.tr( name, getClass() ) );
    }

    public TranslatableAction(
        final String name,
        final Icon icon )
    {
        super( name, icon );
        putValue( NAME, I18N.tr( name, getClass() ) );
    }
}
