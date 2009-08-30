/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class ContentNavigator<PageType>
    extends JPanel
{
    @SuppressWarnings( "unused" )
    private static final Logger                            log     = Logger.getLogger( ContentNavigator.class );

    private final JPanel                                   cards   = new JPanel( new CardLayout() );
    private final Map<String, Class<? extends JComponent>> classes = new HashMap<String, Class<? extends JComponent>>();

    public ContentNavigator()
    {
        super();
        add( cards, BorderLayout.CENTER );
        setVisible( false );
        classes.put( ReaderFactory.BOOKMARKS, BookmarkPanel.class );
        classes.put( ReaderFactory.THUMBNAILS, ThumbnailPanel.class );
        // todo TOC
    }

    public void show(
        final String feature )
    {
        setVisible( feature != null );
        if ( isVisible() )
        {
            final Class<? extends JComponent> clazz = classes.get( feature );
            if ( clazz != null )
            {
                final JComponent jComponent = Single.instance( clazz );
                cards.add( jComponent, feature );
                classes.remove( feature );
            }

            final CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show( cards, feature );
        }
    }
}
