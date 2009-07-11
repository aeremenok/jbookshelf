/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.toolbar.Scalator.Layout;
import org.jbookshelf.view.swinggui.widget.ChangeDocumentListener;

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
            super( null, IMG.icon( IMG.FIRST_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            setCurrentPage( 0 );
        }
    }

    private class LastAction
        extends TranslatableAction
    {
        public LastAction()
        {
            super( null, IMG.icon( IMG.LAST_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            setCurrentPage( pageCount - 1 );
        }
    }

    private class NextAction
        extends TranslatableAction
    {
        public NextAction()
        {
            super( null, IMG.icon( IMG.NEXT_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            currentPage += readerToolBar.getScalator().getPageLayout() == Layout.ONE_PAGE
                ? 1 : 2;
            setCurrentPage( currentPage );
        }
    }

    private class PreviousAction
        extends TranslatableAction
    {
        public PreviousAction()
        {
            super( null, IMG.icon( IMG.PREVIOUS_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            currentPage -= readerToolBar.getScalator().getPageLayout() == Layout.ONE_PAGE
                ? 1 : 2;
            setCurrentPage( currentPage );
        }
    }

    public static final String   PAGE           = "page";

    private int                  pageCount;
    private int                  currentPage;
    private final JTextField     pageSelector   = new JTextField( 3 );
    private final JLabel         pageCountLabel = new JLabel();

    private final FirstAction    firstAction    = new FirstAction();
    private final PreviousAction previousAction = new PreviousAction();
    private final NextAction     nextAction     = new NextAction();
    private final LastAction     lastAction     = new LastAction();

    private final ReaderToolBar  readerToolBar;

    public Paginator(
        final ReaderToolBar readerToolBar )
    {
        super();
        this.readerToolBar = readerToolBar;
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        add( new JButton( firstAction ) );
        add( new JButton( previousAction ) );
        add( pageSelector );
        add( pageCountLabel );
        add( new JButton( nextAction ) );
        add( new JButton( lastAction ) );

        pageSelector.getDocument().addDocumentListener( new ChangeDocumentListener( pageSelector )
        {
            @Override
            public void onChange(
                final String newText )
            {
                setCurrentPageImpl( Integer.valueOf( pageSelector.getText() ) - 1 );
            }

            @Override
            public void removeUpdate(
                final DocumentEvent e )
            {}
        } );

        setPageCount( 1 );
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
    public void setCurrentPage(
        int currentPage )
    {
        currentPage = readerToolBar.getScalator().getPageLayout() == Layout.ONE_PAGE
            ? currentPage : currentPage % 2 == 0
                // odd page left, even page right 
                ? currentPage : currentPage - 1;

        final String t = currentPage + 1 + "";
        pageSelector.setText( t );
    }

    public void setPageCount(
        final int pageCount )
    {
        final int oldCount = this.pageCount;
        this.pageCount = pageCount;
        pageCountLabel.setText( "/ " + pageCount );

        setCurrentPage( oldCount > pageCount
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

        firePropertyChange( PAGE, -1, currentPage );
    }
}
