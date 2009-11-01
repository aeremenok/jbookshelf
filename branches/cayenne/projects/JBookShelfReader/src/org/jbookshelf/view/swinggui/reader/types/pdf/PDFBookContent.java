/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

/**
 * @author eav 2009
 */
public class PDFBookContent
    extends BookContent<PDFPage>
{
    private static final Logger log = Logger.getLogger( PDFBookContent.class );

    /**
     * apache pdfbox document for text extraction, loaded on demand
     */
    private PDDocument          pdDocument;

    private final PDFFile       pdffile;

    public PDFBookContent(
        final IBook book )
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
    public synchronized int findTextPage(
        String text,
        final Boolean direction,
        int startPage )
    {
        try
        {
            text = text.toLowerCase();
            final PDDocument doc = getPdfBoxDocument();
            final PDFTextStripper stripper = new PDFTextStripper();
            if ( Boolean.FALSE.equals( direction ) )
            { // backward
                for ( int p = startPage - 1; p > -1; p-- )
                {
                    stripper.setStartPage( p );
                    stripper.setEndPage( p );
                    final String page = stripper.getText( doc ).toLowerCase();
                    if ( page.contains( text ) )
                    {
                        return p;
                    }
                }
            }
            else
            { // forward
                if ( direction == null )
                { // forward from start
                    startPage = -1;
                }

                for ( int p = startPage + 1; p < pageCount; p++ )
                {
                    stripper.setStartPage( p );
                    stripper.setEndPage( p );
                    final String page = stripper.getText( doc ).toLowerCase();
                    if ( page.contains( text ) )
                    {
                        return p;
                    }
                }
            }
            return -1;
        }
        catch ( final Throwable e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public PDFPage getPageContent(
        final int pageNumber )
    {
        return pdffile.getPage( pageNumber + 1 );
    }

    public synchronized String pageToString(
        final int pageNum )
    {
        try
        {
            final PDDocument doc = getPdfBoxDocument();
            final PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage( pageNum );
            stripper.setEndPage( pageNum );
            return stripper.getText( doc );
        }
        catch ( final IOException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    private PDDocument getPdfBoxDocument()
    {
        if ( pdDocument == null )
        {
            try
            {
                pdDocument = PDDocument.load( file );
            }
            catch ( final IOException e )
            {
                log.error( e, e );
                throw new Error( e );
            }
        }
        return pdDocument;
    }
}
