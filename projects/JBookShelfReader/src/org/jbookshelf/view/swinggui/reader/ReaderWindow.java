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
import org.jbookshelf.view.swinggui.reader.textview.MultiPageLayoutPanel;
import org.jbookshelf.view.swinggui.reader.textview.pagelayout.PageLayout;
import org.jbookshelf.view.swinggui.reader.toolbar.CharsetChooser;
import org.jbookshelf.view.swinggui.reader.toolbar.PageLayoutSwitcher;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;
import org.jbookshelf.view.swinggui.reader.toolbar.ScaleTuner;
import org.jbookshelf.view.swinggui.reader.toolbar.PageLayoutSwitcher.PageLayoutType;
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
    private static final Logger log = Logger.getLogger( ReaderWindow.class );

    private Book                book;
    private BookContent<T>      bookContent;

    @SuppressWarnings( "unchecked" )
    public void followPaginator()
    {
        final Paginator paginator = Single.instance( Paginator.class );
        final MultiPageLayoutPanel layoutable = Single.instance( MultiPageLayoutPanel.class );

        final int newCurrentPage = paginator.getCurrentPage();

        final T leftPage = bookContent.getPageContent( newCurrentPage );
        if ( Single.instance( PageLayoutSwitcher.class ).getCurrentLayout() == PageLayoutType.TWO_PAGES )
        {
            final T rightPage = bookContent.getPageContent( newCurrentPage + 1 );
            layoutable.followLayouter().displayPages( leftPage, rightPage );
        }
        else
        {
            layoutable.followLayouter().displayPages( leftPage );
        }
    }

    public Book getBook()
    {
        return this.book;
    }

    public BookContent<T> getBookContent()
    {
        return this.bookContent;
    }

    /**
     * search text in the displayed book and go to its position if its found
     * 
     * @param searchParameters text and direction to search ( {@link Boolean#TRUE} - forward, {@link Boolean#FALSE} -
     *            backward, null - forward from start )
     */
    public void goToText(
        final Parameters searchParameters )
    {
        final String text = searchParameters.get( Keys.SEARCH_TEXT );
        final Paginator paginator = Single.instance( Paginator.class );

        Single.instance( ProgressBar.class ).invoke( new SafeWorker<Integer, Object>()
        {
            @Override
            protected Integer doInBackground()
            {
                final Boolean direction = searchParameters.get( Keys.SEARCH_DIRECTION );

                int startPage = paginator.getCurrentPage();
                if ( Single.instance( PageLayoutSwitcher.class ).getCurrentLayout() == PageLayoutType.TWO_PAGES )
                { // skip one more page
                    startPage++;
                }
                return bookContent.findTextPage( text, direction, startPage );
            }

            @Override
            protected void doneSafe()
            {
                final int page = getQuiet();
                if ( page > -1 )
                {
                    paginator.setNewPage( page );

                    final PageLayout pageLayout = Single.instance( MultiPageLayoutPanel.class ).followLayouter();
                    pageLayout.highlightText( text );
                }
            }
        } );
    }

    @PostConstruct
    public void init()
    {
        setContentPane( new JPanel( new BorderLayout() ) );

        add( Single.instance( ReaderToolBar.class ), BorderLayout.NORTH );
        add( Single.instance( MultiPageLayoutPanel.class ), BorderLayout.CENTER );

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
                        saveLastReadPosition();
                        System.exit( 0 );
                    }
                } ).start();
            }
        } );

        AnnotationProcessor.process( this );
    }

    public void loadAndDisplayBook(
        final Long bookId )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<Book, Object>()
        {
            @SuppressWarnings( "unchecked" )
            @Override
            protected Book doInBackground()
            {
                book = BookShelf.bookById( bookId );
                bookContent = Single.instance( ReaderSpecific.class ).createBookContent( book );
                return book;
            }

            @Override
            protected void doneSafe()
            {
                setTitle( book.getName() );

                final String charsetName = book.getPhysicalBook().getCharsetName();
                Single.instance( CharsetChooser.class ).setCharset( charsetName );

                // setting page count causes rendering of content
                Single.instance( Paginator.class ).setPageCount( bookContent.getPageCount() );

                final Note lastRead = book.getLastRead();
                // todo avoid NPE
                if ( lastRead != null && lastRead.getPage() != null )
                {
                    Single.instance( MultiPageLayoutPanel.class ).followLayouter().goTo( lastRead );
                }
            }
        } );
    }

    /**
     * fired when a page is over
     * 
     * @param topic {@link Bookmark#PAGE}
     * @param bookmark contains current page info
     */
    @EventTopicSubscriber( topic = Bookmark.PAGE )
    public void onPageChanged(
        @SuppressWarnings( "unused" ) final String topic,
        @SuppressWarnings( "unused" ) final Bookmark bookmark )
    {
        followPaginator();
    }

    public void saveLastReadPosition()
    {
        log.debug( "saving last read position of " + book.getName() );
        final Bookmark bookmark = Single.instance( MultiPageLayoutPanel.class ).followLayouter().createBookmark();
        this.book.setRead( true );

        final Note lastRead = this.book.getLastRead();
        lastRead.setPage( bookmark.getPage() );
        lastRead.setPageCount( bookmark.getPageCount() );
        lastRead.setPosition( bookmark.getPosition() );

        BookShelf.mergeNote( lastRead );
        log.debug( "last read position saved: " + lastRead.getPosition() );
    }

    public void switchPageLayout()
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                final PageLayout pageLayout = Single.instance( MultiPageLayoutPanel.class ).followLayouter();
                pageLayout.scale( Single.instance( ScaleTuner.class ).getScale() );
                followPaginator();
            }
        } );
    }

    /**
     * reload book content with a specified charset
     * 
     * @param charset a {@link Charset} to load with
     */
    @SuppressWarnings( "unchecked" )
    public void useCharset(
        final Charset charset )
    {
        final PhysicalBook physicalBook = getBook().getPhysicalBook();
        physicalBook.setCharsetName( charset.name() );

        BookShelf.updatePhysical( physicalBook );
        bookContent = Single.instance( ReaderSpecific.class ).createBookContent( book );

        followPaginator();
    }
}
