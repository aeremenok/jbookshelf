/**
 * 
 */
package test.dao;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.jbookshelf.model.db.api.Identifiable;
import org.jbookshelf.model.db.dao.AbstractGenericDAO;
import org.junit.Test;

/**
 * @author eav 2009
 * @param <I>
 * @param <D>
 */
public abstract class CommonDAOTests<I extends Identifiable, D extends AbstractGenericDAO<I>>
{
    protected final D dao;

    public CommonDAOTests(
        final D dao )
    {
        super();
        this.dao = dao;
        makePersistent();
    }

    @Test
    public void findAll()
    {
        final List<I> all = dao.findAll();
        assertNotNull( all );
        assertTrue( all.size() > 0 );
    }

    @Test
    public void getById()
    {
        final List<I> all = dao.findAll();
        final I u = all.get( 0 );
        final I byId = dao.getById( u.getId() );
        assertNotNull( byId );
        assertEquals( u, byId );
    }

    @Test
    public void makePersistent()
    {
        final I randomIdentifiable = randomIdentifiable();
        assertNull( randomIdentifiable.getId() );

        final I persistent = dao.makePersistent( randomIdentifiable );
        assertNotNull( persistent );
        assertNotNull( persistent.getId() );

        assertEquals( randomIdentifiable, persistent );
    }

    @Test
    public void makeTransient()
    {
        final List<I> all = dao.findAll();
        final I u = all.get( 0 );

        final Serializable id = u.getId();

        dao.makeTransient( u );
        assertNull( u.getId() );

        final I byId = dao.getById( id );
        assertNull( byId );
    }

    public abstract I randomIdentifiable();
}
