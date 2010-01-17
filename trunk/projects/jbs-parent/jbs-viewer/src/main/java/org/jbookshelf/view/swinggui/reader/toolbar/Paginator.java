/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.PageLayoutSwitcher.PageLayoutType;

/**
 * a panel for page navigation
 * 
 * @author eav 2009
 */
public class Paginator
    extends JPanel
{
    private class FirstAction
        extends TranslatableAction
    {
        public FirstAction()
        {
            super( IMG.icon( IMG.FIRST_PNG ), tr( "First page" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            setNewPage( 0 );
        }
    }

    private class LastAction
        extends TranslatableAction
    {
        public LastAction()
        {
            super( IMG.icon( IMG.LAST_PNG ), tr( "Last page" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            setNewPage( pageCount - 1 );
        }
    }

    private class NextAction
        extends TranslatableAction
    {
        public NextAction()
        {
            super( IMG.icon( IMG.NEXT_PNG ), tr( "Next page" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final PageLayoutType currentLayout = Single.instance( PageLayoutSwitcher.class ).getCurrentLayout();
            currentPage += currentLayout == PageLayoutType.ONE_PAGE
                ? 1 : 2;
            setNewPage( currentPage );
        }
    }

    private class PreviousAction
        extends TranslatableAction
    {
        public PreviousAction()
        {
            super( IMG.icon( IMG.PREVIOUS_PNG ), tr( "Previous page" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final PageLayoutType currentLayout = Single.instance( PageLayoutSwitcher.class ).getCurrentLayout();
            currentPage -= currentLayout == PageLayoutType.ONE_PAGE
                ? 1 : 2;
            setNewPage( currentPage );
        }
    }

    private int                  pageCount;
    private int                  currentPage;
    private final JTextField     pageSelector   = new JTextField( 3 );
    private final JLabel         pageCountLabel = new JLabel();

    private final FirstAction    firstAction    = new FirstAction();
    private final PreviousAction previousAction = new PreviousAction();
    private final NextAction     nextAction     = new NextAction();
    private final LastAction     lastAction     = new LastAction();

    public Paginator()
    {
        super();
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        add( new JButton( firstAction ) );
        add( new JButton( previousAction ) );
        add( pageSelector );
        add( pageCountLabel );
        add( new JButton( nextAction ) );
        add( new JButton( lastAction ) );

        pageSelector.addKeyListener( new KeyAdapter()
        {
            @Override
            public void keyPressed(
                final KeyEvent e )
            {
                final char keyChar = e.getKeyChar();
                if ( keyChar == '\n' || keyChar == '\r' || keyChar == '\t' )
                {
                    final Integer page = Integer.valueOf( pageSelector.getText().trim() );
                    setCurrentPageImpl( page - 1 );
                }
            }
        } );
    }

    public int getCurrentPage()
    {
        return this.currentPage;
    }

    public int getPageCount()
    {
        return this.pageCount;
    }

    /**
     * set currentPage [0,pageCount-1] and the value of pageSelector [1,pageCount]
     * 
     * @param currentPage new currentPage
     */
    public void setNewPage(
        int currentPage )
    {
        final PageLayoutType currentLayout = Single.instance( PageLayoutSwitcher.class ).getCurrentLayout();
        currentPage = currentLayout == PageLayoutType.ONE_PAGE
            ? currentPage : currentPage % 2 == 0
                // odd page left, even page right 
                ? currentPage : currentPage - 1;

        final String t = currentPage + 1 + "";
        pageSelector.setText( t );
        setCurrentPageImpl( currentPage );
    }

    public void setPageCount(
        final int pageCount )
    {
        final int oldCount = this.pageCount;
        this.pageCount = pageCount;
        this.pageCountLabel.setText( "/ " + pageCount );

        setNewPage( oldCount > pageCount
            ? pageCount : 0 );
    }

    /**
     * set current page, but not affect the {@link Paginator#pageSelector}
     * 
     * @param currentPage new current page
     */
    private void setCurrentPageImpl(
        int currentPage )
    {
        if ( currentPage < 0 )
        {
            currentPage = 0;
        }
        else if ( currentPage > pageCount - 1 )
        {
            currentPage = pageCount - 1;
        }

        this.currentPage = currentPage;
        firstAction.setEnabled( this.currentPage > 0 );
        previousAction.setEnabled( this.currentPage > 0 );
        nextAction.setEnabled( this.currentPage < pageCount - 1 );
        lastAction.setEnabled( this.currentPage < pageCount - 1 );

        // todo use template pattern
        final Bookmark bookmark = new Note();
        bookmark.setBook( Single.instance( ReaderWindow.class ).getBook() );
        bookmark.setPage( currentPage );
        bookmark.setPageCount( pageCount );
        final float i = (currentPage + 1) / pageCount;
        bookmark.setPosition( i );
        EventBus.publish( Bookmark.PAGE, bookmark );
    }
}
