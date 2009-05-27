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
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */

package org.jbookshelf.view.qtgui.reader;

import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.view.logic.JBookShelfConstants;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QScrollBar;
import com.trolltech.qt.gui.QTextBrowser;

/**
 * displays book text
 * 
 * @author eav
 */
public class TextBrowser
    extends QTextBrowser
    implements
    JBookShelfConstants,
    Translatable
{
    private final ReaderWindow readerWindow;

    /**
     * action for googling selected text
     */
    private final QAction      googleAction = new QAction( new QIcon( ICONPATH + "google.png" ), "", this );

    public TextBrowser(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;
        setReadOnly( true );

        // wait for the size to be stabilized to scroll
        verticalScrollBar().rangeChanged.connect( this, "rangeChanged(Integer,Integer)" );

        // selected text needed fot citating and bookmarking
        copyAvailable.connect( readerWindow.getCitation(), "setEnabled(boolean)" );
        copyAvailable.connect( googleAction, "setEnabled(boolean)" );

        readerWindow.getCitation().setEnabled( false );
        googleAction.setEnabled( false );

        googleAction.triggered.connect( this, "google()" );

        Translator.addTranslatable( this );
    }

    public void changeFont(
        final QFont font )
    { // todo jambi cannot find this slot %)
        setFont( font );
    }

    public void citate()
    {
    //        final Citation cit = ModelFactory.eINSTANCE.createCitation();
    //        cit.setCreationDate( new Date() );
    //        cit.setCitation( textCursor().selectedText() );
    //        cit.setTitle( cit.getCitation().substring( 1, 21 ) + "..." );
    //        cit.setContent( "" );
    //        cit.setSubject( readerWindow.getBook() );
    //        cit.setPosition( textCursor().position() );
    }

    public void retranslate()
    {
    //        googleAction.setText( Single.instance( ToolBar.class ).getGoogleAction().text() );
    }

    public void savePosition()
    {
        // save current position
        final QScrollBar scrollBar = verticalScrollBar();
        readerWindow.getBook().setRead( (float) scrollBar.sliderPosition() / (float) scrollBar.maximum() );
    }

    @SuppressWarnings( "unused" )
    private void google()
    {
        URIUtil.google( textCursor().selectedText() );
    }

    @SuppressWarnings( "unused" )
    private void rangeChanged(
        final Integer min,
        final Integer max )
    {
        // todo apply only at first change
        final QScrollBar scrollBar = verticalScrollBar();
        scrollBar.setSliderPosition( (int) (readerWindow.getBook().getRead() * scrollBar.maximum()) );
    }

    @Override
    protected void contextMenuEvent(
        final QContextMenuEvent e )
    {
        final QMenu menu = createStandardContextMenu();
        menu.addSeparator();
        menu.addAction( readerWindow.getBookSettings() );
        menu.addSeparator();
        menu.addAction( readerWindow.getCitation() );
        menu.addAction( googleAction );
        menu.exec( e.globalPos() );
    }
}
