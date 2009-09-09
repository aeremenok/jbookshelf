/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.EventQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 */
public class TaskQueue
    implements
    ChangeListener
{
    private static final Logger   log       = Logger.getLogger( TaskQueue.class );

    private final Queue<Runnable> runnables = new ConcurrentLinkedQueue<Runnable>();

    public void enqueue(
        final Runnable runnable )
    {
        runnables.add( runnable );
    }

    @Override
    public void stateChanged(
        final ChangeEvent e )
    {
        final BoundedRangeModel model = (BoundedRangeModel) e.getSource();
        if ( model != null && !model.getValueIsAdjusting() && model.getMaximum() > 0 )
        { // xxx invoke events from queue after initializing
            while ( runnables.size() > 0 )
            {
                final Runnable runnable = runnables.poll();
                log.debug( "queue size=" + runnables.size() );
                EventQueue.invokeLater( runnable );
            }
        }
    }
}
