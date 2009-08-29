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

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutSwitcher;
import org.jbookshelf.view.swinggui.reader.toolbar.CharsetChooser;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.Layouter;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;
import org.jbookshelf.view.swinggui.reader.toolbar.Scalator;
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
    private Book                book;
    private BookContent<T>      bookContent;

    private static final Logger log = Logger.getLogger( ReaderWindow.class );

    /**
     * change the layout between one and two pages
     * 
     * @param layout new {@link PageLayout}
     */
    public void changeLayout(
        final PageLayout layout )
    {
        final LayoutSwitcher layoutSwitcher = Single.instance( LayoutSwitcher.class );
        layoutSwitcher.switchLayout( layout );

        setPage( Single.instance( Paginator.class ).getCurrentPage() );
        Single.instance( Paginator.class ).setPageLayout( layout );
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                layoutSwitcher.getCurrentPanels().setScale( Single.instance( Scalator.class ).getScale() );
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

    @PostConstruct
    public void init()
    {
        setContentPane( new JPanel( new BorderLayout() ) );

        add( Single.instance( ReaderToolBar.class ), BorderLayout.NORTH );
        add( Single.instance( LayoutSwitcher.class ), BorderLayout.CENTER );

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
        else
        {
            final LayoutSwitcher layoutSwitcher = Single.instance( LayoutSwitcher.class );
            if ( Features.SCALING.equals( propertyName ) )
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
            else
            {
                layoutSwitcher.propertyChange( evt );
            }
        }
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
        final Paginator paginator = Single.instance( Paginator.class );

        Single.instance( ProgressBar.class ).invoke( new SafeWorker<Integer, Object>()
        {
            @Override
            protected Integer doInBackground()
            {
                final Boolean direction = parameters.get( Keys.SEARCH_DIRECTION );

                int startPage = paginator.getCurrentPage();
                if ( Single.instance( Layouter.class ).getPageLayout() == PageLayout.TWO_PAGES )
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
                    Single.instance( LayoutSwitcher.class ).getCurrentPanels().highlightText( text );
                }
            }
        } );
    }

    public void setBook(
        final Long bookId )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<Book, Object>()
        {
            @SuppressWarnings( "unchecked" )
            @Override
            protected Book doInBackground()
            {
                ReaderWindow.this.book = BookShelf.bookById( bookId );
                ReaderWindow.this.bookContent = Single.instance( ReaderFactory.class ).createBookContent( book );
                return book;
            }

            @Override
            protected void doneSafe()
            {
                setTitle( book.getName() );
                // render the content
                // todo remember the starting page
                Single.instance( Paginator.class ).setPageCount( bookContent.getPageCount() );

                final String charsetName = book.getPhysicalBook().getCharsetName();
                Single.instance( CharsetChooser.class ).setCharset( charsetName );
            }
        } );
    }

    @SuppressWarnings( "unchecked" )
    public void setCharset(
        final Charset charset )
    {
        final PhysicalBook physicalBook = getBook().getPhysicalBook();
        physicalBook.setCharsetName( charset.name() );
        BookShelf.updatePhysical( physicalBook );
        bookContent = Single.instance( ReaderFactory.class ).createBookContent( book );
        setPage( Single.instance( Paginator.class ).getCurrentPage() );
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
        final LayoutSwitcher layoutSwitcher = Single.instance( LayoutSwitcher.class );
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
}
