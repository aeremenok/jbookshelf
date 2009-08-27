/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

/**
 * @author eav 2009
 * @param <PageType>
 */
public abstract class ReaderContentPanels<PageType>
    extends JPanel
{
    protected final ReaderWindow<PageType> readerWindow;

    public ReaderContentPanels(
        final ReaderWindow<PageType> readerWindow )
    {
        super( new BorderLayout() );
        this.readerWindow = readerWindow;
    }

    public abstract void changeNotesVisibility();

    public abstract void highlightText(
        final String text );

    public abstract void setContent(
        final PageType... pages );

    public abstract void setReaderFont(
        final Font font );

    public abstract void setScale(
        final int scale );
}
