/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;

/**
 * @author eav 2009
 */
public class TXTReaderSpecific
    extends ReaderSpecific<String>
{
    public TXTReaderSpecific()
    {
        super();
        supportedFeatures.add( ReaderSpecific.PAGING );
        supportedFeatures.add( ReaderSpecific.SCALING );
        supportedFeatures.add( ReaderSpecific.LAYOUT );
        supportedFeatures.add( ReaderSpecific.SEARCH );
        supportedFeatures.add( ReaderSpecific.FONT );
        supportedFeatures.add( ReaderSpecific.CHARSET );

        supportedFeatures.add( ReaderSpecific.BOOKMARKS );
        supportedFeatures.add( ReaderSpecific.NOTES );
    }

    @Override
    public BookContent<String> createBookContent(
        final IBook book )
    {
        return new TXTBookContent( book );
    }
}
