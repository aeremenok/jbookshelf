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
package org.jbookshelf.qtgui.reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

import org.jbookshelf.controller.FileHandler;
import org.jbookshelf.controller.URIOpener;
import org.jbookshelf.controller.ZIPHandler;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.qtgui.MainWindow;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.dialog.BookEditDialog;
import org.jbookshelf.qtgui.widgets.ext.QToolBarExt;

import com.trolltech.qt.core.Qt.ToolButtonStyle;
import com.trolltech.qt.core.Qt.WindowState;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QFontComboBox;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QSplitter;
import com.trolltech.qt.gui.QWidget;

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
            bookSettings.setText( tr( "Edit book properties" ) );
            citation.setText( tr( "Add citation" ) );
            view.setText( tr( "View bookmarks and citations" ) );
        }
    }

    private final QFontComboBox   fontComboBox    = new QFontComboBox( this );
    private final QAction         bookSettings    =
                                                      new QAction( new QIcon( ICONPATH + "document-properties.png" ),
                                                          "", this );
    private final QAction         citation        = new QAction( new QIcon( ICONPATH + "knotes.png" ), "", this );
    private final QAction         view            =
                                                      new QAction( new QIcon( ICONPATH + "view-pim-notes.png" ), "",
                                                          this );

    private final QComboBox       charsetComboBox = new QComboBox( this );
    private final Book            book;

    private final TextBrowser     textBrowser;
    private final CitationPanel   citationPanel   = new CitationPanel( this );

    private byte[]                contentBytes;
    private final static String[] extensions      = { "txt", "html", "htm", "shtml"
                                                  // todo import rich text
                                                      // , "doc", "odt", "rtf"
                                                      };

    private final QToolBarExt     toolBar         = new ReaderToolBar();

    public static void open(
        final QWidget parent,
        final Book book )
    {
        try
        {
            final File file = getFile( book );
            // define which viewer to use
            String viewer = book.getPhysical().getViewer();
            if ( viewer == null )
            {
                viewer = canBeOpened( file ) ? PhysicalUnit.INTERNAL_VIEWER : PhysicalUnit.SYSTEM_VIEWER;
                book.getPhysical().setViewer( viewer );
            }

            if ( PhysicalUnit.INTERNAL_VIEWER.equals( viewer ) )
            { // internal
                new ReaderWindow( parent, book ).show();
            }
            else
            { // system default
                URIOpener.browseFile( file );
            }
        }
        catch ( final Throwable e )
        {
            e.printStackTrace();
            final String string = "Error opening book " + book.getName() + "\n\n" + FileHandler.printThrowable( e );
            QMessageBox.critical( parent, "Error", string );
        }
    }

    private static boolean canBeOpened(
        final File file )
    {
        final String lowerCase = file.getName().toLowerCase();
        for ( final String string : extensions )
        {
            if ( lowerCase.endsWith( "." + string ) )
            {
                return true;
            }
        }
        return false;
    }

    private static File getFile(
        final Book book )
    {
        final PhysicalUnit physical = book.getPhysical();
        if ( physical instanceof ArchiveFile )
        {
            final ArchiveFile archiveFile = (ArchiveFile) physical;
            if ( archiveFile.getArchiveFile() == null || !archiveFile.getArchiveFile().exists() )
            { // unpack and remember the file
                final QMessageBox messageBox = new QMessageBox( Singletons.instance( MainWindow.class ) );
                messageBox.setWindowTitle( "Unpacking. Please wait..." );
                messageBox.show();

                final File zippedFileToOpen = ZIPHandler.getZippedFileToOpen( archiveFile.getFile() );
                archiveFile.setArchiveFile( zippedFileToOpen );

                messageBox.hide();
            }
            return archiveFile.getArchiveFile();
        }

        return physical.getFile();
    }

    public ReaderWindow(
        final QWidget parent,
        final Book book )
    {
        super( parent );
        this.book = book;
        textBrowser = new TextBrowser( this );

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
        book.getPhysical().setCharset( charset );
        textBrowser.savePosition();
        // reload text
        textBrowser.setText( getContent() );
    }

    @SuppressWarnings( "unused" )
    private void editBook()
    {
        new BookEditDialog( this, book ).show();
    }

    /**
     * @return book file content
     */
    private String getContent()
    {
        final File file = getFile( book );
        try
        {
            // define which encoding to use
            String charset = book.getPhysical().getCharset();
            if ( charset == null )
            {
                charset = FileHandler.guessEncoding( file );
                book.getPhysical().setCharset( charset );
            }
            if ( contentBytes == null )
            { // cache the contents
                contentBytes = FileHandler.getBytesFromFile( file );
            }
            return new String( contentBytes, charset );
        }
        catch ( final IOException e )
        {
            e.printStackTrace();
            return tr( "Error displaying file " ) + file.getAbsolutePath() + "\n\n" + FileHandler.printThrowable( e );
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

        final String currentCharset = book.getPhysical().getCharset();
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

        toolBar.setToolButtonStyle( ToolButtonStyle.ToolButtonTextUnderIcon );
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
