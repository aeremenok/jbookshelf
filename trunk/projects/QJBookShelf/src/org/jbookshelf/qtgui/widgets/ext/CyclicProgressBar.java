package org.jbookshelf.qtgui.widgets.ext;

import com.trolltech.qt.gui.QProgressBar;
import com.trolltech.qt.gui.QWidget;

public class CyclicProgressBar
    extends QProgressBar
{
    public CyclicProgressBar()
    {
        super();
        init();
    }

    public CyclicProgressBar(
        QPrivateConstructor p )
    {
        super( p );
        init();
    }

    public CyclicProgressBar(
        QWidget parent )
    {
        super( parent );
        init();
    }

    public void increment()
    {
        if ( value() == maximum() )
        {
            reset();
        }
        setValue( value() + 1 );
    }

    private void init()
    {
        setTextVisible( false );
    }
}
