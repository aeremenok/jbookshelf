/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter.PageLayout;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class LayoutSwitcher<PageType>
    extends JPanel
{
    @SuppressWarnings( "unused" )
    private static final Logger                                  log           = Logger
                                                                                   .getLogger( LayoutSwitcher.class );

    private PageLayout                                           currentLayout = PageLayout.ONE_PAGE;
    private final ReaderWindow<PageType>                         readerWindow;
    private final ReaderFactory<PageType>                        factory;

    private final Map<PageLayout, ReaderContentPanels<PageType>> panels        = new HashMap<PageLayout, ReaderContentPanels<PageType>>();

    public LayoutSwitcher(
        final ReaderWindow<PageType> readerWindow,
        final ReaderFactory<PageType> factory )
    {
        super( new CardLayout() );
        this.readerWindow = readerWindow;
        this.factory = factory;
    }

    public PageLayout getCurrentLayout()
    {
        return this.currentLayout;
    }

    public ReaderContentPanels<PageType> getCurrentPanels()
    {
        ReaderContentPanels<PageType> contentPanels = panels.get( this.currentLayout );
        if ( contentPanels == null )
        {
            contentPanels = this.currentLayout == PageLayout.ONE_PAGE
                ? new OnePagePanels<PageType>( readerWindow, factory ) : new TwoPagePanels<PageType>( readerWindow,
                    factory );
            panels.put( this.currentLayout, contentPanels );
            add( contentPanels, this.currentLayout.name() );
        }
        return contentPanels;
    }

    @Override
    public CardLayout getLayout()
    {
        return (CardLayout) super.getLayout();
    }

    public void switchLayout(
        final PageLayout pageLayout )
    {
        this.currentLayout = pageLayout;
        getCurrentPanels();
        getLayout().show( this, pageLayout.name() );
    }
}
