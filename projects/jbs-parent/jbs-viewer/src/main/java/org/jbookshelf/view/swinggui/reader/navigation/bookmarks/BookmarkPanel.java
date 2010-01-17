/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.navigation.bookmarks;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;
import org.jbookshelf.view.swinggui.reader.textview.MultiPageLayoutPanel;
import org.jbookshelf.view.swinggui.reader.textview.pagelayout.PageLayout;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.SortOrder;

/**
 * displays bookmarks in a simple table
 * 
 * @author eav 2009
 */
public class BookmarkPanel
    extends JPanel
{
    public static class BookmarkTable
        extends JXTable
    {
        public BookmarkTable()
        {
            super( new BoookmarkTableModel() );
            this.setSortOrder( 0, SortOrder.ASCENDING );

            final List supportedFeatures = Single.instance( ReaderSpecific.class ).getSupportedFeatures();
            if ( !supportedFeatures.contains( ReaderSpecific.PAGING ) )
            { // do not display pages if paging is not supported
                this.removeColumn( this.getColumn( 1 ) );
            }
            this.packAll();

            this.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
            this.addMouseListener( new MouseAdapter()
            {
                @Override
                public void mouseClicked(
                    final MouseEvent e )
                {
                    if ( e.getClickCount() == 2 )
                    {
                        final int rowAtPoint = rowAtPoint( e.getPoint() );
                        if ( rowAtPoint > -1 )
                        {
                            goToBookmark( rowAtPoint );
                        }
                    }
                }
            } );
        }

        @Override
        public BoookmarkTableModel getModel()
        {
            return (BoookmarkTableModel) super.getModel();
        }

        private void goToBookmark(
            final int bookmarkRow )
        {
            final int selectedRow = convertRowIndexToModel( bookmarkRow );
            final Bookmark bookmark = getModel().getBookmark( selectedRow );
            final PageLayout pageLayout = Single.instance( MultiPageLayoutPanel.class ).followLayouter();
            pageLayout.goTo( bookmark );
        }
    }

    public BookmarkPanel()
    {
        super( new BorderLayout() );

        final BookmarkTable table = new BookmarkTable();
        add( new JScrollPane( table ), BorderLayout.CENTER );
        table.getModel().reloadNotes( null );
    }
}
