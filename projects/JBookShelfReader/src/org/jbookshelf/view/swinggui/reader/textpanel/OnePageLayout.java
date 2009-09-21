/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.BorderLayout;
import java.awt.Font;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.NotesPanel;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class OnePageLayout<PageType>
    extends PageLayout<PageType>
{
    private final ReaderContentPanel<PageType> contentPanel;
    private final NotesPanel                   notesPanel = new NotesPanel();

    @SuppressWarnings( "unchecked" )
    public OnePageLayout()
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
    public Bookmark createBookmark()
    {
        return contentPanel.createNote( null );
    }

    public ReaderContentPanel<PageType> getContentPanel()
    {
        return this.contentPanel;
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
    public void setScale(
        final int scale )
    {
        contentPanel.setScale( scale );
    }

    @Override
    public void useReaderFont(
        final Font font )
    {
        contentPanel.setReaderFont( font );
    }

}
