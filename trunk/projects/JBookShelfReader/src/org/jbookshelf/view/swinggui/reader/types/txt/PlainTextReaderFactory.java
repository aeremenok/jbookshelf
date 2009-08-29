/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

/**
 * @author eav 2009
 */
public class PlainTextReaderFactory
    extends ReaderFactory<String>
{
    public PlainTextReaderFactory()
    {
        super();
        features.add( ReaderFactory.PAGING );
        features.add( ReaderFactory.SCALING );
        features.add( ReaderFactory.LAYOUT );
        features.add( ReaderFactory.SEARCH );
        features.add( ReaderFactory.FONT );
        features.add( ReaderFactory.CHARSET );
        features.add( ReaderFactory.BOOKMARKS );
        features.add( ReaderFactory.NOTES );
    }

    @Override
    public BookContent<String> createBookContent(
        final Book book )
    {
        return new PlainTextContent( book );
    }
}
