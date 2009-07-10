/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;
import org.jbookshelf.view.swinggui.reader.toolbar.Scalator;
import org.jbookshelf.view.swinggui.reader.toolbar.TextFinder;
import org.jbookshelf.view.swinggui.reader.toolbar.Scalator.Layout;
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
    private final Book                  book;

    private final ReaderToolBar         toolBar;
    private final ReaderContentPanel<T> leftContentPanel;
    private final ReaderContentPanel<T> rightContentPanel;
    private final BookContent<T>        bookContent;

    private final JSplitPane            splitPane = new JSplitPane();

    public ReaderWindow(
        final Book book,
        final ReaderFactory<T> factory )
    {
        super();
        this.book = book;

        toolBar = factory.createReaderToolBar( this );
        leftContentPanel = factory.createReaderContentPanel( this );
        rightContentPanel = factory.createReaderContentPanel( this );
        bookContent = factory.createBookContent( book.getPhysicalBook().getFile() );

        setContentPane( new JPanel( new BorderLayout() ) );

        add( toolBar, BorderLayout.NORTH );

        add( splitPane );
        splitPane.setLeftComponent( leftContentPanel );
        splitPane.setRightComponent( rightContentPanel );
        splitPane.setResizeWeight( 0.5 );

        setTitle( book.getName() );
        setIconImage( IMG.img( IMG.LOGO_PNG, 64 ) );

        initListeners();

        toolBar.getPaginator().setPageCount( bookContent.getPageCount() );

        pack();
        setExtendedState( Frame.MAXIMIZED_BOTH );
    }

    /**
     * change the layout between one and two pages
     * 
     * @param layout new {@link Layout}
     */
    public void changeLayout(
        final Layout layout )
    {
        rightContentPanel.setVisible( layout == Layout.TWO_PAGES );
        if ( rightContentPanel.isVisible() )
        {
            splitPane.setDividerLocation( 0.5 );
        }
    }

    /**
     * @return the book
     */
    public Book getBook()
    {
        return this.book;
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
        final Boolean direction = parameters.get( Keys.SEARCH_DIRECTION );

        final Paginator paginator = toolBar.getPaginator();
        final int page = bookContent.findText( text, direction, paginator.getCurrentPage() );
        if ( page > -1 )
        {
            paginator.setCurrentPage( page );
            EventQueue.invokeLater( new Runnable()
            {
                public void run()
                {
                    leftContentPanel.highlightText( text );
                    rightContentPanel.highlightText( text );
                }
            } );
        }
    }

    /**
     * display a page of the specified number
     * 
     * @param pageNumber a number of a page to display
     */
    public void setPage(
        final int pageNumber )
    {
        final T leftPage = bookContent.getPage( pageNumber );
        leftContentPanel.setContent( leftPage );
        final T rightPage = bookContent.getPage( pageNumber + 1 );
        rightContentPanel.setContent( rightPage );
    }

    /**
     * @param scale new scale of displayed text
     */
    public void setScale(
        final int scale )
    {
        leftContentPanel.setScale( scale );
        rightContentPanel.setScale( scale );
    }

    private void initListeners()
    {
        toolBar.getScalator().addPropertyChangeListener( Scalator.LAYOUT, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                changeLayout( (Layout) evt.getNewValue() );
            }
        } );
        toolBar.getScalator().addPropertyChangeListener( Scalator.SCALE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                setScale( (Integer) evt.getNewValue() );
            }
        } );

        toolBar.getPaginator().addPropertyChangeListener( Paginator.PAGE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                setPage( (Integer) evt.getNewValue() );
            }
        } );

        toolBar.getTextFinder().addPropertyChangeListener( TextFinder.SEARCH_TEXT, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                searchText( (Parameters) evt.getNewValue() );
            }
        } );
    }
}