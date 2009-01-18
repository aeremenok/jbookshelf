package org.jbookshelf.qtgui.widgets.completion;

import org.jbookshelf.qtgui.logic.JBookShelfConstants;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.CaseSensitivity;
import com.trolltech.qt.core.Qt.Key;
import com.trolltech.qt.core.Qt.KeyboardModifiers;
import com.trolltech.qt.gui.QCompleter;
import com.trolltech.qt.gui.QKeyEvent;
import com.trolltech.qt.gui.QTextCursor;
import com.trolltech.qt.gui.QTextEdit;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QCompleter.CompletionMode;

/**
 * ported from this {@link http://doc.trolltech.com/4.2/tools-customcompleter.html}
 * 
 * @author eav
 */
public class CompletableTextEdit
    extends QTextEdit
    implements
        JBookShelfConstants
{
    private QCompleter c;

    public CompletableTextEdit()
    {
        super();
    }

    public CompletableTextEdit(
        QPrivateConstructor p )
    {
        super( p );
    }

    public CompletableTextEdit(
        QWidget parent )
    {
        super( parent );
    }

    public CompletableTextEdit(
        String text )
    {
        super( text );
    }

    public CompletableTextEdit(
        String text,
        QWidget parent )
    {
        super( text, parent );
    }

    public void setCompleter(
        QCompleter completer )
    {
        if ( this.c != null )
        {
            this.c.disconnect();
        }

        this.c = completer;
        if ( completer == null )
        {
            return;
        }

        completer.setWidget( this );
        completer.setCompletionMode( CompletionMode.PopupCompletion );
        completer.setCaseSensitivity( CaseSensitivity.CaseInsensitive );

        completer.activated.connect( this, "insertCompletion(String)" );
    }

    @SuppressWarnings( "unused" )
    private void insertCompletion(
        String completion )
    {
        QTextCursor tc = textCursor();
        int extra = completion.length() - c.completionPrefix().length();
        tc.movePosition( QTextCursor.MoveOperation.Left );
        tc.movePosition( QTextCursor.MoveOperation.EndOfWord );
        tc.insertText( completion.substring( completion.length() - extra ) );
        setTextCursor( tc );
    }

    private String textUnderCursor()
    {
        QTextCursor tc = textCursor();
        tc.select( QTextCursor.SelectionType.WordUnderCursor );
        return tc.selectedText();
    }

    @Override
    protected void keyPressEvent(
        QKeyEvent e )
    {
        if ( c != null && c.popup().isVisible() )
        {
            // The following keys are forwarded by the completer to the widget
            Key key = Qt.Key.resolve( e.key() );
            if ( key.equals( Qt.Key.Key_Enter ) || key.equals( Qt.Key.Key_Return ) || key.equals( Qt.Key.Key_Escape ) ||
                key.equals( Qt.Key.Key_Tab ) || key.equals( Qt.Key.Key_Backtab ) )
            {
                e.ignore();
                return; // let the completer do default behavior
            }
        }

        KeyboardModifiers modifiers = e.modifiers();
        boolean isShortcut = modifiers.isSet( Qt.KeyboardModifier.ControlModifier ) && e.key() == Qt.Key.Key_E.value(); // CTRL+E
        if ( c == null || !isShortcut )
        {
            super.keyPressEvent( e );
        }

        boolean ctrlOrShift =
            modifiers.isSet( Qt.KeyboardModifier.ControlModifier ) ||
                modifiers.isSet( Qt.KeyboardModifier.ShiftModifier );
        String text = e.text();
        boolean empty = text.isEmpty();
        if ( c == null || ctrlOrShift && empty )
        {
            return;
        }

        boolean hasModifier = !modifiers.isSet( Qt.KeyboardModifier.NoModifier ) && !ctrlOrShift;

        String prefix = textUnderCursor();

        boolean shortPrefix = prefix.length() < 3;
        boolean hasEndOfWord = EOW.contains( text.substring( text.length() - 1 ) );
        if ( !isShortcut && (hasModifier || empty || shortPrefix || hasEndOfWord) )
        {
            c.popup().hide();
            return;
        }

        if ( !prefix.equals( c.completionPrefix() ) )
        {
            c.setCompletionPrefix( prefix );
            c.popup().setCurrentIndex( c.completionModel().index( 0, 0 ) );
        }

        QRect cr = cursorRect();
        cr.setWidth( c.popup().sizeHintForColumn( 0 ) + c.popup().verticalScrollBar().sizeHint().width() );
        c.complete( cr ); // popup it up!
    }
}
