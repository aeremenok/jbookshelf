/**
 * 
 */
package org.jbookshelf.view.logic;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 * @param <T>
 * @param <V>
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
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    protected void doneSafe()
    {}
}
