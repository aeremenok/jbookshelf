/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 * @param <PageType>
 */
public interface ReaderFactory<PageType>
{
    BookContent<PageType> createBookContent(
        Book book );

    ReaderContentPanel<PageType> createReaderContentPanel(
        ReaderWindow<PageType> readerWindow );

    ReaderToolBar createReaderToolBar(
        ReaderWindow<PageType> readerWindow );

    ThumbnailPanel<PageType> createThumbnailPanel(
        ReaderWindow<PageType> readerWindow );
}
