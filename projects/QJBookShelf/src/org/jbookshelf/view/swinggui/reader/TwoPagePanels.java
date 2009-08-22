/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JSplitPane;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class TwoPagePanels<PageType>
    extends ReaderContentPanels<PageType>
{
    @SuppressWarnings( "unused" )
    private static final Logger                log = Logger.getLogger( TwoPagePanels.class );
    private final ReaderContentPanel<PageType> leftContentPanel;
    private final ReaderContentPanel<PageType> rightContentPanel;

    public TwoPagePanels(
        final ReaderWindow<PageType> readerWindow,
        final ReaderFactory<PageType> factory )
    {
        super( readerWindow, factory );

        leftContentPanel = factory.createReaderContentPanel( readerWindow );
        rightContentPanel = factory.createReaderContentPanel( readerWindow );

        final JSplitPane splitPane = new JSplitPane();
        add( splitPane, BorderLayout.CENTER );

        splitPane.setLeftComponent( leftContentPanel );
        splitPane.setRightComponent( rightContentPanel );
        splitPane.setResizeWeight( 0.5 );
    }

    @Override
    public void highlightText(
        final String text )
    {
        leftContentPanel.highlightText( text );
        rightContentPanel.highlightText( text );
    }

    @Override
    public void setContent(
        final PageType... pages )
    {
        leftContentPanel.setContent( pages[0] );
        rightContentPanel.setContent( pages[1] );
    }

    @Override
    public void setReaderFont(
        final Font font )
    {
        leftContentPanel.setReaderFont( font );
        rightContentPanel.setReaderFont( font );
    }

    @Override
    public void setScale(
        final int scale )
    {
        leftContentPanel.setScale( scale );
        rightContentPanel.setScale( scale );
    }
}
