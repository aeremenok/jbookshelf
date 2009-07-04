/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.io.File;

import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 */
public class TxtReaderFactory
    implements
    ReaderFactory<String>
{
    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.reader.ReaderFactory#createBookContent(java.io.File)
     */
    @Override
    public BookContent<String> createBookContent(
        final File file )
    {
        return new TxtBookContent( file );
    }

    /* (non-Javadoc) 
     * @see org.jbookshelf.view.swinggui.reader.ReaderFactory#createReaderContentPanel(org.jbookshelf.view.swinggui.reader.ReaderWindow)
     */
    @Override
    public ReaderContentPanel<String> createReaderContentPanel(
        final ReaderWindow<String> readerWindow )
    {
        return new TxtReaderContentPanel( readerWindow );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.reader.ReaderFactory#createReaderToolBar(org.jbookshelf.view.swinggui.reader.ReaderWindow)
     */
    @Override
    public ReaderToolBar createReaderToolBar(
        final ReaderWindow readerWindow )
    {
        // todo add font setting
        return new ReaderToolBar( readerWindow );
    }
}
