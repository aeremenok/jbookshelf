/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.pdf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.widget.WrapperPanel;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

/**
 * @author eav 2009
 */
public class PDFPanel
    extends ReaderContentPanel<PDFPage>
{
    private final PagePanel    pagePanel = new PagePanel();
    private final WrapperPanel wrap      = new WrapperPanel( pagePanel );
    private final JScrollPane  scroll    = new JScrollPane( wrap );
    private Dimension          preferredSize;

    public PDFPanel(
        final ReaderWindow<PDFPage> readerWindow )
    {
        super( readerWindow );
        add( scroll, BorderLayout.CENTER );
    }

    @Override
    public void highlightText(
        final String text )
    {
    // todo wait for PDFRenderer updates
    }

    @Override
    public void reset()
    {
        //        wrap.setPreferredSize( preferredSize );
        //        pagePanel.updateUI();
        preferredSize = null;
    }

    @Override
    public void setContent(
        final PDFPage content )
    {
        pagePanel.showPage( content );
        if ( preferredSize == null )
        {
            EventQueue.invokeLater( new Runnable()
            {
                public void run()
                {
                    preferredSize = wrap.getSize();
                }
            } );
        }
    }

    @Override
    public void setScale(
        final int scalePercentage )
    {
        if ( preferredSize != null )
        {
            final float fScale = scalePercentage;
            final float scaleFactor = fScale / 100;
            final int newWidth = (int) (preferredSize.width * scaleFactor);
            final int newHeight = (int) (preferredSize.height * scaleFactor);
            wrap.setPreferredSize( new Dimension( newWidth, newHeight ) );
            wrap.updateUI();
            EventQueue.invokeLater( new Runnable()
            {
                public void run()
                {
                    final JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();
                    final BoundedRangeModel model = horizontalScrollBar.getModel();
                    horizontalScrollBar.setValue( (model.getMaximum() - model.getExtent()) / 2 );
                }
            } );
        }
    }
}
