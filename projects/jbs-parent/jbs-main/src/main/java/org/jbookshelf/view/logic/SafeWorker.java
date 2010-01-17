/**
 * 
 */
package org.jbookshelf.view.logic;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

/**
 * {@link SwingWorker} that ensures when is done safely
 * 
 * @author eav 2009
 * @param <T> the result type returned by this {@code SwingWorker's} {@code doInBackground} and {@code get} methods
 * @param <V> the type used for carrying out intermediate results by this {@code SwingWorker's} {@code publish} and
 *            {@code process} methods
 */
public abstract class SafeWorker<T, V>
    extends SwingWorker<T, V>
{
    private static final Logger log = Logger.getLogger( SafeWorker.class );

    private T                   result;

    /**
     * @return the result
     */
    public T getQuiet()
    {
        return this.result;
    }

    @Override
    protected void done()
    {
        try
        {
            result = get();
            doneSafe();
        }
        catch ( final Throwable e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    /**
     * called when is done and no exceptions occured
     */
    protected void doneSafe()
    {}
}
