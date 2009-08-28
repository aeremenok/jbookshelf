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
    implements
    ReaderFactory<PDFPage>
{
    @Override
    public BookContent<PDFPage> createBookContent(
        final Book book )
    {
        return new PDFContent( book );
    }
}
