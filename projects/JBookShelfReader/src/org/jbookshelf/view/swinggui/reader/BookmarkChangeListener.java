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
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutablePanel;

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
            final Bookmark bookmark = Single.instance( LayoutablePanel.class ).getCurrentPanels().createBookmark();
            EventBus.publish( Bookmark.POSITION, bookmark );
            oldValue = newValue;
        }
    }
}