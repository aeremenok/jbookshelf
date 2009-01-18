package org.jbookshelf.qtgui.widgets.completion;

import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;

import com.trolltech.qt.core.QObject;
import com.trolltech.qt.gui.QCompleter;
import com.trolltech.qt.gui.QLineEdit;

/**
 * Decorates the {@link QLineEdit} with completion popup
 * 
 * @author eav
 */
public class CommaSeparatedCompleter
    extends QCompleter
    implements
        JBookShelfConstants
{
    public static void decorate(
        QLineEdit lineEdit,
        List<? extends Unique> uniques )
    {
        CommaSeparatedCompleter completer = new CommaSeparatedCompleter( toStringList( uniques ), lineEdit.parent() );

        completer.setModelSorting( ModelSorting.CaseInsensitivelySortedModel );
        completer.setCompletionMode( CompletionMode.PopupCompletion );

        completer.setWidget( lineEdit );

        lineEdit.textChanged.connect( completer, "textChanged(String)" );
    }

    private static List<String> toStringList(
        List<? extends Unique> uniques )
    {
        List<String> list = new ArrayList<String>();
        for ( Unique unique : uniques )
        {
            list.add( unique.getName() );
        }
        return list;
    }

    public CommaSeparatedCompleter(
        List<String> completions,
        QObject parent )
    {
        super( completions, parent );
        activated.connect( this, "insertCompletion(String)" );
    }

    public void textChanged(
        String text )
    {
        String prefix;
        int lastIndexOf = text.lastIndexOf( "," );
        if ( lastIndexOf > -1 )
        {
            prefix = text.substring( lastIndexOf + 1 );
            prefix = prefix.trim();
        }
        else
        {
            prefix = text;
        }

        if ( prefix.length() > 0 )
        {
            setCompletionPrefix( prefix );
            complete();
        }
    }

    private int[] getBounds(
        String text,
        int cursorPosition )
    {
        int left = cursorPosition - 1;
        int right = text.length() - cursorPosition;

        int leftPos = 0;
        int rightPos = text.length();

        for ( int i = left; i > 0; i-- )
        {
            char charAt = text.charAt( i );
            if ( charAt == ',' )
            {
                leftPos = i;
                break;
            }
        }

        for ( int i = left; i < right; i++ )
        {
            char charAt = text.charAt( i );
            if ( charAt == ',' )
            {
                rightPos = i;
                break;
            }
        }

        return new int[] { leftPos, rightPos };
    }

    @SuppressWarnings( "unused" )
    private void insertCompletion(
        String completion )
    {
        QLineEdit lineEdit = (QLineEdit) widget();
        int[] bounds = getBounds( lineEdit.text(), lineEdit.cursorPosition() );

        String left = "";
        if ( bounds[0] > 0 )
        {
            left = lineEdit.text().substring( 0, bounds[0] + 2 );
        }

        String text = left + completion + lineEdit.text().substring( bounds[1] );
        lineEdit.setText( text );
        popup().hide();
    }
}
