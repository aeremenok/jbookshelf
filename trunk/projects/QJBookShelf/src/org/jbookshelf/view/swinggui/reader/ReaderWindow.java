/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author eav 2009
 */
public class ReaderWindow
    extends JFrame
{
    @SuppressWarnings( "unused" )
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

    public ReaderWindow(
        final Book book )
    {
        super();
        this.book = book;
        setContentPane( new JPanel( new BorderLayout() ) );

        add( toolBar, BorderLayout.NORTH );

        final JSplitPane splitPane = new JSplitPane();
        add( splitPane );
        splitPane.setLeftComponent( leftContentPanel );
        splitPane.setRightComponent( rightContentPanel );

        setTitle( book.getName() );

        pack();
        setExtendedState( Frame.MAXIMIZED_BOTH );
    }

    /**
     * @return the book
     */
    public Book getBook()
    {
        return this.book;
    }
}
