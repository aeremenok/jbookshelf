/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.navigation.thumbnails;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.Border;

import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 * displays a page in a small panel
 * 
 * @author eav 2009
 */
public abstract class Thumbnail
    extends JPanel
{
    protected final Border deselectedBorder = new DropShadowBorder();
    protected final Border selectedBorder   = new DropShadowBorder( Color.GREEN, 5 );

    protected int          pageNumber       = 0;

    public Thumbnail()
    {
        super( new BorderLayout() );
        setBorder( deselectedBorder );
        AnnotationProcessor.process( this );
    }

    public abstract void displayPage(
        int pageNumber );

    @EventTopicSubscriber( topic = Bookmark.PAGE )
    public void onPageChanged(
        @SuppressWarnings( "unused" ) final String topic,
        @SuppressWarnings( "unused" ) final Bookmark bookmark )
    {
        // highlight if a page is current 
        final int currentPage = Single.instance( Paginator.class ).getCurrentPage();
        setBorder( currentPage == pageNumber
            ? selectedBorder : deselectedBorder );
    }
}