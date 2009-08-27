/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
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
    private static final Logger                log             = Logger.getLogger( TwoPagePanels.class );
    private final ReaderContentPanel<PageType> leftContentPanel;
    private final ReaderContentPanel<PageType> rightContentPanel;

    private final NotesPanel                   leftNotesPanel  = new NotesPanel();
    private final NotesPanel                   rightNotesPanel = new NotesPanel();

    public TwoPagePanels(
        final ReaderWindow<PageType> readerWindow )
    {
        super( readerWindow );

        final ReaderFactory<PageType> factory = readerWindow.getFactory();
        leftContentPanel = factory.createReaderContentPanel( readerWindow );
        rightContentPanel = factory.createReaderContentPanel( readerWindow );

        final JSplitPane splitPane = new JSplitPane();
        add( splitPane, BorderLayout.CENTER );

        final JPanel left = new JPanel( new BorderLayout() );
        left.add( leftContentPanel, BorderLayout.CENTER );
        left.add( leftNotesPanel, BorderLayout.EAST );

        final JPanel right = new JPanel( new BorderLayout() );
        right.add( rightContentPanel, BorderLayout.CENTER );
        right.add( rightNotesPanel, BorderLayout.EAST );

        splitPane.setLeftComponent( left );
        splitPane.setRightComponent( right );
        splitPane.setResizeWeight( 0.5 );
    }

    @Override
    public void changeNotesVisibility()
    {
        leftNotesPanel.setVisible( !leftNotesPanel.isVisible() );
        rightNotesPanel.setVisible( !rightNotesPanel.isVisible() );
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
