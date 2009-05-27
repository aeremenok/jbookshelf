package org.jbookshelf.view.swinggui.widgets;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class StretchAction
    extends AbstractAction
{
    public StretchAction()
    {
        super();
    }

    public StretchAction(
        final String name )
    {
        super( name );
    }

    public StretchAction(
        final String name,
        final Icon icon )
    {
        super( name, icon );
    }

    @Override
    public void putValue(
        final String key,
        final Object newValue )
    {
        if ( NAME.equals( key ) )
        {
            super.putValue( key, "<html>" + newValue + "</html>" );
        }
        else
        {
            super.putValue( key, newValue );
        }
    }
}
