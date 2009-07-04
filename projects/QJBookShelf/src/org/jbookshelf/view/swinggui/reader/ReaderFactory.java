/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.io.File;

import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 * @param <T>
 */
public interface ReaderFactory<T>
{
    BookContent<T> createBookContent(
        File file );

    ReaderContentPanel<T> createReaderContentPanel(
        ReaderWindow<T> readerWindow );

    ReaderToolBar createReaderToolBar(
        ReaderWindow<T> readerWindow );
}
