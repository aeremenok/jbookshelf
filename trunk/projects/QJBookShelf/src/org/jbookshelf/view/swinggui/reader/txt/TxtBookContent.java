/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.BookContent;

/**
 * @author eav 2009
 */
public class TxtBookContent
    extends BookContent<String>
{
    private final static int    CHARS_IN_PAGE = 2700;
    private static final Logger log           = Logger.getLogger( TxtBookContent.class );
    private final String        plainText;

    public TxtBookContent(
        final File file )
    {
        super( file );
        try
        { // todo encoding
            // todo lazy loading
            plainText = FileUtils.readFileToString( file, "ibm866" );
            pageCount = plainText.length() / CHARS_IN_PAGE + 1;
        }
        catch ( final IOException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public String getPage(
        final int pageNumber )
    {
        final int start = pageNumber * CHARS_IN_PAGE;
        final int end = (pageNumber + 1) * CHARS_IN_PAGE;
        return plainText.substring( start, end );
    }
}
