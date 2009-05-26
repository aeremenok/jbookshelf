/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.qtgui.widgets.completion;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.qtgui.ToolBar;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.CaseSensitivity;
import com.trolltech.qt.core.Qt.Key;
import com.trolltech.qt.core.Qt.KeyboardModifiers;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QCompleter;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QKeyEvent;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QTextCursor;
import com.trolltech.qt.gui.QTextEdit;
import com.trolltech.qt.gui.QCompleter.CompletionMode;

/**
 * ported from this {@link http://doc.trolltech.com/4.2/tools-customcompleter.html}
 * 
 * @author eav
 */
public class CompletableTextEdit
    extends QTextEdit
    implements
        JBookShelfConstants,
        Translatable
{
    private QCompleter    c;

    /**
     * action for googling selected text
     */
    private final QAction googleAction = new QAction( new QIcon( ICONPATH + "google.png" ), "", this );

    public CompletableTextEdit()
    {
        super();

        googleAction.triggered.connect( this, "google()" );

        copyAvailable.connect( googleAction, "setEnabled(boolean)" );
        googleAction.setEnabled( false );

        Translator.addTranslatable( this );
    }

    public void retranslate()
    {
        googleAction.setText( Single.instance( ToolBar.class ).getGoogleAction().text() );
    }

    public void setCompleter(
        final QCompleter completer )
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
    private void google()
    {
        URIUtil.google( textCursor().selectedText() );
    }

    @SuppressWarnings( "unused" )
    private void insertCompletion(
        final String completion )
    {
        final QTextCursor tc = textCursor();
        final int extra = completion.length() - c.completionPrefix().length();
        tc.movePosition( QTextCursor.MoveOperation.Left );
        tc.movePosition( QTextCursor.MoveOperation.EndOfWord );
        tc.insertText( completion.substring( completion.length() - extra ) );
        setTextCursor( tc );
    }

    private String textUnderCursor()
    {
        final QTextCursor tc = textCursor();
        tc.select( QTextCursor.SelectionType.WordUnderCursor );
        return tc.selectedText();
    }

    @Override
    protected void contextMenuEvent(
        final QContextMenuEvent e )
    {
        final QMenu menu = super.createStandardContextMenu();
        menu.addSeparator();
        menu.addAction( googleAction );
        menu.exec( e.globalPos() );
    }

    @Override
    protected void keyPressEvent(
        final QKeyEvent e )
    {
        if ( c != null && c.popup().isVisible() )
        {
            // The following keys are forwarded by the completer to the widget
            final Key key = Qt.Key.resolve( e.key() );
            if ( key.equals( Qt.Key.Key_Enter ) || key.equals( Qt.Key.Key_Return ) || key.equals( Qt.Key.Key_Escape ) ||
                key.equals( Qt.Key.Key_Tab ) || key.equals( Qt.Key.Key_Backtab ) )
            {
                e.ignore();
                return; // let the completer do default behavior
            }
        }

        final KeyboardModifiers modifiers = e.modifiers();
        final boolean isShortcut =
            modifiers.isSet( Qt.KeyboardModifier.ControlModifier ) && e.key() == Qt.Key.Key_E.value(); // CTRL+E
        if ( c == null || !isShortcut )
        {
            super.keyPressEvent( e );
        }

        final boolean ctrlOrShift =
            modifiers.isSet( Qt.KeyboardModifier.ControlModifier ) ||
                modifiers.isSet( Qt.KeyboardModifier.ShiftModifier );
        final String text = e.text();
        final boolean empty = text.isEmpty();
        if ( c == null || ctrlOrShift && empty )
        {
            return;
        }

        final boolean hasModifier = !modifiers.isSet( Qt.KeyboardModifier.NoModifier ) && !ctrlOrShift;

        final String prefix = textUnderCursor();

        final boolean shortPrefix = prefix.length() < 3;
        final boolean hasEndOfWord = EOW.contains( text.substring( text.length() - 1 ) );
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

        final QRect cr = cursorRect();
        cr.setWidth( c.popup().sizeHintForColumn( 0 ) + c.popup().verticalScrollBar().sizeHint().width() );
        c.complete( cr ); // popup it up!
    }
}
