/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author eav 2009
 * @param <T>
 */
public abstract class ReaderContentPanel<T>
    extends JPanel
{
    // todo unused?
    protected final ReaderWindow<T> readerWindow;

    public ReaderContentPanel(
        final ReaderWindow<T> readerWindow )
    {
        super( new BorderLayout() );
        this.readerWindow = readerWindow;
    }

    public abstract void setContent(
        T content );

    /**
     * @param scale
     */
    public abstract void setScale(
        final int scale );
}
