package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.widget.ChangeDocumentListener;

/**
 * a panel for control of text search
 * 
 * @author eav 2009
 */
public class TextFinder
    extends JPanel
{
    private class FindAction
        extends TranslatableAction
    {
        public FindAction()
        {
            super( null, IMG.icon( IMG.EDIT_FIND_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            final Parameters parameters = new Parameters();
            parameters.put( Keys.SEARCH_TEXT, searchTextField.getText() );
            parameters.put( Keys.SEARCH_DIRECTION, null );
            searchText( parameters );
        }
    }

    private class FindNextAction
        extends TranslatableAction
    {
        public FindNextAction()
        {
            super( null, IMG.icon( IMG.ARROW_DOWN_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            final Parameters parameters = new Parameters();
            parameters.put( Keys.SEARCH_TEXT, searchTextField.getText() );
            parameters.put( Keys.SEARCH_DIRECTION, true );
            searchText( parameters );
        }
    }

    private class FindPreviousAction
        extends TranslatableAction
    {
        public FindPreviousAction()
        {
            super( null, IMG.icon( IMG.ARROW_UP_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            final Parameters parameters = new Parameters();
            parameters.put( Keys.SEARCH_TEXT, searchTextField.getText() );
            parameters.put( Keys.SEARCH_DIRECTION, false );
            searchText( parameters );
        }
    }

    private final JTextField         searchTextField    = new JTextField( 20 );
    private final FindNextAction     findNextAction     = new FindNextAction();
    private final FindPreviousAction findPreviousAction = new FindPreviousAction();
    private final FindAction         findAction         = new FindAction();

    public TextFinder()
    {
        super();
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );
        add( searchTextField );
        add( new JButton( findAction ) );
        add( new JButton( findNextAction ) );
        add( new JButton( findPreviousAction ) );

        final ChangeDocumentListener listener = new ChangeDocumentListener( searchTextField )
        {
            @Override
            public void onChange(
                final String newText )
            {
                final boolean isEmpty = newText == null || "".equals( newText );
                findAction.setEnabled( !isEmpty );
                findNextAction.setEnabled( !isEmpty );
                findPreviousAction.setEnabled( !isEmpty );
            }
        };
        searchTextField.getDocument().addDocumentListener( listener );
        listener.onChange( searchTextField.getText() );
    }

    private void searchText(
        final Parameters parameters )
    {
        Single.instance( ReaderWindow.class ).searchText( parameters );
    }
}
