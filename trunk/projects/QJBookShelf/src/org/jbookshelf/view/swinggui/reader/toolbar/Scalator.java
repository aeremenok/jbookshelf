/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jbookshelf.view.swinggui.actions.TranslatableAction;

/**
 * a panel for control scale and layout
 * 
 * @author eav 2009
 */
public class Scalator
    extends JPanel
{
    private class ZoomInAction
        extends TranslatableAction
    {
        public ZoomInAction()
        {
            super( null, IMG.icon( IMG.ZOOM_IN_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final int selectedIndex = scaleComboBox.getSelectedIndex();
            if ( selectedIndex < scaleComboBox.getItemCount() - 1 )
            {
                scaleComboBox.setSelectedIndex( selectedIndex + 1 );
            }
        }
    }

    private class ZoomOutAction
        extends TranslatableAction
    {
        public ZoomOutAction()
        {
            super( null, IMG.icon( IMG.ZOOM_OUT_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final int selectedIndex = scaleComboBox.getSelectedIndex();
            if ( selectedIndex > 0 )
            {
                scaleComboBox.setSelectedIndex( selectedIndex - 1 );
            }
        }
    }

    public final JComboBox scaleComboBox = new JComboBox();

    private int            scale;

    private int            max;
    private int            min;
    private int            step;

    public final Action    zoomOutAction = new ZoomOutAction();
    public final Action    zoomInAction  = new ZoomInAction();

    private int            start;

    public Scalator(
        final int min,
        final int max,
        final int step,
        final int start )
    {
        this();
        this.start = start;
        setScaleBounds( min, max, step );
        reset();
    }

    private Scalator()
    {
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        add( new JButton( zoomOutAction ) );
        add( scaleComboBox );
        add( new JButton( zoomInAction ) );

        scaleComboBox.addItemListener( new ItemListener()
        {
            @Override
            public void itemStateChanged(
                final ItemEvent e )
            {
                if ( ItemEvent.SELECTED == e.getStateChange() )
                {
                    setScale( min + scaleComboBox.getSelectedIndex() * step );
                }
            }
        } );
    }

    public int getScale()
    {
        return this.scale;
    }

    public void reset()
    {
        scaleComboBox.setSelectedItem( start + "%" );
    }

    public void setScaleBounds(
        final int min,
        final int max,
        final int step )
    {
        if ( max - min < 0 )
        {
            throw new IllegalArgumentException( "max = " + max + ", min = " + min );
        }
        this.min = min;
        this.max = max;
        this.step = step;
        scaleComboBox.removeAllItems();
        for ( int i = min; i <= max; i += step )
        {
            scaleComboBox.addItem( i + "%" );
        }
        setScale( min );
    }

    private void setScale(
        int scale )
    {
        if ( scale < min )
        {
            scale = min;
        }
        else if ( scale > max )
        {
            scale = max;
        }

        this.scale = scale;
        firePropertyChange( Features.SCALING, null, scale );
        zoomInAction.setEnabled( scale < max );
        zoomOutAction.setEnabled( scale > min );
    }
}
