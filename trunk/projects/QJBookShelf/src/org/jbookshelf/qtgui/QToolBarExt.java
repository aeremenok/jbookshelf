package org.jbookshelf.qtgui;

import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;

import com.trolltech.qt.gui.QToolBar;
import com.trolltech.qt.gui.QWidget;

public abstract class QToolBarExt
    extends QToolBar
    implements
        Translatable
{

    public QToolBarExt()
    {
        super();
    }

    public QToolBarExt(
        QPrivateConstructor p )
    {
        super( p );
    }

    public QToolBarExt(
        QWidget parent )
    {
        super( parent );
    }

    public QToolBarExt(
        String title )
    {
        super( title );
    }

    public QToolBarExt(
        String title,
        QWidget parent )
    {
        super( title, parent );
    }

    @Override
    public String tr(
        String source )
    {
        return Translator.tr( getClass().getName(), source );
    }

}
