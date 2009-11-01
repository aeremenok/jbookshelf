/**
 * 
 */
package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.dao.NamedDAO;
import org.junit.Test;

/**
 * @author eav 2009
 * @param <U>
 * @param <D>
 */
public abstract class UniqueDAOTests<U extends Named, D extends NamedDAO<U>>
    extends CommonDAOTests<U, D>
{
    private static final Logger log = Logger.getLogger( UniqueDAOTests.class );

    public UniqueDAOTests(
        final D dao )
    {
        super( dao );
    }

    @Test
    public void getByName()
    {
        final List<U> all = dao.findAll();
        final U u = all.get( 0 );
        final U byId = dao.getByName( u.getName() );
        assertNotNull( byId );
        assertEquals( u, byId );
    }

    @Override
    public U randomIdentifiable()
    {
        try
        {
            final Class<U> entityClass = dao.getEntityClass();
            final U u = entityClass.newInstance();
            u.setName( entityClass.getSimpleName() + System.currentTimeMillis() );
            return u;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

}
