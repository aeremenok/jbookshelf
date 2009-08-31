/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

import org.jbookshelf.model.db.Bookmark;

/**
 * @author eav 2009
 * @param <PageType>
 */
public abstract class ReaderContentPanels<PageType>
    extends JPanel
{
    public ReaderContentPanels()
    {
        super( new BorderLayout() );
    }

    public abstract void changeNotesVisibility();

    public abstract void goTo(
        final Bookmark bookmark );

    public abstract void highlightText(
        final String text );

    public abstract void setContent(
        final PageType... pages );

    public abstract void setReaderFont(
        final Font font );

    public abstract void setScale(
        final int scale );
}
