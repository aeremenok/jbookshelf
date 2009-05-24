/**
 * 
 */
package org.jbookshelf.model.db;

import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

/**
 * @author eav
 */
public class Timestamper
    extends DefaultSaveOrUpdateEventListener
{
    /* (non-Javadoc)
     * @see org.hibernate.event.def.DefaultSaveOrUpdateEventListener#onSaveOrUpdate(org.hibernate.event.SaveOrUpdateEvent)
     */
    @Override
    public void onSaveOrUpdate(
        final SaveOrUpdateEvent arg0 )
    {
        final Object entity = arg0.getObject();
        if ( entity instanceof Timestampable )
        {
            ((Timestampable) entity).timestamp();
        }
        super.onSaveOrUpdate( arg0 );
    }

}
