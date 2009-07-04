/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeSupport;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbookshelf.view.swinggui.actions.TranslatableAction;
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
            setCurrentPage( 1 );
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
            setCurrentPage( pageCount );
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
            setCurrentPage( ++currentPage );
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
            setCurrentPage( --currentPage );
        }
    }

    public static final String          PAGE                  = "page";

    private int                         pageCount;
    private int                         currentPage;
    private final JTextField            pageSelector          = new JTextField( 3 );
    private final JLabel                pageCountLabel        = new JLabel();

    private final FirstAction           firstAction           = new FirstAction();
    private final PreviousAction        previousAction        = new PreviousAction();
    private final NextAction            nextAction            = new NextAction();
    private final LastAction            lastAction            = new LastAction();

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport( this );

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

        pageSelector.getDocument().addDocumentListener( new ChangeDocumentListener( pageSelector )
        {
            @Override
            public void onChange(
                final String newText )
            {
                setCurrentPageImpl( Integer.valueOf( newText.trim() ) - 1 );
            }
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

    public PropertyChangeSupport getPropertyChangeSupport()
    {
        return propertyChangeSupport;
    }

    /**
     * set currentPage and the value of pageSelector
     * 
     * @param currentPage new currentPage
     */
    public void setCurrentPage(
        final int currentPage )
    {
        setCurrentPageImpl( currentPage );
        pageSelector.setText( this.currentPage + 1 + "" );
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

        propertyChangeSupport.firePropertyChange( PAGE, -1, currentPage );
    }
}
