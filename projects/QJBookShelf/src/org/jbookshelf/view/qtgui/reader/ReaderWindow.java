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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.FileUtil;
import org.jbookshelf.controller.util.StringUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.JBookShelfConstants;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.widgets.dialog.BookEditDialog;

import com.trolltech.qt.core.Qt.ToolButtonStyle;
import com.trolltech.qt.core.Qt.WindowState;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QFontComboBox;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QSplitter;

public class ReaderWindow
    extends QMainWindow
    implements
    JBookShelfConstants
{
    private final class ReaderToolBar
        extends QToolBarExt
    {
        public void retranslate()
        {
            bookSettings.setText( I18N.tr( "Edit book properties" ) );
            citation.setText( I18N.tr( "Add citation" ) );
            view.setText( I18N.tr( "View bookmarks and citations" ) );
        }
    }

    private final QFontComboBox fontComboBox    = new QFontComboBox( this );

    private final QAction       bookSettings    = new QAction( new QIcon( ICONPATH + "document-properties.png" ), "",
                                                    this );
    private final QAction       citation        = new QAction( new QIcon( ICONPATH + "knotes.png" ), "", this );

    private final QAction       view            = new QAction( new QIcon( ICONPATH + "view-pim-notes.png" ), "", this );
    private final QComboBox     charsetComboBox = new QComboBox( this );

    private final Book          book;
    private final TextBrowser   textBrowser     = new TextBrowser( this );

    private final CitationPanel citationPanel   = new CitationPanel( this );

    private byte[]              contentBytes;

    private final QToolBarExt   toolBar         = new ReaderToolBar();

    public ReaderWindow(
        final Book book )
    {
        super();
        setWindowIcon( new QIcon( ICONPATH + "logo-64.png" ) );
        this.book = book;

        initComponents();
        initListeners();

        Translator.addTranslatable( toolBar );
    }

    public Book getBook()
    {
        return book;
    }

    public QAction getBookSettings()
    {
        return bookSettings;
    }

    public QAction getCitation()
    {
        return citation;
    }

    @SuppressWarnings( "unused" )
    private void charsetChanged(
        final String charset )
    {
        book.getPhysicalBook().setCharsetName( charset );
        textBrowser.savePosition();
        // reload text
        textBrowser.setText( getContent() );
    }

    @SuppressWarnings( "unused" )
    private void editBook()
    {
        new BookEditDialog( book ).setVisible( true );
    }

    /**
     * @return book file content
     */
    private String getContent()
    {
        final File file = Single.instance( Viewer.class ).getFile( book );
        try
        {
            // define which encoding to use
            String charset = book.getPhysicalBook().getCharsetName();
            if ( charset == null )
            {
                charset = FileUtil.guessFileEncoding( file );
                book.getPhysicalBook().setCharsetName( charset );
            }
            if ( contentBytes == null )
            { // cache the contents
                contentBytes = FileUtil.getBytesFromFile( file );
            }
            return new String( contentBytes, charset );
        }
        catch ( final IOException e )
        {
            e.printStackTrace();
            return tr( "Error displaying file " ) + file.getAbsolutePath() + "\n\n" + StringUtil.printThrowable( e );
        }
    }

    /**
     * initialize the charsetComboBox
     */
    private void initCharsets()
    {// todo make static
        charsetComboBox.setEditable( true );

        final SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        for ( final String charsetName : availableCharsets.keySet() )
        {
            charsetComboBox.addItem( availableCharsets.get( charsetName ).name() );
        }

        final String currentCharset = book.getPhysicalBook().getCharsetName();
        final int currentCharsetIndex = charsetComboBox.findText( currentCharset );
        if ( currentCharsetIndex > -1 )
        {
            charsetComboBox.setCurrentIndex( currentCharsetIndex );
        }
        else
        { // charset not available (probably, wrong text case)
            charsetComboBox.addItem( currentCharset );
            charsetComboBox.setCurrentIndex( charsetComboBox.count() - 1 );
        }
    }

    private void initComponents()
    {
        setWindowTitle( book.getName() );
        setWindowState( WindowState.WindowMaximized );

        final QSplitter splitter = new QSplitter();
        splitter.addWidget( textBrowser );
        splitter.addWidget( citationPanel );
        setCentralWidget( splitter );

        toolBar.setToolButtonStyle( ToolButtonStyle.ToolButtonTextBesideIcon );
        addToolBar( toolBar );
        toolBar.addWidget( fontComboBox );
        toolBar.addWidget( charsetComboBox );
        toolBar.addSeparator();
        toolBar.addAction( bookSettings );
        toolBar.addAction( citation );
        toolBar.addSeparator();
        toolBar.addAction( view );

        // todo break huge content into parts
        textBrowser.setText( getContent() );
        initCharsets();
    }

    private void initListeners()
    {
        fontComboBox.currentFontChanged.connect( textBrowser, "changeFont(QFont)" );
        charsetComboBox.currentStringChanged.connect( this, "charsetChanged(String)" );

        bookSettings.triggered.connect( this, "editBook()" );

        citation.triggered.connect( textBrowser, "citate()" );
        view.triggered.connect( citationPanel, "viewComments()" );
    }

    @Override
    protected void closeEvent(
        final QCloseEvent arg__1 )
    {
        textBrowser.savePosition();
        super.closeEvent( arg__1 );
    }
}
