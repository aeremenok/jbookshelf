/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.ContentNavigator;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter.PageLayout;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class LayoutSwitcher<PageType>
    extends JPanel
    implements
    PropertyChangeListener
{
    @SuppressWarnings( "unused" )
    private static final Logger                                  log           = Logger
                                                                                   .getLogger( LayoutSwitcher.class );

    private PageLayout                                           currentLayout = PageLayout.ONE_PAGE;
    private final ContentNavigator<PageType>                     contentNavigator;

    private final JPanel                                         cards         = new JPanel( new CardLayout() );

    private final Map<PageLayout, ReaderContentPanels<PageType>> panels        = new HashMap<PageLayout, ReaderContentPanels<PageType>>();

    public LayoutSwitcher()
    {
        super( new BorderLayout() );

        final List<String> features = Single.instance( ReaderToolBar.class ).getFeatures();
        this.contentNavigator = new ContentNavigator<PageType>( features );

        add( contentNavigator, BorderLayout.WEST );
        add( cards, BorderLayout.CENTER );
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
            // xxx single
            contentPanels = this.currentLayout == PageLayout.ONE_PAGE
                ? new OnePagePanels<PageType>() : new TwoPagePanels<PageType>();
            panels.put( this.currentLayout, contentPanels );
            cards.add( contentPanels, this.currentLayout.name() );
        }
        return contentPanels;
    }

    @Override
    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        final String propertyName = evt.getPropertyName();
        if ( Features.BOOKMARKS.equals( propertyName ) || Features.TOC.equals( propertyName )
            || Features.THUMBNAILS.equals( propertyName ) )
        {
            final Object newValue = evt.getNewValue();
            contentNavigator.setVisible( newValue != null );
            if ( newValue != null )
            {
                contentNavigator.show( propertyName );
            }
        }
        else if ( Features.NOTES.equals( propertyName ) )
        {
            getCurrentPanels().changeNotesVisibility();
        }
    }

    public void switchLayout(
        final PageLayout pageLayout )
    {
        this.currentLayout = pageLayout;
        getCurrentPanels();
        getCardLayout().show( cards, pageLayout.name() );
    }

    private CardLayout getCardLayout()
    {
        return (CardLayout) cards.getLayout();
    }
}
