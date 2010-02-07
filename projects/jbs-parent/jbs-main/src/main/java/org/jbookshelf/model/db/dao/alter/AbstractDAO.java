/**
 * 
 */
package org.jbookshelf.model.db.dao.alter;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.jbookshelf.model.db.Identifiable;
import org.jbookshelf.model.db.util.HibernateUtil;

/**
 * @author eav
 * @param <I>
 */
public abstract class AbstractDAO<I extends Identifiable>
    implements
    DAO<I>
{
    private static final Logger log = Logger.getLogger( AbstractDAO.class );

    protected Connection closeSession(
        final Session session )
    {
        return session.close();
    }

    protected void logAndRollback(
        final Session session,
        final Exception e )
    {
        log.error( e, e );
        session.getTransaction().rollback();
    }

    protected Session openSession()
    {
        return HibernateUtil.getSession();
    }
}
