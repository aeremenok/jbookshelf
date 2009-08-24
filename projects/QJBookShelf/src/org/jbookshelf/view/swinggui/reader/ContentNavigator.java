/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class ContentNavigator<PageType>
    extends JToolBar
{
    @SuppressWarnings( "unused" )
    private static final Logger      log   = Logger.getLogger( ContentNavigator.class );

    private final JPanel             cards = new JPanel( new CardLayout() );
    private BookmarkPanel            bookmarkPanel;
    private ThumbnailPanel<PageType> thumbnailPanel;

    public ContentNavigator(
        final ReaderWindow<PageType> readerWindow,
        final List<String> features )
    {
        super( SwingConstants.VERTICAL );
        add( cards );
        if ( features.contains( Features.BOOKMARKS ) )
        {
            cards.add( bookmarkPanel = new BookmarkPanel(), Features.BOOKMARKS );
            bookmarkPanel.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.THUMBNAILS ) )
        {
            cards.add( thumbnailPanel = readerWindow.getFactory().createThumbnailPanel( readerWindow ),
                Features.THUMBNAILS );
            thumbnailPanel.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        // todo TOC
    }

    public void show(
        final String feature )
    {
        getCardLayout().show( cards, feature );
    }

    private CardLayout getCardLayout()
    {
        return (CardLayout) cards.getLayout();
    }
}
