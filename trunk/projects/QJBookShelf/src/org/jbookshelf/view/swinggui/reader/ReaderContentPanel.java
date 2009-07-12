/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author eav 2009
 * @param <PageType>
 */
public abstract class ReaderContentPanel<PageType>
    extends JPanel
{
    // todo unused?
    protected final ReaderWindow<PageType> readerWindow;

    public ReaderContentPanel(
        final ReaderWindow<PageType> readerWindow )
    {
        super( new BorderLayout() );
        this.readerWindow = readerWindow;
    }

    public abstract void highlightText(
        String text );

    public abstract void setContent(
        PageType content );

    /**
     * @param scale
     */
    public abstract void setScale(
        final int scale );
}
