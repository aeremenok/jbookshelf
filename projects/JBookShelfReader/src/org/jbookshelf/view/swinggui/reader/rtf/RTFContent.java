package org.jbookshelf.view.swinggui.reader.rtf;

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

public class RTFContent
    extends BookContent<StyledDocument>
{
    private final static Logger  log      = Logger.getLogger( RTFContent.class );
    private final StyledDocument document = new DefaultStyledDocument();
    private final RTFEditorKit   kit      = new RTFEditorKit();

    public RTFContent(
        final Book book )
    {
        super( book );
        log.debug( "parsing file " + file );
        Reader reader = null;
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream( file );
            reader = new InputStreamReader( fis, book.getPhysicalBook().getCharsetName() );
            kit.read( reader, document, 0 );
            pageCount = 1;
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
        log.debug( "file parsed" );
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
    public StyledDocument getPage(
        final int pageNumber )
    {
        return document;
    }

}
