/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;

/**
 * @author eav 2009
 * @param <PageType>
 */
public abstract class ReaderContentPanel<PageType>
    extends JPanel
{
    public ReaderContentPanel()
    {
        super( new BorderLayout() );
    }

    public Note createNote(
        final String text )
    {
        final Note note = new Note();

        note.setCitation( text );

        final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );

        final int currentPage = Single.instance( Paginator.class ).getCurrentPage();
        note.setPage( currentPage + 1 );
        note.setPageCount( readerWindow.getBookContent().getPageCount() );

        note.setPosition( getPosition( note ) );

        note.setBook( readerWindow.getBook() );
        note.setTitle( NoteDialog.createTitle() );

        return note;
    }

    public abstract void goTo(
        final Bookmark bookmark );

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
        Bookmark note );
}
