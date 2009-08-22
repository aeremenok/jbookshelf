/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.charset.Charset;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter.PageLayout;
import org.jdesktop.swingx.JXFrame;

/**
 * book reader main window
 * 
 * @author eav 2009
 * @param <T>
 */
public class ReaderWindow<T>
    extends JXFrame
    implements
    PropertyChangeListener
{
    private final Book              book;
    private final ReaderToolBar     toolBar;
    private final LayoutSwitcher<T> layoutSwitcher;
    private final ReaderFactory<T>  factory;
    private BookContent<T>          bookContent;

    private static final Logger     log = Logger.getLogger( ReaderWindow.class );

    public ReaderWindow(
        final Book book,
        final ReaderFactory<T> factory )
    {
        super();
        this.book = book;
        this.factory = factory;

        toolBar = factory.createReaderToolBar( this );
        layoutSwitcher = new LayoutSwitcher<T>( this, factory );
        bookContent = factory.createBookContent( book );

        setContentPane( new JPanel( new BorderLayout() ) );

        add( toolBar, BorderLayout.NORTH );
        add( layoutSwitcher, BorderLayout.CENTER );

        setTitle( book.getName() );
        setIconImage( IMG.img( IMG.LOGO_PNG, 64 ) );

        pack();
        setExtendedState( Frame.MAXIMIZED_BOTH );

        addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(
                final WindowEvent e )
            { // clear content data, close streams, etc...  
                bookContent.onClose();
            }
        } );
    }

    /**
     * change the layout between one and two pages
     * 
     * @param layout new {@link PageLayout}
     */
    public void changeLayout(
        final PageLayout layout )
    {
        layoutSwitcher.switchLayout( layout );
        setPage( toolBar.getPaginator().getCurrentPage() );
        toolBar.getPaginator().setPageLayout( layout );
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                layoutSwitcher.getCurrentPanels().setScale( toolBar.getScalator().getScale() );
            }
        } );
    }

    public Book getBook()
    {
        return this.book;
    }

    public BookContent<T> getBookContent()
    {
        return this.bookContent;
    }

    public ReaderToolBar getReaderToolBar()
    {
        return this.toolBar;
    }

    @Override
    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        final String propertyName = evt.getPropertyName();
        final Object newValue = evt.getNewValue();
        log.debug( "property changed " + propertyName + "=" + newValue );

        if ( Features.PAGING.equals( propertyName ) )
        {
            setPage( (Integer) newValue );
        }
        else if ( Features.SCALING.equals( propertyName ) )
        {
            layoutSwitcher.getCurrentPanels().setScale( ((Integer) newValue) );
        }
        else if ( Features.LAYOUT.equals( propertyName ) )
        {
            changeLayout( (PageLayout) newValue );
        }
        else if ( Features.SEARCH.equals( propertyName ) )
        {
            searchText( (Parameters) newValue );
        }
        else if ( Features.FONT.equals( propertyName ) )
        {
            layoutSwitcher.getCurrentPanels().setReaderFont( ((Font) newValue) );
        }
        else if ( Features.CHARSET.equals( propertyName ) )
        {
            setCharset( (Charset) newValue );
        }
        //        else if ( Features.BOOKMARKS.equals( propertyName ) )
        //        {
        //
        //        }
    }

    /**
     * search text in the displayed book and go to its position if its found
     * 
     * @param parameters text and direction to search ( {@link Boolean#TRUE} - forward, {@link Boolean#FALSE} -
     *            backward, null - forward from start )
     */
    public void searchText(
        final Parameters parameters )
    {
        final String text = parameters.get( Keys.SEARCH_TEXT );
        final Paginator paginator = toolBar.getPaginator();

        toolBar.getProgressBar().invoke( new SafeWorker<Integer, Object>()
        {
            @Override
            protected Integer doInBackground()
            {
                final Boolean direction = parameters.get( Keys.SEARCH_DIRECTION );

                int startPage = paginator.getCurrentPage();
                if ( toolBar.getLayouter().getPageLayout() == PageLayout.TWO_PAGES )
                { // skip one more page
                    startPage++;
                }
                return bookContent.findText( text, direction, startPage );
            }

            @Override
            protected void doneSafe()
            {
                final int page = getQuiet();
                if ( page > -1 )
                {
                    paginator.setCurrentPage( page );
                    layoutSwitcher.getCurrentPanels().highlightText( text );
                }
            }
        } );
    }

    public void setCharset(
        final Charset charset )
    {
        final PhysicalBook physicalBook = getBook().getPhysicalBook();
        physicalBook.setCharsetName( charset.name() );
        BookShelf.updatePhysical( physicalBook );
        bookContent = factory.createBookContent( book );
        setPage( toolBar.getPaginator().getCurrentPage() );
    }

    /**
     * display a page of the specified number
     * 
     * @param pageNumber a number of a page to display
     */
    @SuppressWarnings( "unchecked" )
    public void setPage(
        final int pageNumber )
    {
        final T leftPage = bookContent.getPage( pageNumber );
        if ( layoutSwitcher.getCurrentLayout() == PageLayout.TWO_PAGES )
        {
            final T rightPage = bookContent.getPage( pageNumber + 1 );
            layoutSwitcher.getCurrentPanels().setContent( leftPage, rightPage );
        }
        else
        {
            layoutSwitcher.getCurrentPanels().setContent( leftPage );
        }
    }

    @Override
    public void setVisible(
        final boolean visible )
    {
        super.setVisible( visible );
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                if ( visible )
                { // render the content
                    // todo remember the starting page
                    toolBar.getPaginator().setPageCount( bookContent.getPageCount() );
                }
            }
        } );
    }
}
