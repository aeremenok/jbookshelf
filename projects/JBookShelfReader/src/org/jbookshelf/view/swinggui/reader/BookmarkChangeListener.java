/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.swinggui.reader.textpanel.MultiPageLayoutPanel;
import org.jbookshelf.view.swinggui.reader.textpanel.PageLayout;

/**
 * monitors {@link JScrollBar} scrolling and fires events for {@link Note} reloading
 * 
 * @author eav 2009
 */
public class BookmarkChangeListener
    implements
    ChangeListener
{
    private int oldValue = 0;

    @Override
    public void stateChanged(
        final ChangeEvent e )
    {
        final BoundedRangeModel model = (BoundedRangeModel) e.getSource();
        final int newValue = model.getValue();
        if ( !model.getValueIsAdjusting() && Math.abs( newValue - oldValue ) > model.getExtent() )
        {
            final PageLayout pageLayout = Single.instance( MultiPageLayoutPanel.class ).followLayouter();
            EventBus.publish( Bookmark.POSITION, pageLayout.createBookmark() );
            oldValue = newValue;
        }
    }
}