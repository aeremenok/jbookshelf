/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.html;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 */
public class HTMLReaderFactory
    implements
    ReaderFactory<HTMLContentContainer>
{
    @Override
    public BookContent<HTMLContentContainer> createBookContent(
        final Book book )
    {
        return new HTMLContent( book );
    }

    @Override
    public ReaderContentPanel<HTMLContentContainer> createReaderContentPanel(
        final ReaderWindow<HTMLContentContainer> readerWindow )
    {
        return new HTMLReaderPanel( readerWindow );
    }

    @Override
    public ReaderToolBar createReaderToolBar(
        final ReaderWindow<HTMLContentContainer> readerWindow )
    {
        return new ReaderToolBar( readerWindow, Features.SCALING, Features.SEARCH, Features.CHARSET );
    }
}
