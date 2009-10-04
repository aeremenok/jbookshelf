package org.jbookshelf.view.swinggui.reader.types.rtf;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;

/**
 * @author eav 2009
 */
public class RTFBookContent
    extends BookContent<StyledDocument>
{
    private final static Logger  log      = Logger.getLogger( RTFBookContent.class );

    private final StyledDocument document = new DefaultStyledDocument();

    public RTFBookContent(
        final Book book )
    {
        super( book );

        pageCount = 1;
        final String encoding = book.getPhysicalBook().getCharsetName();

        Reader reader = null;
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream( file );
            reader = new InputStreamReader( fis, encoding );
            new RTFEditorKit().read( reader, document, 0 );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
        finally
        {
            IOUtils.closeQuietly( fis );
            IOUtils.closeQuietly( reader );
        }
    }

    @Override
    public int findTextPage(
        final String text,
        final Boolean direction,
        final int currentPage )
    {
        return 0;
    }

    @Override
    public StyledDocument getPageContent(
        final int pageNumber )
    {
        return document;
    }

}
