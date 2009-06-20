package org.jbookshelf.view.swinggui.actions;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;

public abstract class TranslatableAction
    extends AbstractAction
    implements
        Translatable
{
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

    public void retranslate()
    {
        putValue( NAME, I18N.tr( getValue( NAME ).toString() ) );
    }
}
