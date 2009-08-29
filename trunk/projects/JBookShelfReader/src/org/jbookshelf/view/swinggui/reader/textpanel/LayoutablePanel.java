/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.ContentNavigator;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter.PageLayout;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class LayoutablePanel<PageType>
    extends JPanel
    implements
    PropertyChangeListener
{
    @SuppressWarnings( "unused" )
    private static final Logger                                  log    = Logger.getLogger( LayoutablePanel.class );

    private final JPanel                                         cards  = new JPanel( new CardLayout() );

    private final Map<PageLayout, ReaderContentPanels<PageType>> panels = new HashMap<PageLayout, ReaderContentPanels<PageType>>();

    public LayoutablePanel()
    {
        super( new BorderLayout() );

        add( Single.instance( ContentNavigator.class ), BorderLayout.WEST );
        add( cards, BorderLayout.CENTER );
    }

    public ReaderContentPanels<PageType> getCurrentPanels()
    {
        final PageLayout currentLayout = Single.instance( Layouter.class ).getCurrentLayout();

        ReaderContentPanels<PageType> contentPanels = panels.get( currentLayout );
        if ( contentPanels == null )
        {
            // xxx single
            contentPanels = currentLayout == PageLayout.ONE_PAGE
                ? new OnePagePanels<PageType>() : new TwoPagePanels<PageType>();
            panels.put( currentLayout, contentPanels );
            cards.add( contentPanels, currentLayout.name() );
        }
        return contentPanels;
    }

    @Override
    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        final String propertyName = evt.getPropertyName();
        if ( ReaderFactory.BOOKMARKS.equals( propertyName ) || ReaderFactory.TOC.equals( propertyName )
            || ReaderFactory.THUMBNAILS.equals( propertyName ) )
        {
            final Object newValue = evt.getNewValue();

            final ContentNavigator contentNavigator = Single.instance( ContentNavigator.class );
            contentNavigator.setVisible( newValue != null );
            if ( newValue != null )
            {
                contentNavigator.show( propertyName );
            }
        }
        else if ( ReaderFactory.NOTES.equals( propertyName ) )
        {
            getCurrentPanels().changeNotesVisibility();
        }
    }

    public void switchLayout()
    {
        getCurrentPanels();

        final CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show( cards, Single.instance( Layouter.class ).getCurrentLayout().name() );
    }
}
