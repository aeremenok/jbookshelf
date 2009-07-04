/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;

/**
 * a panel for control scale and layout
 * 
 * @author eav 2009
 */
public class Scalator
    extends JPanel
{
    public static enum Layout
    {
        ONE_PAGE,
        TWO_PAGES
    }

    private class LayoutAction
        extends TranslatableAction
    {
        public LayoutAction()
        {
            super();
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final boolean isOne = getPageLayout() == Layout.ONE_PAGE;
            setPageLayout( isOne
                ? Layout.TWO_PAGES : Layout.ONE_PAGE );
            putValue( SMALL_ICON, IMG.icon( isOne
                ? IMG.RIGHT_CLOSE_PNG : IMG.RIGHT_NEW_PNG ) );
        }
    }

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

    @SuppressWarnings( "unused" )
    private static final Logger log           = Logger.getLogger( Scalator.class );
    public static final String  SCALE         = "scale";
    public static final String  LAYOUT        = "layout";

    public final JComboBox      scaleComboBox = new JComboBox();

    private int                 scale;

    private int                 max;
    private int                 min;
    private int                 step;

    private Layout              pageLayout;

    private final ZoomOutAction zoomOutAction = new ZoomOutAction();
    private final ZoomInAction  zoomInAction  = new ZoomInAction();

    public Scalator(
        final int min,
        final int max,
        final int step )
    {
        this();
        setScaleBounds( min, max, step );
    }

    private Scalator()
    {
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        add( new JButton( zoomOutAction ) );
        add( scaleComboBox );
        add( new JButton( zoomInAction ) );
        final LayoutAction layoutAction = new LayoutAction();
        add( new JButton( layoutAction ) );

        scaleComboBox.addItemListener( new ItemListener()
        {
            @Override
            public void itemStateChanged(
                final ItemEvent e )
            {
                setScale( min + scaleComboBox.getSelectedIndex() * step );
            }
        } );

        layoutAction.actionPerformed( null );
    }

    /**
     * @return the pageLayout
     */
    public Layout getPageLayout()
    {
        return this.pageLayout;
    }

    /**
     * @return the scale
     */
    public int getScale()
    {
        return this.scale;
    }

    /**
     * @param pageLayout the pageLayout to set
     */
    public void setPageLayout(
        final Layout pageLayout )
    {
        this.pageLayout = pageLayout;
        firePropertyChange( LAYOUT, null, pageLayout );
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(
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
        firePropertyChange( SCALE, null, scale );
        zoomInAction.setEnabled( scale < max );
        zoomOutAction.setEnabled( scale > min );
    }

    public void setScaleBounds(
        final int min,
        final int max,
        final int step )
    {
        if ( max - min < 0 )
        {
            throw new IllegalArgumentException();
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
}
