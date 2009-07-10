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
    private final String        plainTextLowerCase;

    public TxtBookContent(
        final File file )
    {
        super( file );
        try
        { // todo encoding
            // todo lazy loading
            plainText = FileUtils.readFileToString( file, "ibm866" );
            plainTextLowerCase = plainText.toLowerCase();
            pageCount = plainText.length() / CHARS_IN_PAGE + 1;
        }
        catch ( final IOException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int findText(
        String text,
        final Boolean direction,
        final int startPage )
    {
        text = text.toLowerCase();
        int indexOf;
        final int fromIndex = startPage * CHARS_IN_PAGE;
        if ( Boolean.TRUE.equals( direction ) )
        { // search forward
            indexOf = plainTextLowerCase.indexOf( text, fromIndex );
        }
        if ( Boolean.FALSE.equals( direction ) )
        { // search backward
            final String substring = plainTextLowerCase.substring( 0, fromIndex - 1 );
            indexOf = substring.lastIndexOf( text );
        }
        else
        { // search forward from start
            indexOf = plainTextLowerCase.indexOf( text );
        }

        log.debug( startPage + " " + text + " " + indexOf );

        if ( indexOf > -1 )
        {
            return indexOf / CHARS_IN_PAGE;
        }

        return -1;
    }

    @Override
    public String getPage(
        int pageNumber )
    { // todo cache?
        if ( pageNumber > pageCount - 1 )
        {
            return "";
        }
        if ( pageNumber < 0 )
        {
            pageNumber = 0;
        }
        final int start = pageNumber * CHARS_IN_PAGE;
        final int end = Math.min( (pageNumber + 1) * CHARS_IN_PAGE, plainText.length() - 1 );
        return plainText.substring( start, end );
    }
}
