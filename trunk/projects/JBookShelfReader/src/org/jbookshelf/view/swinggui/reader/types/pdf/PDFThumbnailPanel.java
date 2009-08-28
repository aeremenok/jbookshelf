/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.ThumbnailPanel;

import com.sun.pdfview.PDFPage;

/**
 * @author eav 2009
 */
public class PDFThumbnailPanel
    extends ThumbnailPanel<PDFPage>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( PDFThumbnailPanel.class );

    public PDFThumbnailPanel(
        final ReaderWindow<PDFPage> readerWindow )
    {
        super( readerWindow );
    }

}
