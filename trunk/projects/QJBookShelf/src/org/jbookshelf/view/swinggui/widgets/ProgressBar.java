/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

import org.apache.log4j.Logger;
import org.jbookshelf.view.logic.SafeWorker;

/**
 * @author eav 2009
 */
public class ProgressBar
    extends Box
{
    private static final Logger     log         = Logger.getLogger( ProgressBar.class );

    private final JProgressBar      progressBar = new JProgressBar();

    private final List<SwingWorker> workers     = new ArrayList<SwingWorker>();

    public ProgressBar()
    {
        super( BoxLayout.X_AXIS );

        add( progressBar );

        progressBar.setIndeterminate( true );

        setVisible( false );
    }

    public void invoke(
        final Runnable runnable )
    {
        invoke( new SafeWorker<Object, Object>()
        {
            @Override
            protected Object doInBackground()
            {
                runnable.run();
                return null;
            }
        } );
    }

    public <T, V> void invoke(
        final SwingWorker<T, V> worker )
    {
        worker.getPropertyChangeSupport().addPropertyChangeListener( "state", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                switch ( (StateValue) evt.getNewValue() )
                {
                    case PENDING:
                        break;
                    case STARTED:
                        workers.add( worker );
                        break;
                    case DONE:
                        workers.remove( worker );
                        break;
                }
                setVisible( workers.size() > 0 );
            }
        } );
        worker.execute();
    }

    public void invokeSync(
        final Runnable runnable )
    {
        setVisible( true );
        try
        {
            runnable.run();
        }
        catch ( final Throwable e )
        {
            log.error( e, e );
            throw new Error( e );
        }
        setVisible( false );
    }
}