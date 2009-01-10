package org.jbookshelf.qtgui.widgets.ext.autocomplete;

import java.util.List;

import com.trolltech.qt.gui.QLineEdit;

public class AutoCompleteDecorator
{
    public static void decorate(
        QLineEdit lineEdit,
        List suggests )
    {
        decorate( lineEdit, suggests, new ObjectToStringConverter()
        {
            public String toString(
                Object object )
            {
                return object.toString();
            }
        } );
    }

    public static void decorate(
        QLineEdit lineEdit,
        List suggests,
        ObjectToStringConverter converter )
    {
        AutoCompletePopup popup = new AutoCompletePopup( lineEdit, suggests, converter );
        popup.setCharLimit( 3 );
        // todo do something more
    }
}
