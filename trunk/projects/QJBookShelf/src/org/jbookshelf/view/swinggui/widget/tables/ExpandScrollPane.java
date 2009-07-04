/**
 * 
 */
package org.jbookshelf.view.swinggui.widget.tables;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.ProgressBar;

/**
 * listens for the vertical scrollbar changes. if its position reaches TRESHOLD - expands the table model
 * 
 * @author eav 2009
 */
public class ExpandScrollPane
    extends JScrollPane
    implements
    ChangeListener
{
    private static final double     TRESHOLD = 0.8;

    private final ExpandTableModel  model;
    private final BoundedRangeModel boundedRangeModel;

    public ExpandScrollPane(
        final JTable table,
        final ExpandTableModel model )
    {
        super( table );
        this.model = model;
        boundedRangeModel = getVerticalScrollBar().getModel();
        boundedRangeModel.addChangeListener( this );
    }

    @Override
    public void stateChanged(
        final ChangeEvent e )
    {
        final int value = boundedRangeModel.getValue();
        final int maximum = boundedRangeModel.getMaximum() - boundedRangeModel.getExtent();
        if ( maximum > 0 && value / maximum > TRESHOLD )
        {
            Single.instance( ProgressBar.class ).invoke( new Runnable()
            {
                @Override
                public void run()
                {
                    model.expand();
                }
            } );
        }
    }
}
