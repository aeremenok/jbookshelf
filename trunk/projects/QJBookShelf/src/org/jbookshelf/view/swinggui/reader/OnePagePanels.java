/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Font;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class OnePagePanels<PageType>
    extends ReaderContentPanels<PageType>
{
    @SuppressWarnings( "unused" )
    private static final Logger                log = Logger.getLogger( OnePagePanels.class );
    private final ReaderContentPanel<PageType> contentPanel;

    public OnePagePanels(
        final ReaderWindow<PageType> readerWindow,
        final ReaderFactory<PageType> factory )
    {
        super( readerWindow, factory );
        contentPanel = factory.createReaderContentPanel( readerWindow );
        add( contentPanel, BorderLayout.CENTER );
    }

    @Override
    public void highlightText(
        final String text )
    {
        contentPanel.highlightText( text );
    }

    @Override
    public void setContent(
        final PageType... pages )
    {
        contentPanel.setContent( pages[0] );
    }

    @Override
    public void setReaderFont(
        final Font font )
    {
        contentPanel.setReaderFont( font );
    }

    @Override
    public void setScale(
        final int scale )
    {
        contentPanel.setScale( scale );
    }

}
