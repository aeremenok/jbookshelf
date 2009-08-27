/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.textpanel.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.textpanel.aux.ThumbnailPanel;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 */
public class PlainTextReaderFactory
    implements
    ReaderFactory<String>
{
    @Override
    public BookContent<String> createBookContent(
        final Book book )
    {
        return new PlainTextContent( book );
    }

    @Override
    public ReaderContentPanel<String> createReaderContentPanel(
        final ReaderWindow<String> readerWindow )
    {
        return new PlaintTextPanel( readerWindow );
    }

    @Override
    public ReaderToolBar createReaderToolBar(
        final ReaderWindow<String> readerWindow )
    {
        return new ReaderToolBar( readerWindow, Features.PAGING, Features.SCALING, Features.LAYOUT, Features.SEARCH,
            Features.FONT, Features.CHARSET, Features.BOOKMARKS, Features.NOTES );
    }

    @Override
    public ThumbnailPanel<String> createThumbnailPanel(
        final ReaderWindow<String> readerWindow )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
