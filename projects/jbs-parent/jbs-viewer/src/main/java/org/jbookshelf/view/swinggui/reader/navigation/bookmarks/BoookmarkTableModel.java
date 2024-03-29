/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.navigation.bookmarks;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.main.ProgressBar;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;

/**
 * contains all {@link Bookmark}s for a displayed {@link Book}
 * 
 * @author eav 2009
 */
public class BoookmarkTableModel
    extends DefaultTableModel
{
    private static String[]      names     =
                                           { "#", I18N.tr( "Page" ), I18N.tr( "Position" ) };

    private final List<Bookmark> bookmarks = new ArrayList<Bookmark>();

    public BoookmarkTableModel()
    {
        super( names, 0 );
        AnnotationProcessor.process( this );
    }

    public Bookmark getBookmark(
        final int row )
    {
        return bookmarks.get( row );
    }

    @Override
    public int getRowCount()
    {
        return bookmarks != null
            ? bookmarks.size() : 0;
    }

    @Override
    public Object getValueAt(
        final int row,
        final int column )
    {
        final Bookmark bookmark = bookmarks.get( row );
        switch ( column )
        {
            case 0:
                return bookmark.getId();
            case 1:
                return bookmark.getPage() != null
                    ? bookmark.getPage() + "/" + bookmark.getPageCount() : "";
            case 2:
                final String pos = bookmark.getPosition().toString();
                if ( pos.length() > 4 )
                {
                    return pos.substring( 0, 4 );
                }
                return pos;
        }
        return super.getValueAt( row, column );
    }

    @Override
    public boolean isCellEditable(
        final int row,
        final int column )
    {
        return false;
    }

    /**
     * if any Note has been changed - reload list
     * 
     * @param note changed note
     */
    @EventSubscriber( eventClass = Note.class )
    public void reloadNotes(
        @SuppressWarnings( "unused" ) final Note note )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<Object, Object>()
        {
            @Override
            protected Object doInBackground()
            {
                final Book book = Single.instance( ReaderWindow.class ).getBook();
                bookmarks.clear();
                bookmarks.addAll( BookShelf.getNotes( book ) );
                return null;
            }

            @Override
            protected void doneSafe()
            {
                fireTableDataChanged();
            }
        } );
    }
}