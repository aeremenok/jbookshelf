package org.jbookshelf.view.swinggui.reader.types.rtf;

import javax.swing.text.StyledDocument;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;

/**
 * @author eav 2009
 */
public class RTFReaderSpecific
    extends ReaderSpecific<StyledDocument>
{
    public RTFReaderSpecific()
    {
        super();
        supportedFeatures.add( ReaderSpecific.SCALING );
        supportedFeatures.add( ReaderSpecific.SEARCH );
        supportedFeatures.add( ReaderSpecific.CHARSET );

        supportedFeatures.add( ReaderSpecific.BOOKMARKS );
        supportedFeatures.add( ReaderSpecific.NOTES );
    }

    @Override
    public BookContent<StyledDocument> createBookContent(
        final Book book )
    {
        return new RTFBookContent( book );
    }
}
