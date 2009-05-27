/**
 * 
 */
package org.jbookshelf.model.db;

import org.hibernate.HibernateException;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.def.DefaultPersistEventListener;

/**
 * @author eav
 */
public class Timestamper
    extends DefaultPersistEventListener
{
    /* (non-Javadoc)
     * @see org.hibernate.event.def.DefaultPersistEventListener#onPersist(org.hibernate.event.PersistEvent)
     */
    @Override
    public void onPersist(
        final PersistEvent event )
        throws HibernateException
    {
        // TODO Auto-generated method stub
        final Object entity = event.getObject();
        if ( entity instanceof Timestampable )
        {
            ((Timestampable) entity).timestamp();
        }
        super.onPersist( event );
    }
}
