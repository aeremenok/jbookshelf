/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.ObjectEvent;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

/**
 * @author eav
 */
public class BrowserNavigator
    extends JPanel
{
    public static enum BrowserCommand
    {
        HOME, BACK, FORWARD, GOOGLE, SAVE
    }

    private class BackAction
        extends TranslatableAction
    {
        public BackAction()
        {
            super( null, IMG.icon( IMG.PREVIOUS_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderFactory.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.BACK ) );
        }
    }

    private class FwdAction
        extends TranslatableAction
    {
        public FwdAction()
        {
            super( null, IMG.icon( IMG.NEXT_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderFactory.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.FORWARD ) );
        }
    }

    private class GoogleAction
        extends TranslatableAction
    {
        public GoogleAction()
        {
            super( null, IMG.icon( IMG.GOOGLE_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderFactory.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.HOME ) );
        }
    }

    private class HomeAction
        extends TranslatableAction
    {
        public HomeAction()
        {
            super( null, IMG.icon( IMG.NEXT_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderFactory.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.HOME ) );
        }
    }

    private class SaveAction
        extends TranslatableAction
    {
        public SaveAction()
        {
            super( null, IMG.icon( IMG.DOCUMENT_SAVE_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            EventBus.publish( ReaderFactory.BROWSER, new ObjectEvent( BrowserNavigator.this, BrowserCommand.HOME ) );
        }
    }

    public BrowserNavigator()
    {
        super();
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        add( new JButton( new FwdAction() ) );
        add( new JButton( new BackAction() ) );
        add( new JButton( new HomeAction() ) );
        add( new JButton( new GoogleAction() ) );
        add( new JButton( new SaveAction() ) );

        // todo address
    }
}
