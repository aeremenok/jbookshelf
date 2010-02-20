/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.navigation;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;
import org.jbookshelf.view.swinggui.reader.navigation.bookmarks.BookmarkPanel;
import org.jbookshelf.view.swinggui.reader.navigation.thumbnails.ThumbnailPanel;
import org.jbookshelf.view.swinggui.reader.widget.CardLayoutExt;

/**
 * displays components that provide content navigation
 * 
 * @author eav 2009
 * @param <PageType> displayed page type
 */
public class ContentNavigators<PageType>
    extends JPanel
{
    private final JPanel                                   cards               = new JPanel( new CardLayoutExt() );
    private final Map<String, Class<? extends JComponent>> availableNavigators = new HashMap<String, Class<? extends JComponent>>();

    public ContentNavigators()
    {
        super( new BorderLayout() );
        add( cards, BorderLayout.CENTER );
        setVisible( false );

        availableNavigators.put( ReaderSpecific.BOOKMARKS, BookmarkPanel.class );
        availableNavigators.put( ReaderSpecific.THUMBNAILS, ThumbnailPanel.class );
        // todo TOC
    }

    /**
     * show navigator for specified feature
     * 
     * @param navigatingFeature {@link ReaderSpecific#BOOKMARKS} or {@link ReaderSpecific#THUMBNAILS}
     */
    public void show(
        final String navigatingFeature )
    {
        final boolean isVisible = navigatingFeature != null;
        setVisible( isVisible );
        if ( isVisible )
        {
            final CardLayoutExt cardLayout = (CardLayoutExt) cards.getLayout();
            if ( !cardLayout.containsName( navigatingFeature ) )
            {
                final Class<? extends JComponent> navigatorClass = availableNavigators.get( navigatingFeature );
                cards.add( Single.instance( navigatorClass ), navigatingFeature );
            }
            cardLayout.show( cards, navigatingFeature );
        }
    }
}
