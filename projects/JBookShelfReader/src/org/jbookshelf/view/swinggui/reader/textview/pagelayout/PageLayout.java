/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textview.pagelayout;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

import org.jbookshelf.model.db.api.Bookmark;

/**
 * displays pages using specific layout
 * 
 * @author eav 2009
 * @param <PageType> displayed page type
 */
public abstract class PageLayout<PageType>
    extends JPanel
{
    public PageLayout()
    {
        super( new BorderLayout() );
    }

    public abstract void changeNotesVisibility();

    public abstract Bookmark createBookmark();

    public abstract void displayPages(
        final PageType... pages );

    public abstract void goTo(
        final Bookmark bookmark );

    public abstract void highlightText(
        final String text );

    public abstract void scale(
        final int scalePercentage );

    public abstract void useReaderFont(
        final Font font );
}
