/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;


import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 * @param <T>
 */
public interface ReaderFactory<T>
{
    BookContent<T> createBookContent(
        Book book );

    ReaderContentPanel<T> createReaderContentPanel(
        ReaderWindow<T> readerWindow );

    ReaderToolBar createReaderToolBar(
        ReaderWindow<T> readerWindow );
}
