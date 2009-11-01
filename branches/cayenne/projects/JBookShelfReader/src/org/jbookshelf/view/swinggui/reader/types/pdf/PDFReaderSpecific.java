/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import org.jbookshelf.model.db.api.spec.IBook;
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
        final IBook book )
    {
        return new PDFBookContent( book );
    }
}
