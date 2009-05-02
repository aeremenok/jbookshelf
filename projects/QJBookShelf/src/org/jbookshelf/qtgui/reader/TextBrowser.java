package org.jbookshelf.qtgui.reader;

import java.util.Date;

import org.jbookshelf.model.Citation;
import org.jbookshelf.model.ModelFactory;

import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QFont;
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
{
    private final ReaderWindow readerWindow;

    public TextBrowser(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;

        // wait for the size to be stabilized to scroll
        verticalScrollBar().rangeChanged.connect( this, "rangeChanged(Integer,Integer)" );
        // selected text needed fot citating and bookmarking
        copyAvailable.connect( readerWindow.getCitation(), "setEnabled(boolean)" );
        copyAvailable.connect( readerWindow.getBookmark(), "setEnabled(boolean)" );
        readerWindow.getCitation().setEnabled( false );
        readerWindow.getBookmark().setEnabled( false );
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
        cit.setTitle( readerWindow.getBook().getName() + " " + cit.getCreationDate().toLocaleString() );
        cit.setCitation( textCursor().selectedText() );
        cit.setContent( "" );
        cit.setSubject( readerWindow.getBook() );
    }

    public void onClose()
    {
        // save current position
        final QScrollBar scrollBar = verticalScrollBar();
        readerWindow.getBook().setRead( (float) scrollBar.sliderPosition() / (float) scrollBar.maximum() );
    }

    @SuppressWarnings( "unused" )
    private void rangeChanged(
        final Integer min,
        final Integer max )
    {
        // todo apply only at first change
        final QScrollBar scrollBar = verticalScrollBar();
        scrollBar.setSliderPosition( (int) (readerWindow.getBook().getRead() * max) );
    }

    @Override
    protected void contextMenuEvent(
        final QContextMenuEvent e )
    {
        final QMenu menu = createStandardContextMenu();
        menu.addSeparator();
        menu.addAction( readerWindow.getCitation() );
        menu.addAction( readerWindow.getBookmark() );
        menu.exec( e.globalPos() );
    }
}
