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

import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translator;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QTextBrowser;

/**
 * displays comments and citations
 * 
 * @author eav
 */
public class CitationPanel
    extends QGroupBoxExt
{
    @SuppressWarnings( "unused" )
    private class CitationWidget
        extends QTextBrowser
    {
        public CitationWidget(
            final String text )
        {
            super();
            setText( text );
            setReadOnly( true );
        }
    }

    @SuppressWarnings( "unused" )
    private final ReaderWindow readerWindow;

    public CitationPanel(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;
        setLayout( new QGridLayout() );
        setVisible( false );
        Translator.addTranslatable( this );
    }

    public void retranslate()
    {
        setTitle( I18N.tr( "Citations" ) );
    }

    public void viewComments()
    {
        setVisible( !isVisible() );
        if ( isVisible() )
        { // refresh comments
            //            final QGridLayout layout = (QGridLayout) layout();
            //            final int count = layout.count();
            //            for ( int i = 0; i < count; i++ )
            //            {
            //                layout.removeItem( layout.itemAt( 0 ) );
            //            }
            //
            //            for ( final Comment comment : readerWindow.getBook().getComments() )
            //            {
            //                if ( comment instanceof Citation )
            //                {
            //                    final Citation cit = (Citation) comment;
            //                    layout.addWidget( new CitationWidget( cit.getCitation() ) );
            //                }
            //                else
            //                {
            //                    layout.addWidget( new CitationWidget( comment.getContent() ) );
            //                }
            //            }
        }
    }

}
