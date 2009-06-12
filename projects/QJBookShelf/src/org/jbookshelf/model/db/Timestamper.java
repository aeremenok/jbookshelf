/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.event.MergeEvent;
import org.hibernate.event.MergeEventListener;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.PersistEventListener;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;
import org.hibernate.event.def.DefaultMergeEventListener;
import org.hibernate.event.def.DefaultPersistEventListener;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

/**
 * @author eav
 */
public class Timestamper
    implements
    MergeEventListener,
    PersistEventListener,
    SaveOrUpdateEventListener
{
    private static final DefaultSaveOrUpdateEventListener SAVE_OR_UPDATE_EVENT_LISTENER = new DefaultSaveOrUpdateEventListener();
    private static final DefaultMergeEventListener        MERGE_EVENT_LISTENER          = new DefaultMergeEventListener();
    private static final DefaultPersistEventListener      PERSIST_EVENT_LISTENER        = new DefaultPersistEventListener();

    @Override
    public void onMerge(
        final MergeEvent arg0 )
        throws HibernateException
    {
        timestamp( arg0.getResult() );
        MERGE_EVENT_LISTENER.onMerge( arg0 );
    }

    @Override
    public void onMerge(
        final MergeEvent arg0,
        final Map arg1 )
        throws HibernateException
    {
        timestamp( arg0.getResult() );
        MERGE_EVENT_LISTENER.onMerge( arg0, arg1 );
    }

    @Override
    public void onPersist(
        final PersistEvent event )
        throws HibernateException
    {
        timestamp( event.getObject() );
        PERSIST_EVENT_LISTENER.onPersist( event );
    }

    @Override
    public void onPersist(
        final PersistEvent arg0,
        final Map arg1 )
        throws HibernateException
    {
        timestamp( arg0.getObject() );
        PERSIST_EVENT_LISTENER.onPersist( arg0, arg1 );
    }

    @Override
    public void onSaveOrUpdate(
        final SaveOrUpdateEvent arg0 )
        throws HibernateException
    {
        timestamp( arg0.getObject() );
        SAVE_OR_UPDATE_EVENT_LISTENER.onSaveOrUpdate( arg0 );
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
