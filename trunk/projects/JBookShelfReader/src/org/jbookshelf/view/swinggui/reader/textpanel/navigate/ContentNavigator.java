/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
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

    @SuppressWarnings( "unchecked" )
    public ContentNavigator(
        final List<String> features )
    {
        super( SwingConstants.VERTICAL );
        add( cards );

        final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );
        if ( features.contains( Features.BOOKMARKS ) )
        {
            cards.add( bookmarkPanel = new BookmarkPanel(), Features.BOOKMARKS );
            bookmarkPanel.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.THUMBNAILS ) )
        {
            cards.add( thumbnailPanel = Single.instance( ThumbnailPanel.class ), Features.THUMBNAILS );
            thumbnailPanel.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        // todo TOC

        setVisible( false );
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
