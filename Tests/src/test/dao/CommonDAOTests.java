/**
 * 
 */
package test.dao;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jbookshelf.model.db.Named;
import org.jbookshelf.model.db.dao.UniqueDAO;
import org.junit.Test;

/**
 * @author eav 2009
 * @param <U>
 * @param <D>
 */
public abstract class CommonDAOTests<U extends Named, D extends UniqueDAO<U>>
{
    protected final D dao;

    public CommonDAOTests(
        final D dao )
    {
        super();
        this.dao = dao;
    }

    @Test
    public void _makePersistent()
    {
        final U randomUnique = randomUnique();
        assertNull( randomUnique.getId() );

        final U persistent = dao.makePersistent( randomUnique );
        assertNotNull( persistent );
        assertNotNull( persistent.getId() );
    }

    @Test
    public void findAll()
    {
        final List<U> all = dao.findAll();
        assertNotNull( all );
        assertTrue( all.size() > 0 );
    }

    @Test
    public void getById()
    {
        final List<U> all = dao.findAll();
        final U u = all.get( 0 );
        final U byId = dao.getById( u.getId() );
        assertNotNull( byId );
        assertEquals( u, byId );
    }

    @Test
    public void makeTransient()
    {
        final List<U> all = dao.findAll();
        final U u = all.get( 0 );

        final Long id = u.getId();

        dao.makeTransient( u );
        assertNull( u.getId() );

        final U byId = dao.getById( id );
        assertNull( byId );
    }

    public abstract U randomUnique();
}
