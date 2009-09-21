/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;

import com.sun.pdfview.PDFPage;

/**
 * @author eav 2009
 */
public class PDFReaderSpecific
    extends ReaderSpecific<PDFPage>
{
    public PDFReaderSpecific()
    {
        super();
        supportedFeatures.add( ReaderSpecific.PAGING );
        supportedFeatures.add( ReaderSpecific.LAYOUT );
        supportedFeatures.add( ReaderSpecific.SEARCH );
        supportedFeatures.add( ReaderSpecific.SCALING );

        supportedFeatures.add( ReaderSpecific.THUMBNAILS );
        supportedFeatures.add( ReaderSpecific.NOTES );
        supportedFeatures.add( ReaderSpecific.BOOKMARKS );
    }

    @Override
    public BookContent<PDFPage> createBookContent(
        final Book book )
    {
        return new PDFBookContent( book );
    }
}
