/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.reader.textpanel.MultiPageLayoutPanel;

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
            final Bookmark bookmark = Single.instance( MultiPageLayoutPanel.class ).followLayouter().createBookmark();
            EventBus.publish( Bookmark.POSITION, bookmark );
            oldValue = newValue;
        }
    }
}