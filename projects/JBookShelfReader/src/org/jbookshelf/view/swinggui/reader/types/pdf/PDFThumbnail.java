/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.navigation.thumbnails.Thumbnail;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

/**
 * displays a single pdf page in a small panel<br>
 * double click navigates to page in a main viewer todo pull up to {@link Thumbnail}
 * 
 * @author eav 2009
 */
public class PDFThumbnail
    extends Thumbnail
{
    protected PagePanel pagePanel = new PagePanel();
    protected JLabel    label     = new JLabel();

    public PDFThumbnail()
    {
        super();
        add( pagePanel, BorderLayout.CENTER );
        add( label, BorderLayout.SOUTH );

        label.setHorizontalAlignment( SwingConstants.CENTER );

        pagePanel.setPreferredSize( new Dimension( 150, 200 ) );
        final MouseAdapter adapter = new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                if ( e.getClickCount() == 2 )
                {
                    Single.instance( Paginator.class ).setNewPage( pageNumber );
                }
            }
        };

        addMouseListener( adapter );
        pagePanel.addMouseListener( adapter );
        label.addMouseListener( adapter );
    }

    @Override
    public void displayPage(
        final int pageNumber )
    {
        this.pageNumber = pageNumber;
        final PDFBookContent bookContent = (PDFBookContent) Single.instance( ReaderWindow.class ).getBookContent();
        if ( pageNumber > bookContent.getPageCount() - 1 )
        {
            setVisible( false );
        }
        else
        {
            setVisible( true );
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<PDFPage, Object>()
            {
                @Override
                protected PDFPage doInBackground()
                {
                    return bookContent.getPageContent( pageNumber );
                }

                @Override
                protected void doneSafe()
                {
                    label.setText( pageNumber + 1 + "" );
                    pagePanel.showPage( getQuiet() );
                    onPageChanged( null, null );
                }
            } );
        }
    }
}