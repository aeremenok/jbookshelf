/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

import com.sun.pdfview.PDFPage;

/**
 * @author eav 2009
 */
public class PDFReaderFactory
    extends ReaderFactory<PDFPage>
{
    public PDFReaderFactory()
    {
        super();
        features.add( ReaderFactory.PAGING );
        features.add( ReaderFactory.LAYOUT );
        features.add( ReaderFactory.SEARCH );
        features.add( ReaderFactory.SCALING );
        features.add( ReaderFactory.THUMBNAILS );
    }

    @Override
    public BookContent<PDFPage> createBookContent(
        final Book book )
    {
        return new PDFContent( book );
    }
}
