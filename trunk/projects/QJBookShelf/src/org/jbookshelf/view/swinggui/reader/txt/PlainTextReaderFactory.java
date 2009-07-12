/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
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
        final ReaderWindow readerWindow )
    {
        // todo add font setting
        return new ReaderToolBar( readerWindow );
    }
}
