/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;

/**
 * @author eav 2009
 */
public class HTMLReaderSpecific
    extends ReaderSpecific<HTMLContentWrapper>
{
    public HTMLReaderSpecific()
    {
        super();
        supportedFeatures.add( ReaderSpecific.CHARSET );
        supportedFeatures.add( ReaderSpecific.BOOKMARKS );
        supportedFeatures.add( ReaderSpecific.NOTES );
        supportedFeatures.add( ReaderSpecific.BROWSER );
    }

    @Override
    public BookContent<HTMLContentWrapper> createBookContent(
        final Book book )
    {
        return new HTMLBookContent( book );
    }
}
