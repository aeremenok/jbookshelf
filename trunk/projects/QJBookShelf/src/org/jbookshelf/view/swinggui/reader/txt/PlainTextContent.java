/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;

/**
 * stores plain text
 * 
 * @author eav 2009
 */
public class PlainTextContent
    extends BookContent<String>
{
    private final static int    CHARS_IN_PAGE = 2700;
    private static final Logger log           = Logger.getLogger( PlainTextContent.class );
    private final String        plainText;

    public PlainTextContent(
        final Book book )
    {
        super( book );
        try
        { // todo encoding
            // todo lazy loading
            plainText = FileUtils.readFileToString( file, book.getPhysicalBook().getCharsetName() );
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
        int startPage )
    {
        text = text.toLowerCase();

        if ( Boolean.FALSE.equals( direction ) )
        { // backward
            for ( int p = startPage - 1; p > -1; p-- )
            {
                final String page = getPage( p ).toLowerCase();
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
                final String page = getPage( p ).toLowerCase();
                if ( page.contains( text ) )
                {
                    return p;
                }
            }
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
