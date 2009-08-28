/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

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
}
