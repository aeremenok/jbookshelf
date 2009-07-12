/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.pdf;

import java.awt.BorderLayout;

import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

/**
 * @author eav 2009
 */
public class PDFReaderContentPanel
    extends ReaderContentPanel<PDFPage>
{
    private final PagePanel pagePanel = new PagePanel();

    public PDFReaderContentPanel(
        final ReaderWindow<PDFPage> readerWindow )
    {
        super( readerWindow );
        add( pagePanel, BorderLayout.CENTER );
    }

    @Override
    public void highlightText(
        final String text )
    {
    // todo wait for PDFRenderer updates
    }

    @Override
    public void setContent(
        final PDFPage content )
    {
        pagePanel.showPage( content );
    }

    @Override
    public void setScale(
        final int scale )
    {
    // todo override useZoom
    }
}
