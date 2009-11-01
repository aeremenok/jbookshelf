/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.FileUtil;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.controller.util.ZIPUtil;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;

/**
 * @author eav 2009
 */
public class Viewer
{
    private static final Logger       log   = Logger.getLogger( Viewer.class );

    public static final String        TXT   = "txt";
    public static final String        PDF   = "pdf";
    public static final String        RTF   = "rtf";
    public static final String        HTML  = "html";

    private final Map<String, String> types = new HashMap<String, String>();
    {
        types.put( "text/plain", TXT );
        types.put( "text/html", HTML );
        types.put( "application/rtf", RTF );
        types.put( "application/pdf", PDF );

        // try extensions if the mime is broken
        types.put( "txt", TXT );
        types.put( "html", HTML );
        types.put( "htm", HTML );
        types.put( "shtml", HTML );
        types.put( "rtf", RTF );
        types.put( "pdf", PDF );
    }

    public void open(
        final IBook book )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<String, Object>()
        {
            @Override
            protected String doInBackground()
            {
                final IPhysicalBook physical = book.getPhysicalBook();
                initPhysicalBook( physical );
                // define which viewer to use
                if ( IPhysicalBook.INTERNAL_VIEWER.equals( physical.getViewer() ) )
                {
                    return getReaderType( getFile( physical ) );
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
                    try
                    {
                        final String command = "java -jar jbsreader.jar " + book.getId() + " " + getQuiet();
                        Runtime.getRuntime().exec( command );
                    }
                    catch ( final Exception e )
                    {
                        log.error( e, e );
                        throw new Error( e );
                    }
                }
            }
        } );
    }

    private File getFile(
        final IPhysicalBook physical )
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

    private String getReaderType(
        final File file )
    {
        try
        {
            log.debug( "getting mimetype of file " + file.getAbsolutePath() );
            final String contentType = file.toURI().toURL().openConnection().getContentType();
            log.debug( "mimetype=" + contentType );
            String type = types.get( contentType.toLowerCase() );
            if ( type == null )
            {
                final String extension = FilenameUtils.getExtension( file.getName() );
                type = types.get( extension );
            }
            return type;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    private void initPhysicalBook(
        final IPhysicalBook physical )
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
            final String type = getReaderType( file );
            physical.setViewer( type != null
                ? IPhysicalBook.INTERNAL_VIEWER : IPhysicalBook.SYSTEM_VIEWER );
        }

        if ( IPhysicalBook.INTERNAL_VIEWER.equals( physical.getViewer() ) && physical.getCharsetName() == null )
        { // encoding for the internal viewer undefined
            final String encoding = FileUtil.guessFileEncoding( file );
            physical.setCharsetName( encoding );
        }

        BookShelf.updatePhysical( physical );
    }
}
