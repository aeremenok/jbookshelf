/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textview;

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
 * displays book content
 * 
 * @author eav 2009
 * @param <PageType> displayed page type
 */
public abstract class ContentRenderer<PageType>
    extends JPanel
{
    public ContentRenderer()
    {
        super( new BorderLayout() );
    }

    public Note createNote(
        final String citationText )
    {
        final Note note = new Note();

        note.setCitation( citationText );

        final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );

        final int currentPage = Single.instance( Paginator.class ).getCurrentPage();
        note.setPage( currentPage );
        note.setPageCount( readerWindow.getBookContent().getPageCount() );

        note.setPosition( calcRelativePosition( note ) );

        note.setBook( readerWindow.getBook() );
        note.setTitle( NoteDialog.createTitle() );

        return note;
    }

    public abstract void displayContent(
        PageType content );

    public abstract void goTo(
        final Bookmark bookmark );

    public abstract void highlightText(
        String text );

    public void scale(
        @SuppressWarnings( "unused" ) final int scalePercentage )
    {}

    public void useFont(
        @SuppressWarnings( "unused" ) final Font font )
    {}

    /**
     * pageable viewers calculate relative position (page/pageCount)<br>
     * non-pageable get it from vertical scrollbar
     * 
     * @param bookmark page and page count
     * @return relative position of the bookmark
     */
    protected abstract float calcRelativePosition(
        Bookmark bookmark );
}
