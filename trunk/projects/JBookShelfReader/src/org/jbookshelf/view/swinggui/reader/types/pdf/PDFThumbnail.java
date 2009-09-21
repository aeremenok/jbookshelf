/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.Thumbnail;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jdesktop.swingx.border.DropShadowBorder;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class PDFThumbnail
    extends Thumbnail
{
    protected PagePanel    pagePanel        = new PagePanel();
    protected JLabel       label            = new JLabel();
    protected int          pageNumber       = 0;

    protected final Border deselectedBorder = new DropShadowBorder();
    protected final Border selectedBorder   = new DropShadowBorder( Color.GREEN, 5 );

    public PDFThumbnail()
    {
        super();
        add( pagePanel, BorderLayout.CENTER );
        add( label, BorderLayout.SOUTH );

        label.setHorizontalAlignment( SwingConstants.CENTER );

        setBorder( deselectedBorder );

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

        AnnotationProcessor.process( this );
    }

    @EventTopicSubscriber( topic = Bookmark.PAGE )
    public void onPageChanged(
        @SuppressWarnings( "unused" ) final String topic,
        @SuppressWarnings( "unused" ) final Bookmark bookmark )
    {
        final int currentPage = Single.instance( Paginator.class ).getCurrentPage();
        setBorder( currentPage == pageNumber
            ? selectedBorder : deselectedBorder );
    }

    @Override
    public void setPageNumber(
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