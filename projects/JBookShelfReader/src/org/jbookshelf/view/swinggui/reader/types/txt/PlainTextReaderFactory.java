/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;

/**
 * @author eav 2009
 */
public class PlainTextReaderFactory
    extends ReaderFactory<String>
{
    public PlainTextReaderFactory()
    {
        super();
        features.add( Features.PAGING );
        features.add( Features.SCALING );
        features.add( Features.LAYOUT );
        features.add( Features.SEARCH );
        features.add( Features.FONT );
        features.add( Features.CHARSET );
        features.add( Features.BOOKMARKS );
        features.add( Features.NOTES );
    }

    @Override
    public BookContent<String> createBookContent(
        final Book book )
    {
        return new PlainTextContent( book );
    }
}
