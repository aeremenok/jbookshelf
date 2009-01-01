package org.jbookshelf.qtgui.widgets.ext;

import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;

import com.trolltech.qt.core.Qt.WindowFlags;
import com.trolltech.qt.core.Qt.WindowType;
import com.trolltech.qt.gui.QWidget;

public abstract class QWidgetExt
    extends QWidget
    implements
        Translatable
{
    public QWidgetExt()
    {
        super();
    }

    public QWidgetExt(
        QPrivateConstructor p )
    {
        super( p );
    }

    public QWidgetExt(
        QWidget parent )
    {
        super( parent );
    }

    public QWidgetExt(
        QWidget parent,
        WindowFlags f )
    {
        super( parent, f );
    }

    public QWidgetExt(
        QWidget parent,
        WindowType... f )
    {
        super( parent, f );
    }

    @Override
    public String tr(
        String source )
    {
        return Translator.tr( getClass().getName(), source );
    }

}
