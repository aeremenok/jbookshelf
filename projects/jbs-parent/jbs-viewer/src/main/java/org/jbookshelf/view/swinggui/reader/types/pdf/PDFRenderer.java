/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.BoundedRangeModel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;
import org.jbookshelf.view.swinggui.main.ProgressBar;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.textview.ContentRenderer;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jbookshelf.view.swinggui.widget.panel.WrapperPanel;
import org.xnap.commons.gui.util.PopupListener;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

/**
 * displays pdf pages
 * 
 * @author eav 2009
 */
public class PDFRenderer
    extends ContentRenderer<PDFPage>
{
    private class ExtractAndCopyAction
        extends TranslatableAction
    {
        public ExtractAndCopyAction()
        {
            super( tr( "Extracted page to clipboard" ), IMG.icon( IMG.CLIPBOARD_PNG, 32 ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            Single.instance( ProgressBar.class ).invoke( new Runnable()
            {
                public void run()
                {
                    final String text = currentPageToString();
                    final StringSelection clipString = new StringSelection( text );
                    getToolkit().getSystemClipboard().setContents( clipString, clipString );
                }
            } );
        }
    }

    private class ExtractAndCreateNoteAction
        extends TranslatableAction
    {
        public ExtractAndCreateNoteAction()
        {
            super( tr( "Extracted page to note" ), IMG.icon( IMG.KNOTES_PNG, 32 ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<Note, Object>()
            {
                @Override
                protected Note doInBackground()
                {
                    final String text = currentPageToString();
                    return createNote( text );
                }

                @Override
                protected void doneSafe()
                {
                    new NoteDialog( Single.instance( ReaderWindow.class ), getQuiet() ).setVisible( true );
                }
            } );
        }
    }

    private class ExtractTextAction
        extends TranslatableAction
    {
        public ExtractTextAction()
        {
            super( tr( "Extract page text" ), IMG.icon( IMG.EXTRACT_TEXT_PNG, 32 ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<String, Object>()
            {
                @Override
                protected String doInBackground()
                {
                    return currentPageToString();
                }

                @Override
                protected void doneSafe()
                {
                    final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );
                    new ExtractedTextDialog( readerWindow, getQuiet(), PDFRenderer.this ).setVisible( true );
                }
            } );
        }
    }

    private final PagePanel    pagePanel = new PagePanel();
    private final WrapperPanel wrap      = new WrapperPanel( pagePanel );
    private final JScrollPane  scroll    = new JScrollPane( wrap );

    private Dimension          preferredSize;

    public PDFRenderer()
    {
        super();
        add( scroll, BorderLayout.CENTER );

        final JPopupMenu menu = new JPopupMenu();
        menu.add( new ExtractTextAction() );
        menu.add( new ExtractAndCreateNoteAction() );
        menu.add( new ExtractAndCopyAction() );

        pagePanel.addMouseListener( new PopupListener( menu ) );
    }

    @Override
    public void displayContent(
        final PDFPage content )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                pagePanel.showPage( content );
                if ( preferredSize == null )
                {
                    preferredSize = wrap.getSize();
                }
            }
        } );
    }

    @Override
    public void goTo(
        final Bookmark bookmark )
    {
        Single.instance( Paginator.class ).setNewPage( bookmark.getPage() );
    }

    @Override
    public void highlightText(
        final String text )
    {
    // todo wait for PDFRenderer lib updates
    }

    @Override
    public void scale(
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

    private String currentPageToString()
    {
        final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );
        final PDFBookContent content = (PDFBookContent) readerWindow.getBookContent();
        final int pageNumber = pagePanel.getPage().getPageNumber();
        return content.pageToString( pageNumber );
    }

    @Override
    protected float calcRelativePosition(
        final Bookmark bookmark )
    {
        final float page = bookmark.getPage() + 1;
        return page / bookmark.getPageCount();
    }
}
