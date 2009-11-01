/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textview.pagelayout;

import java.awt.BorderLayout;
import java.awt.Font;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.api.Bookmark;
import org.jbookshelf.view.swinggui.reader.navigation.notes.NotesPanel;
import org.jbookshelf.view.swinggui.reader.textview.ContentRenderer;

/**
 * displays a single page
 * 
 * @author eav 2009
 * @param <PageType> displayed page type
 */
public class OnePageLayout<PageType>
    extends PageLayout<PageType>
{
    private final ContentRenderer<PageType> contentPanel;
    private final NotesPanel                   notesPanel = new NotesPanel();

    @SuppressWarnings( "unchecked" )
    public OnePageLayout()
    {
        super();
        contentPanel = Single.newInstance( ContentRenderer.class );
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

    @Override
    public void displayPages(
        final PageType... pages )
    {
        contentPanel.displayContent( pages[0] );
    }

    public ContentRenderer<PageType> getContentPanel()
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
    public void scale(
        final int scalePercentage )
    {
        contentPanel.scale( scalePercentage );
    }

    @Override
    public void useReaderFont(
        final Font font )
    {
        contentPanel.useFont( font );
    }

}
