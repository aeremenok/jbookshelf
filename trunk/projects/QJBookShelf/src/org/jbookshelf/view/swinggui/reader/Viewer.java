/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.FileUtil;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.controller.util.ZIPUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.pdf.PDFReaderFactory;
import org.jbookshelf.view.swinggui.reader.txt.PlainTextReaderFactory;

/**
 * @author eav 2009
 */
public class Viewer
{
    private static final Logger              log       = Logger.getLogger( Viewer.class );

    private final Map<String, ReaderFactory> factories = new HashMap<String, ReaderFactory>();
    {
        factories.put( "text/plain", new PlainTextReaderFactory() );
        factories.put( "application/pdf", new PDFReaderFactory() );
    }

    public void open(
        final Book book )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<ReaderWindow, Object>()
        {
            @SuppressWarnings( "unchecked" )
            @Override
            protected ReaderWindow doInBackground()
            {
                final PhysicalBook physical = book.getPhysicalBook();
                initPhysicalBook( physical );
                // define which viewer to use
                if ( PhysicalBook.INTERNAL_VIEWER.equals( physical.getViewer() ) )
                {
                    final ReaderFactory factory = getReaderFactory( getFile( physical ) );
                    return new ReaderWindow( book, factory );
                }

                return null;
            }

            @Override
            protected void doneSafe()
            {
                if ( getQuiet() == null )
                { // system default
                    URIUtil.browseFile( book.getPhysicalBook().getFile() );
                }
                else
                { // internal
                    getQuiet().setVisible( true );
                }
            }
        } );
    }

    private File getFile(
        final PhysicalBook physical )
    {
        if ( physical.getUnpackedFileName() != null )
        {
            if ( !physical.getUnpackedFile().exists() )
            {
                throw new IllegalStateException();
            }

            return physical.getUnpackedFile();
        }
        return physical.getFile();
    }

    private ReaderFactory getReaderFactory(
        final File file )
    {
        try
        {
            final String contentType = file.toURI().toURL().openConnection().getContentType();
            return factories.get( contentType.toLowerCase() );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    private void initPhysicalBook(
        final PhysicalBook physical )
    {
        File file = physical.getFile();
        if ( ZIPUtil.isZip( file ) && (physical.getUnpackedFile() == null || !physical.getUnpackedFile().exists()) )
        { // unpack and remember the file
            final File unpackedFileToOpen = ZIPUtil.getZippedFileToOpen( file );
            physical.setUnpackedFile( unpackedFileToOpen );
            file = unpackedFileToOpen;
        }

        if ( physical.getViewer() == null )
        { // viewer undefined
            final ReaderFactory factory = getReaderFactory( file );
            physical.setViewer( factory != null
                ? PhysicalBook.INTERNAL_VIEWER : PhysicalBook.SYSTEM_VIEWER );
        }

        if ( PhysicalBook.INTERNAL_VIEWER.equals( physical.getViewer() ) && physical.getCharsetName() == null )
        { // encoding for the internal viewer undefined
            final String encoding = FileUtil.guessFileEncoding( file );
            physical.setCharsetName( encoding );
        }

        BookShelf.updatePhysical( physical );
    }
}
