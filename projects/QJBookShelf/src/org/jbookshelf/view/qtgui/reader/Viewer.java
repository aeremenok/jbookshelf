/**
 * 
 */
package org.jbookshelf.view.qtgui.reader;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.FileUtil;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.controller.util.ZIPUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.LogRunner;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;

/**
 * @author eav 2009
 */
public class Viewer
{
    private static final Logger log = Logger.getLogger( Viewer.class );

    /**
     * @param file a file to check
     * @return is this file supported by the internal viewer
     */
    public static boolean isInternallySupported(
        final File file )
    {
        try
        {
            final String contentType = file.toURI().toURL().openConnection().getContentType();
            return contentType.toLowerCase().startsWith( "text" );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    /**
     * get a file with book content
     * 
     * @param book a book to open
     * @return a file with book content
     */
    public File getFile(
        final Book book )
    {
        final PhysicalBook physical = book.getPhysicalBook();
        if ( physical.getUnpackedFileName() != null && physical.getUnpackedFile().exists() )
        {
            return physical.getUnpackedFile();
        }

        final File file = physical.getFile();
        if ( ZIPUtil.isZip( file ) )
        {
            // unpack and remember the file
            final File zippedFileToOpen = ZIPUtil.getZippedFileToOpen( file );
            physical.setUnpackedFile( zippedFileToOpen );
            // save to db
            try
            { // todo to BookShelf
                final LogRunner runner = new LogRunner();
                runner.update( "update physical_book set unpackedFileName=? where id=?", new Object[]
                { physical.getUnpackedFileName(), physical.getId() } );
            }
            catch ( final SQLException e )
            {
                log.error( e, e );
                throw new Error( e );
            }
            return zippedFileToOpen;
        }

        return file;
    }

    public void open(
        final Book book )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<byte[], Object>()
        {
            @Override
            protected byte[] doInBackground()
                throws Exception
            {
                return prepareBook( book );
            }

            @Override
            protected void doneSafe()
            {
                final String viewer = book.getPhysicalBook().getViewer();
                if ( PhysicalBook.INTERNAL_VIEWER.equals( viewer ) )
                { // internal
                    // start qt in another thread
                    Single.instance( QT.class ).invoke( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            new ReaderWindow( book, getQuiet() ).show();
                        }
                    } );
                }
                else
                { // system default
                    URIUtil.browseFile( book.getPhysicalBook().getFile() );
                }
            }
        } );
    }

    private byte[] prepareBook(
        final Book book )
        throws IOException
    {
        // unpack and define attributes
        final File file = getFile( book );

        // define which viewer to use
        String viewer = book.getPhysicalBook().getViewer();
        if ( viewer == null )
        {
            viewer = Viewer.isInternallySupported( file )
                ? PhysicalBook.INTERNAL_VIEWER : PhysicalBook.SYSTEM_VIEWER;
            book.getPhysicalBook().setViewer( viewer );
        }

        if ( PhysicalBook.INTERNAL_VIEWER.equals( viewer ) )
        {
            // read the whole book into memory
            final byte[] content = FileUtils.readFileToByteArray( file );
            // define which encoding to use
            String charset = book.getPhysicalBook().getCharsetName();
            if ( charset == null )
            {
                charset = FileUtil.guessByteArrayEncoding( content );
                book.getPhysicalBook().setCharsetName( charset );
            }
            return content;
        }

        return null;
    }
}
