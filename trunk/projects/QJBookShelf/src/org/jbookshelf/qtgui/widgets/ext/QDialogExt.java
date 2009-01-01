package org.jbookshelf.qtgui.widgets.ext;

import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;

import com.trolltech.qt.core.Qt.WindowFlags;
import com.trolltech.qt.core.Qt.WindowType;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QWidget;

public abstract class QDialogExt
    extends QDialog
    implements
        Translatable
{
    public QDialogExt()
    {
        super();
    }

    public QDialogExt(
        QPrivateConstructor p )
    {
        super( p );
    }

    public QDialogExt(
        QWidget parent )
    {
        super( parent );
    }

    public QDialogExt(
        QWidget parent,
        WindowFlags f )
    {
        super( parent, f );
    }

    public QDialogExt(
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
