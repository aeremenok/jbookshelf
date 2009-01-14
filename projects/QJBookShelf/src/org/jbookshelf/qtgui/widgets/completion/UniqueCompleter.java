package org.jbookshelf.qtgui.widgets.completion;

import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.model.Unique;

import com.trolltech.qt.core.QObject;
import com.trolltech.qt.gui.QCompleter;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QCompleter.CompletionMode;
import com.trolltech.qt.gui.QCompleter.ModelSorting;

/**
 * Decorates the {@link QLineEdit} with completion popup todo comma-separated values
 * 
 * @author eav
 */
public class UniqueCompleter
{
    private static class CommaSeparatedCompleter
        extends QCompleter
    {
        public CommaSeparatedCompleter(
            List<String> completions,
            QObject parent )
        {
            super( completions, parent );
        }

        // todo
    }

    public static void decorate(
        QLineEdit lineEdit,
        List<? extends Unique> uniques )
    {
        CommaSeparatedCompleter completer = new CommaSeparatedCompleter( toStringList( uniques ), lineEdit.parent() );

        completer.setModelSorting( ModelSorting.CaseInsensitivelySortedModel );
        completer.setCompletionMode( CompletionMode.PopupCompletion );

        lineEdit.setCompleter( completer );
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
}