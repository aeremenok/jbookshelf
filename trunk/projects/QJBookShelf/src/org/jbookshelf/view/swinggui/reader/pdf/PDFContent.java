/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.pdf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

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
    private PDDocument          pdDocument;

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
    public synchronized int findText(
        String text,
        final Boolean direction,
        int startPage )
    {
        try
        {
            text = text.toLowerCase();
            final PDDocument doc = getPdDocument();
            synchronized ( pdDocument )
            {
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
        }
        catch ( final Throwable e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public PDFPage getPage(
        final int pageNumber )
    {
        log.debug( "getting page " + pageNumber );
        final PDFPage page = pdffile.getPage( pageNumber + 1 );
        log.debug( "page got" );
        return page;
    }

    public synchronized String getPageContent(
        final int pageNum )
    {
        try
        {
            final PDDocument doc = getPdDocument();
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

    @Override
    public void onClose()
    {
        if ( pdDocument != null )
        {
            // leave a thread to wait until the search is done
            new Thread( new Runnable()
            {
                @Override
                public void run()
                {
                    synchronized ( pdDocument )
                    {
                        try
                        {
                            pdDocument.close();
                        }
                        catch ( final IOException e )
                        {
                            log.error( e, e );
                            throw new Error( e );
                        }
                    }
                }
            } ).start();
        }
    }

    private PDDocument getPdDocument()
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
