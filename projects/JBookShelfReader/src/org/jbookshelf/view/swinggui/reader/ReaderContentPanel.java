/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;

/**
 * @author eav 2009
 * @param <PageType>
 */
public abstract class ReaderContentPanel<PageType>
    extends JPanel
{
    protected final ReaderWindow<PageType> readerWindow;

    public ReaderContentPanel(
        final ReaderWindow<PageType> readerWindow )
    {
        super( new BorderLayout() );
        this.readerWindow = readerWindow;
    }

    public Note createNote(
        final String text )
    {
        final Note note = new Note();

        note.setCitation( text );

        final int currentPage = readerWindow.getReaderToolBar().getPaginator().getCurrentPage();
        note.setPage( currentPage + 1 );
        note.setPageCount( readerWindow.getBookContent().getPageCount() );

        note.setPosition( getPosition( note ) );

        note.setBook( readerWindow.getBook() );
        note.setTitle( NoteDialog.createTitle() );

        return note;
    }

    public abstract void highlightText(
        String text );

    public void reset()
    {}

    public abstract void setContent(
        PageType content );

    public void setReaderFont(
        @SuppressWarnings( "unused" ) final Font font )
    {}

    public void setScale(
        @SuppressWarnings( "unused" ) final int scalePercentage )
    {}

    protected abstract float getPosition(
        Note note );
}
