package org.jbookshelf.qtgui.widgets.ext;

import org.jbookshelf.qtgui.logic.Translator;

import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QWidget;

public class QLineEditExt
    extends QLineEdit
{
    public QLineEditExt()
    {
        super();
    }

    public QLineEditExt(
        QPrivateConstructor p )
    {
        super( p );
    }

    public QLineEditExt(
        QWidget parent )
    {
        super( parent );
    }

    public QLineEditExt(
        String arg__1 )
    {
        super( arg__1 );
    }

    public QLineEditExt(
        String arg__1,
        QWidget parent )
    {
        super( arg__1, parent );
    }

    @Override
    public String tr(
        String source )
    {
        return Translator.tr( getClass().getName(), source );
    }
}
