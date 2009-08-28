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
    implements
    ReaderFactory<String>
{
    @Override
    public BookContent<String> createBookContent(
        final Book book )
    {
        return new PlainTextContent( book );
    }
}
