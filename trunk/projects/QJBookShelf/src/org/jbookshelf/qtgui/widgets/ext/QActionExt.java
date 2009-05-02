package org.jbookshelf.qtgui.widgets.ext;

import org.jbookshelf.qtgui.logic.Translator;

import com.trolltech.qt.core.QObject;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QIcon;

public class QActionExt
    extends QAction
{
    public QActionExt(
        final QIcon icon,
        final String text,
        final QObject parent )
    {
        super( icon, text, parent );
    }

    public QActionExt(
        final QObject parent )
    {
        super( parent );
    }

    public QActionExt(
        final QPrivateConstructor p )
    {
        super( p );
    }

    public QActionExt(
        final String text,
        final QObject parent )
    {
        super( text, parent );
    }

    @Override
    public String tr(
        final String source )
    {
        return Translator.tr( getClass().getName(), source );
    }
}
