/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textview;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.navigation.ContentNavigators;
import org.jbookshelf.view.swinggui.reader.textview.pagelayout.OnePageLayout;
import org.jbookshelf.view.swinggui.reader.textview.pagelayout.PageLayout;
import org.jbookshelf.view.swinggui.reader.textview.pagelayout.TwoPageLayout;
import org.jbookshelf.view.swinggui.reader.toolbar.PageLayoutSwitcher;
import org.jbookshelf.view.swinggui.reader.toolbar.PageLayoutSwitcher.PageLayoutType;
import org.jbookshelf.view.swinggui.widget.CardLayoutExt;

/**
 * displays different page layouts
 * 
 * @author eav 2009
 * @param <PageType>
 */
public class MultiPageLayoutPanel<PageType>
    extends JPanel
{
    private final Map<String, Class<?>> availableLayouts = new HashMap<String, Class<?>>();
    private final JPanel                cards            = new JPanel( new CardLayoutExt() );

    /**
     * @return currently displayed layout
     */
    @SuppressWarnings( "unchecked" )
    public PageLayout<PageType> followLayouter()
    {
        final CardLayoutExt cardLayout = (CardLayoutExt) cards.getLayout();
        final String layoutName = Single.instance( PageLayoutSwitcher.class ).getCurrentLayout().name();

        // initialize if needed
        final Class<?> layoutClass = availableLayouts.get( layoutName );
        final PageLayout layoutPanel = (PageLayout) Single.instance( layoutClass );
        if ( !cardLayout.containsName( layoutName ) )
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

        add( Single.instance( ContentNavigators.class ), BorderLayout.WEST );
        add( cards, BorderLayout.CENTER );

        availableLayouts.put( PageLayoutType.ONE_PAGE.name(), OnePageLayout.class );
        availableLayouts.put( PageLayoutType.TWO_PAGES.name(), TwoPageLayout.class );
    }
}
