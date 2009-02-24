package org.jbookshelf.qtgui.widgets.ext;

import com.trolltech.qt.gui.QFocusEvent;
import com.trolltech.qt.gui.QWidget;

public class SearchTextField
    extends QLineEditExt
{

    private static final String SEARCH = "Search...";

    public SearchTextField()
    {
        super();
        setText( tr( SEARCH ) );
    }

    public SearchTextField(
        QPrivateConstructor p )
    {
        super( p );
        setText( tr( SEARCH ) );
    }

    public SearchTextField(
        QWidget parent )
    {
        super( parent );
        setText( tr( SEARCH ) );
    }

    public SearchTextField(
        String arg__1 )
    {
        super( arg__1 );
        setText( tr( SEARCH ) );
    }

    public SearchTextField(
        String arg__1,
        QWidget parent )
    {
        super( arg__1, parent );
        setText( tr( SEARCH ) );
    }

    @Override
    protected void focusInEvent(
        QFocusEvent arg__1 )
    {
        super.focusInEvent( arg__1 );

        if ( text().equals( tr( SEARCH ) ) )
        {
            setText( "" );
        }
    }

    @Override
    protected void focusOutEvent(
        QFocusEvent arg__1 )
    {
        super.focusOutEvent( arg__1 );

        if ( text().equals( "" ) )
        {
            setText( tr( SEARCH ) );
        }
    }
}
