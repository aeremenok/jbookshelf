/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.pdf;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

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

    @Override
    public ReaderContentPanel<PDFPage> createReaderContentPanel(
        final ReaderWindow<PDFPage> readerWindow )
    {
        return new PDFPanel( readerWindow );
    }

    @Override
    public ReaderToolBar createReaderToolBar(
        final ReaderWindow<PDFPage> readerWindow )
    {
        return new ReaderToolBar( readerWindow );
    }
}
