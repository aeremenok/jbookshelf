/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.ObjectEvent;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;
import org.jbookshelf.view.swinggui.reader.types.html.EventDrivenRendererContext;
import org.jbookshelf.view.swinggui.reader.types.html.History;
import org.xnap.commons.gui.Builder;

/**
 * browser navigation buttons and address bar
 * 
 * @author eav 2009
 */
public class BrowserNavigator
    extends JPanel
{
    public static enum BrowserCommand
    {
        HOME,
        BACK,
        FORWARD,
        GOOGLE,
        SAVE,
        ADDRESS
    }

    private class BackAction
        extends TranslatableAction
    {
        public BackAction()
        {
            super( IMG.icon( IMG.PREVIOUS_PNG ), tr( "Back" ) );
            setEnabled( false );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderSpecific.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.BACK ) );
        }
    }

    private class FwdAction
        extends TranslatableAction
    {
        public FwdAction()
        {
            super( IMG.icon( IMG.NEXT_PNG ), tr( "Forward" ) );
            setEnabled( false );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderSpecific.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.FORWARD ) );
        }
    }

    private class GoogleAction
        extends TranslatableAction
    {
        public GoogleAction()
        {
            super( IMG.icon( IMG.GOOGLE_PNG ), tr( "Open google" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderSpecific.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.GOOGLE ) );
        }
    }

    private class HomeAction
        extends TranslatableAction
    {
        public HomeAction()
        {
            super( IMG.icon( IMG.HOME_PNG ), tr( "Back to book" ) );
            setEnabled( false );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderSpecific.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.HOME ) );
        }
    }

    private class SaveAction
        extends TranslatableAction
    {
        public SaveAction()
        {
            super( IMG.icon( IMG.DOCUMENT_SAVE_PNG ), tr( "Save as new book" ) );
            setEnabled( false );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderSpecific.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.SAVE ) );
        }
    }

    private final JTextField address    = new JTextField( 25 );

    private final Action     backAction = new BackAction();
    private final Action     fwdAction  = new FwdAction();
    private final Action     homeAction = new HomeAction();
    private final Action     saveAction = new SaveAction();

    public BrowserNavigator()
    {
        super();
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        add( new JButton( backAction ) );
        add( new JButton( fwdAction ) );
        add( new JButton( homeAction ) );
        add( new JButton( new GoogleAction() ) );
        add( new JButton( saveAction ) );

        add( address );
        address.addKeyListener( new KeyAdapter()
        {
            @Override
            public void keyPressed(
                final KeyEvent e )
            {
                final char keyChar = e.getKeyChar();
                switch ( keyChar )
                {
                    case '\n':
                    case '\r':
                    case '\t':
                        final ObjectEvent event = new ObjectEvent( BrowserNavigator.this, BrowserCommand.ADDRESS );
                        EventBus.publish( ReaderSpecific.BROWSER, event );
                        break;

                    default:
                        break;
                }
            }
        } );

        final Collection urls = Single.instance( History.class ).getUrls();
        Builder.addCompletion( address, new IngoreCaseCollectionCompletionModel( urls ) );

        AnnotationProcessor.process( this );
    }

    public JTextField getAddress()
    {
        return this.address;
    }

    @EventTopicSubscriber( topic = History.NAVIGATION )
    public void onHistoryNavigation(
        @SuppressWarnings( "unused" ) final String topic,
        final ObjectEvent event )
    {
        final History history = Single.instance( History.class );
        fwdAction.setEnabled( history.hasNext() );
        backAction.setEnabled( history.hasPrevious() );

        final Object source = event.getSource();
        if ( source instanceof EventDrivenRendererContext )
        {
            final EventDrivenRendererContext context = (EventDrivenRendererContext) source;
            final boolean bookDisplayed = context.isBookDisplayed();
            homeAction.setEnabled( !bookDisplayed );
            saveAction.setEnabled( !bookDisplayed );
        }

        address.setText( history.current() );
    }
}
