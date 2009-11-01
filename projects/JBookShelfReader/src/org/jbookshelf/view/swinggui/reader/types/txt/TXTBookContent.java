/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.swinggui.reader.BookContent;

/**
 * @author eav 2009
 */
public class TXTBookContent
    extends BookContent<String>
{
    private static final Logger log           = Logger.getLogger( TXTBookContent.class );
    /**
     * fixme divide by rows, not by character count
     */
    private final static int    CHARS_IN_PAGE = 2700;
    private final String        plainText;

    public TXTBookContent(
        final IBook book )
    {
        super( book );
        try
        {
            final String encoding = book.getPhysicalBook().getCharsetName();
            plainText = FileUtils.readFileToString( file, encoding );
            pageCount = plainText.length() / CHARS_IN_PAGE + 1;
        }
        catch ( final IOException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int findTextPage(
        String text,
        final Boolean direction,
        int startPage )
    {
        text = text.toLowerCase();

        if ( Boolean.FALSE.equals( direction ) )
        { // backward
            for ( int p = startPage - 1; p > -1; p-- )
            {
                final String page = getPageContent( p ).toLowerCase();
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
                final String page = getPageContent( p ).toLowerCase();
                if ( page.contains( text ) )
                {
                    return p;
                }
            }
        }

        return -1;
    }

    @Override
    public String getPageContent(
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
