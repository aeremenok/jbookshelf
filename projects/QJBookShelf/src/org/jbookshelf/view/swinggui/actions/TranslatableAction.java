package org.jbookshelf.view.swinggui.actions;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import org.jbookshelf.view.i18n.I18N;

/**
 * able to translate its text
 * 
 * @author eav 2009
 */
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
        final Icon icon,
        final String desc )
    {
        super( null, icon );
        putValue( Action.SHORT_DESCRIPTION, desc );
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
