package org.jbookshelf.qtgui.reader;

import java.util.Date;

import org.jbookshelf.controller.URIOpener;
import org.jbookshelf.model.Citation;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.qtgui.ToolBar;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;

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
    { // todo jambi cannot find this slot somehow
        setFont( font );
    }

    public void citate()
    {
        final Citation cit = ModelFactory.eINSTANCE.createCitation();
        cit.setCreationDate( new Date() );
        cit.setCitation( textCursor().selectedText() );
        cit.setTitle( cit.getCitation().substring( 1, 21 ) + "..." );
        cit.setContent( "" );
        cit.setSubject( readerWindow.getBook() );
        cit.setPosition( textCursor().position() );
    }

    public void retranslate()
    {
        googleAction.setText( ToolBar.getInstance().getGoogleAction().text() );
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
        URIOpener.google( textCursor().selectedText() );
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
