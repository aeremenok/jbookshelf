/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;

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
        features.add( Features.PAGING );
        features.add( Features.LAYOUT );
        features.add( Features.SEARCH );
        features.add( Features.SCALING );
    }

    @Override
    public BookContent<PDFPage> createBookContent(
        final Book book )
    {
        return new PDFContent( book );
    }
}
