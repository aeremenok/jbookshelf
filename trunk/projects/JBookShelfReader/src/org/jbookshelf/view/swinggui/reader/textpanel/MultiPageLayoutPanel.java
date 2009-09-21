/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.ContentNavigator;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter.PageLayoutType;

/**
 * displays different page layouts
 * 
 * @author eav 2009
 * @param <PageType>
 */
public class MultiPageLayoutPanel<PageType>
    extends JPanel
{
    private static Map<String, Class<?>> availableLayouts = new HashMap<String, Class<?>>();
    static
    {
        availableLayouts.put( PageLayoutType.ONE_PAGE.name(), OnePageLayout.class );
        availableLayouts.put( PageLayoutType.TWO_PAGES.name(), TwoPageLayout.class );
    }

    private final JPanel                 cards            = new JPanel( new CardLayoutExt() );

    /**
     * @return currently displayed layout
     */
    @SuppressWarnings( "unchecked" )
    public PageLayout<PageType> followLayouter()
    {
        final CardLayoutExt cardLayout = (CardLayoutExt) cards.getLayout();
        final String layoutName = Single.instance( Layouter.class ).getCurrentLayout().name();

        // initialize if needed
        final PageLayout layoutPanel = (PageLayout) Single.instance( availableLayouts.get( layoutName ) );
        if ( !cardLayout.contains( layoutPanel ) )
        {
            cards.add( layoutPanel, layoutName );
        }

        cardLayout.show( cards, layoutName );

        return layoutPanel;
    }

    @PostConstruct
    public void init()
    {
        setLayout( new BorderLayout() );

        add( Single.instance( ContentNavigator.class ), BorderLayout.WEST );
        add( cards, BorderLayout.CENTER );
    }
}
