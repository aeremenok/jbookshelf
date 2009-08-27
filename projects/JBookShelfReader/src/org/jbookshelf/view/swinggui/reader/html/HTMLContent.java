/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.html;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;

/**
 * @author eav 2009
 */
public class HTMLContent
    extends BookContent<HTMLContentContainer>
{
    private static final Logger        log = Logger.getLogger( HTMLContent.class );

    private final HTMLContentContainer container;

    public HTMLContent(
        final Book book )
    {
        super( book );
        try
        {
            final String encoding = book.getPhysicalBook().getCharsetName();
            final String string = FileUtils.readFileToString( file, encoding );
            container = new HTMLContentContainer( string );

            pageCount = 1;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int findText(
        final String text,
        final Boolean direction,
        final int currentPage )
    {
        return 0;
    }

    @Override
    public HTMLContentContainer getPage(
        final int pageNumber )
    {
        return container;
    }
}