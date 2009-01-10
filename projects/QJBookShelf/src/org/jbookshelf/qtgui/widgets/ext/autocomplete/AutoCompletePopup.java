package org.jbookshelf.qtgui.widgets.ext.autocomplete;

import java.util.List;

import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMenu;

public class AutoCompletePopup
    extends QMenu
{
    private final QLineEdit               lineEdit;
    private final List                    suggests;
    private final ObjectToStringConverter converter;
    private int                           charLimit;

    public AutoCompletePopup(
        QLineEdit lineEdit,
        List suggests,
        ObjectToStringConverter converter )
    {
        this.lineEdit = lineEdit;
        this.suggests = suggests;
        this.converter = converter;

        lineEdit.textEdited.connect( this, "suggest(String)" );
    }

    public void setCharLimit(
        int charLimit )
    {
        this.charLimit = charLimit;
    }

    public void suggest(
        String text )
    {
        if ( charLimit <= text.length() )
        {
            clear();

            searchSuggests( text );

            exec( lineEdit.mapToGlobal( lineEdit.geometry().bottomLeft() ) );
            lineEdit.setFocus();
        }
    }

    private void searchSuggests(
        String text )
    {
        String lowerCase = text.toLowerCase();
        for ( Object object : suggests )
        {
            String candidate = converter.toString( object );
            if ( candidate.toLowerCase().startsWith( lowerCase ) )
            {
                addAction( candidate );
            }
        }
    }
}
