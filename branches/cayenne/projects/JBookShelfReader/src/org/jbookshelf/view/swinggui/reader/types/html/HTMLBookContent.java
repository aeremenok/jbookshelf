/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.swinggui.reader.BookContent;

/**
 * @author eav 2009
 */
public class HTMLBookContent
    extends BookContent<HTMLContentWrapper>
{
    private static final Logger      log = Logger.getLogger( HTMLBookContent.class );

    private final HTMLContentWrapper onlyPageContainer;

    public HTMLBookContent(
        final IBook book )
    {
        super( book );
        try
        {
            final String encoding = book.getPhysicalBook().getCharsetName();
            final String fileContent = FileUtils.readFileToString( file, encoding );
            onlyPageContainer = new HTMLContentWrapper( fileContent );

            pageCount = 1;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int findTextPage(
        final String text,
        final Boolean direction,
        final int currentPage )
    { // just let the panel highlight the text
        return 0;
    }

    @Override
    public HTMLContentWrapper getPageContent(
        final int pageNumber )
    {
        return onlyPageContainer;
    }
}