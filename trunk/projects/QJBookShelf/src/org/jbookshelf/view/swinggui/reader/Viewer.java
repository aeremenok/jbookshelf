/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.io.File;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.controller.util.ZIPUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.LogRunner;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.txt.TxtReaderFactory;

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
    public File prepareFile(
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
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<ReaderWindow, Object>()
        {
            @Override
            protected ReaderWindow doInBackground()
            {
                // unpack and define attributes
                final File file = prepareFile( book );

                // define which viewer to use
                String viewer = book.getPhysicalBook().getViewer();
                if ( viewer == null )
                {
                    viewer = Viewer.isInternallySupported( file )
                        ? PhysicalBook.INTERNAL_VIEWER : PhysicalBook.SYSTEM_VIEWER;
                    book.getPhysicalBook().setViewer( viewer );
                }

                if ( PhysicalBook.INTERNAL_VIEWER.equals( viewer ) )
                { // todo define factory 
                    return new ReaderWindow<String>( book, new TxtReaderFactory() );
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
}
