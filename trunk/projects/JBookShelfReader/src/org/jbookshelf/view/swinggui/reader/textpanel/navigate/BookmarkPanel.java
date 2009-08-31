/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutablePanel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.SortOrder;

/**
 * @author eav 2009
 */
public class BookmarkPanel
    extends JPanel
{
    public static class BoookmarkTableModel
        extends DefaultTableModel
    {
        private static String[]      names     =
                                               { "#", I18N.tr( "Page" ), I18N.tr( "Pos" ) };

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

        @EventSubscriber( eventClass = Note.class )
        public void update(
            @SuppressWarnings( "unused" ) final Bookmark note )
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

    @SuppressWarnings( "unused" )
    private static final Logger       log   = Logger.getLogger( BookmarkPanel.class );
    private final BoookmarkTableModel model = new BoookmarkTableModel();
    private final JXTable             table = new JXTable( model );

    public BookmarkPanel()
    {
        super( new BorderLayout() );
        add( new JScrollPane( table ), BorderLayout.CENTER );
        model.update( null );

        table.setSortOrder( 0, SortOrder.ASCENDING );
        if ( !Single.instance( ReaderFactory.class ).getFeatures().contains( ReaderFactory.PAGING ) )
        {
            table.removeColumn( table.getColumn( 1 ) );
        }

        // todo resize automatically
        final Dimension mainSize = Single.instance( LayoutablePanel.class ).getCurrentPanels().getSize();
        final Dimension preferredScrollableViewportSize = table.getPreferredScrollableViewportSize();
        final Dimension size = new Dimension( preferredScrollableViewportSize.width, mainSize.height - 45 );
        table.setPreferredScrollableViewportSize( size );

        table.packAll();

        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        table.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                if ( e.getClickCount() == 2 )
                {
                    final int rowAtPoint = table.rowAtPoint( e.getPoint() );
                    if ( rowAtPoint > -1 )
                    {
                        final int selectedRow = table.convertRowIndexToModel( rowAtPoint );
                        final Bookmark bookmark = model.getBookmark( selectedRow );
                        Single.instance( LayoutablePanel.class ).getCurrentPanels().goTo( bookmark );
                    }
                }
            }
        } );
    }
}
