/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.reader.Scalator.Layout;
import org.jdesktop.swingx.JXFrame;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author eav 2009
 */
public class ReaderWindow
    extends JXFrame
{
    private static final Logger log = Logger.getLogger( ReaderWindow.class );

    public static void main(
        final String[] args )
    {
        try
        {
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
            final Book book = new Book();
            book.setName( "test" );
            new ReaderWindow( book ).setVisible( true );
        }
        catch ( final UnsupportedLookAndFeelException e )
        {
            e.printStackTrace();
        }
    }

    private final ReaderToolBar      toolBar           = new ReaderToolBar( this );
    private final ReaderContentPanel leftContentPanel  = new ReaderContentPanel( this );
    private final ReaderContentPanel rightContentPanel = new ReaderContentPanel( this );
    private final Book               book;

    private final JSplitPane         splitPane         = new JSplitPane();

    public ReaderWindow(
        final Book book )
    {
        super();
        this.book = book;
        setContentPane( new JPanel( new BorderLayout() ) );

        add( toolBar, BorderLayout.NORTH );

        add( splitPane );
        splitPane.setLeftComponent( leftContentPanel );
        splitPane.setRightComponent( rightContentPanel );

        setTitle( book.getName() );

        initListeners();

        pack();
        setExtendedState( Frame.MAXIMIZED_BOTH );
    }

    /**
     * @param layout
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
     * @param parameters
     */
    public void searchText(
        final Parameters parameters )
    {
        final String text = parameters.get( Keys.SEARCH_TEXT );
        final Boolean direction = parameters.get( Keys.SEARCH_DIRECTION );
        log.debug( "searchText=" + text );
        log.debug( "searchDirection=" + direction );
        // todo
    }

    /**
     * @param i
     */
    public void setPage(
        @SuppressWarnings( "unused" ) final int i )
    {
        log.debug( "setPage" );
        // TODO Auto-generated method stub

    }

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
