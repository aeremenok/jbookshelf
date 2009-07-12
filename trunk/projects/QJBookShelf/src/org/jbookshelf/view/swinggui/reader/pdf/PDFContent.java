/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.pdf;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

/**
 * @author eav 2009
 */
public class PDFContent
    extends BookContent<PDFPage>
{
    private static final Logger log = Logger.getLogger( PDFContent.class );
    private final PDFFile       pdffile;

    public PDFContent(
        final Book book )
    {
        super( book );
        try
        {
            final RandomAccessFile raf = new RandomAccessFile( file, "r" );
            final FileChannel channel = raf.getChannel();
            final ByteBuffer buf = channel.map( FileChannel.MapMode.READ_ONLY, 0, channel.size() );
            pdffile = new PDFFile( buf );
            pageCount = pdffile.getNumPages();
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int findText(
        final String text,
        final Boolean direction,
        final int currentPage )
    {
        // TODO use iText
        return 0;
    }

    @Override
    public PDFPage getPage(
        final int pageNumber )
    {
        return pdffile.getPage( pageNumber );
    }
}
