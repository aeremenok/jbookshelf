package org.jbookshelf.view.qtgui.reader;

import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.i18n.Translator;

import com.trolltech.qt.gui.QGroupBox;
import com.trolltech.qt.gui.QWidget;

public abstract class QGroupBoxExt
    extends QGroupBox
    implements
    Translatable
{
    public QGroupBoxExt()
    {
        super();
    }

    public QGroupBoxExt(
        final QPrivateConstructor p )
    {
        super( p );
    }

    public QGroupBoxExt(
        final QWidget parent )
    {
        super( parent );
    }

    public QGroupBoxExt(
        final String title )
    {
        super( title );
    }

    public QGroupBoxExt(
        final String title,
        final QWidget parent )
    {
        super( title, parent );
    }

    @Override
    public String tr(
        final String source )
    {
        return Translator.tr( getClass().getName(), source );
    }

}
