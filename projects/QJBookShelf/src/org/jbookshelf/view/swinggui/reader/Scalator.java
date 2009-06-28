/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;

/**
 * @author eav 2009
 */
public class Scalator
    extends JPanel
{
    private class LayoutAction
        extends TranslatableAction
    {
        public LayoutAction()
        {
            super( tr( "Change layout" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
        // todo
        }
    }

    private class ZoomInAction
        extends TranslatableAction
    {
        public ZoomInAction()
        {
            super( tr( "Zoom In" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
        // todo
        }
    }

    private class ZoomOutAction
        extends TranslatableAction
    {
        public ZoomOutAction()
        {
            super( tr( "Zoom Out" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
        // todo
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger log           = Logger.getLogger( Scalator.class );

    public final JComboBox      scaleComboBox = new JComboBox();

    public Scalator()
    {
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        scaleComboBox.addItem( "50%" );
        scaleComboBox.addItem( "100%" );
        scaleComboBox.addItem( "150%" );
        scaleComboBox.addItem( "200%" );

        add( new JButton( new ZoomOutAction() ) );
        add( scaleComboBox );
        add( new JButton( new ZoomInAction() ) );
        add( new JButton( new LayoutAction() ) );
    }

    protected void scaleChanged(
        @SuppressWarnings( "unused" ) final int scale )
    {}
}
