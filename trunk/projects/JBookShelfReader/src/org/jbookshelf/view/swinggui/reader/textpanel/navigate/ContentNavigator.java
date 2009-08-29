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
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class ContentNavigator<PageType>
    extends JToolBar
{
    @SuppressWarnings( "unused" )
    private static final Logger log   = Logger.getLogger( ContentNavigator.class );

    private final JPanel        cards = new JPanel( new CardLayout() );

    public ContentNavigator()
    {
        super( SwingConstants.VERTICAL );
        add( cards );

        final ReaderFactory<?> readerFactory = Single.instance( ReaderFactory.class );
        final List<String> features = readerFactory.getFeatures();
        if ( features.contains( ReaderFactory.BOOKMARKS ) )
        {
            cards.add( Single.instance( BookmarkPanel.class ), ReaderFactory.BOOKMARKS );
        }
        if ( features.contains( ReaderFactory.THUMBNAILS ) )
        {
            cards.add( Single.instance( ThumbnailPanel.class ), ReaderFactory.THUMBNAILS );
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
