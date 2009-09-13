/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutablePanel;
import org.jbookshelf.view.swinggui.reader.toolbar.CharsetChooser;
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
{
    private Book                book;
    private BookContent<T>      bookContent;

    private static final Logger log = Logger.getLogger( ReaderWindow.class );

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
        add( Single.instance( LayoutablePanel.class ), BorderLayout.CENTER );

        setIconImage( IMG.img( IMG.LOGO_PNG, 64 ) );

        pack();
        setExtendedState( Frame.MAXIMIZED_BOTH );

        addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(
                final WindowEvent e )
            { // clear content data, close streams, etc...
                // leave a thread to wait until the clearing is done
                new Thread( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        updateLastRead();
                        System.exit( 0 );
                    }
                } ).start();
            }
        } );

        AnnotationProcessor.process( this );
    }

    @EventTopicSubscriber( topic = Bookmark.PAGE )
    public void onPageChanged(
        @SuppressWarnings( "unused" ) final String topic,
        @SuppressWarnings( "unused" ) final Bookmark bookmark )
    {
        updateCurrentPage();
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

        final LayoutablePanel layoutable = Single.instance( LayoutablePanel.class );
        final Paginator paginator = Single.instance( Paginator.class );
        final Layouter layouter = Single.instance( Layouter.class );

        Single.instance( ProgressBar.class ).invoke( new SafeWorker<Integer, Object>()
        {
            @Override
            protected Integer doInBackground()
            {
                final Boolean direction = parameters.get( Keys.SEARCH_DIRECTION );

                int startPage = paginator.getCurrentPage();
                if ( layouter.getCurrentLayout() == PageLayout.TWO_PAGES )
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
                    paginator.setNewPage( page );
                    layoutable.getCurrentPanels().highlightText( text );
                }
            }
        } );
    }

    public void setBookById(
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

                final String charsetName = book.getPhysicalBook().getCharsetName();
                Single.instance( CharsetChooser.class ).setCharset( charsetName );

                // render the content
                // todo remember the starting page
                Single.instance( Paginator.class ).setPageCount( bookContent.getPageCount() );

                final Note lastRead = book.getLastRead();
                // todo avoid NPE
                if ( lastRead != null && lastRead.getPage() != null )
                {
                    Single.instance( LayoutablePanel.class ).getCurrentPanels().goTo( lastRead );
                }
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

        updateCurrentPage();
    }

    /**
     * change the layout between one and two pages
     */
    public void switchLayout()
    {
        final LayoutablePanel layoutable = Single.instance( LayoutablePanel.class );
        final Scalator scalator = Single.instance( Scalator.class );

        layoutable.switchLayout();
        updateCurrentPage();

        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                layoutable.getCurrentPanels().setScale( scalator.getScale() );
            }
        } );
    }

    /**
     * display a page of the specified number
     */
    @SuppressWarnings( "unchecked" )
    public void updateCurrentPage()
    {
        final Paginator paginator = Single.instance( Paginator.class );
        final LayoutablePanel layoutable = Single.instance( LayoutablePanel.class );

        final int newCurrentPage = paginator.getCurrentPage();

        final T leftPage = bookContent.getPage( newCurrentPage );
        if ( Single.instance( Layouter.class ).getCurrentLayout() == PageLayout.TWO_PAGES )
        {
            final T rightPage = bookContent.getPage( newCurrentPage + 1 );
            layoutable.getCurrentPanels().setContent( leftPage, rightPage );
        }
        else
        {
            layoutable.getCurrentPanels().setContent( leftPage );
        }
    }

    public void updateLastRead()
    {
        log.debug( "updating last read position of " + book.getName() );
        final Bookmark bookmark = Single.instance( LayoutablePanel.class ).getCurrentPanels().createBookmark();
        this.book.setRead( true );

        final Note lastRead = this.book.getLastRead();
        lastRead.setPage( bookmark.getPage() );
        lastRead.setPageCount( bookmark.getPageCount() );
        lastRead.setPosition( bookmark.getPosition() );

        BookShelf.mergeNote( lastRead );
        log.debug( "last read position updated: " + lastRead.getPosition() );
    }
}
