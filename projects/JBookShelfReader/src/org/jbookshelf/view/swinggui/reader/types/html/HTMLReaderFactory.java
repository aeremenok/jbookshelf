/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;

/**
 * @author eav 2009
 */
public class HTMLReaderFactory
    extends ReaderFactory<HTMLContentContainer>
{
    public HTMLReaderFactory()
    {
        super();
        features.add( Features.CHARSET );
    }

    @Override
    public BookContent<HTMLContentContainer> createBookContent(
        final Book book )
    {
        return new HTMLContent( book );
    }
}
