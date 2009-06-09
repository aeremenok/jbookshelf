/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.event.MergeEvent;
import org.hibernate.event.MergeEventListener;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.def.DefaultPersistEventListener;

/**
 * @author eav
 */
public class Timestamper
    extends DefaultPersistEventListener
    implements
    MergeEventListener
{
    @Override
    public void onMerge(
        final MergeEvent arg0 )
        throws HibernateException
    {
        timestamp( arg0.getResult() );
    }

    @Override
    public void onMerge(
        final MergeEvent arg0,
        final Map arg1 )
        throws HibernateException
    {
        timestamp( arg0.getResult() );
    }

    @Override
    public void onPersist(
        final PersistEvent event )
        throws HibernateException
    {
        timestamp( event.getObject() );
        super.onPersist( event );
    }

    /**
     * @param entity
     */
    private void timestamp(
        final Object entity )
    {
        if ( entity instanceof Timestampable )
        {
            ((Timestampable) entity).timestamp();
        }
    }
}
