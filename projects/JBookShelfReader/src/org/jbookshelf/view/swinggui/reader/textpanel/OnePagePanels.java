/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.BorderLayout;
import java.awt.Font;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.NotesPanel;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class OnePagePanels<PageType>
    extends ReaderContentPanels<PageType>
{
    @SuppressWarnings( "unused" )
    private static final Logger                log        = Logger.getLogger( OnePagePanels.class );
    private final ReaderContentPanel<PageType> contentPanel;
    private final NotesPanel                   notesPanel = new NotesPanel();

    @SuppressWarnings( "unchecked" )
    public OnePagePanels()
    {
        super();
        contentPanel = Single.newInstance( ReaderContentPanel.class );
        add( contentPanel, BorderLayout.CENTER );
        add( notesPanel, BorderLayout.EAST );
    }

    @Override
    public void changeNotesVisibility()
    {
        notesPanel.setVisible( !notesPanel.isVisible() );
    }

    @Override
    public void goTo(
        final Bookmark bookmark )
    {
        contentPanel.goTo( bookmark );
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
