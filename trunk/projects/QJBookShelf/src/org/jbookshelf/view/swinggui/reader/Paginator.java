/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeSupport;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbookshelf.view.swinggui.actions.TranslatableAction;

/**
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
        add( new JButton( nextAction ) );
        add( new JButton( lastAction ) );

        // todo add listener to pageSelector

        setPageCount( 1 );
        setCurrentPage( 1 );
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

    public void setCurrentPage(
        int currentPage )
    {
        if ( currentPage < 1 )
        {
            currentPage = 1;
        }
        else if ( currentPage > pageCount )
        {
            currentPage = pageCount;
        }

        if ( this.currentPage == currentPage )
        {
            return;
        }

        final int oldPage = this.currentPage;
        this.currentPage = currentPage;
        firstAction.setEnabled( this.currentPage != 1 );
        previousAction.setEnabled( this.currentPage != 1 );
        nextAction.setEnabled( this.currentPage != pageCount );
        lastAction.setEnabled( this.currentPage != pageCount );
        pageSelector.setText( this.currentPage - 1 + "" );

        propertyChangeSupport.firePropertyChange( PAGE, oldPage, currentPage );
    }

    public void setPageCount(
        final int pageCount )
    {
        final int oldCount = this.pageCount;
        this.pageCount = pageCount;

        setCurrentPage( oldCount > pageCount
            ? pageCount : 1 );
    }
}
