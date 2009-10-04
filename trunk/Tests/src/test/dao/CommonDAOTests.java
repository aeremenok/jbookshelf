/**
 * 
 */
package test.dao;

import java.util.List;

import junit.framework.TestCase;

import org.jbookshelf.model.db.Unique;
import org.jbookshelf.model.db.dao.BookShelfDAO;
import org.junit.Test;

/**
 * @author eav 2009
 * @param <U>
 * @param <D>
 */
public class CommonDAOTests<U extends Unique, D extends BookShelfDAO<U>>
    extends TestCase
{
    protected final D dao;

    public CommonDAOTests(
        final D dao )
    {
        super();
        this.dao = dao;
    }

    @Test
    public void testFindAll()
    {
        final List<U> all = dao.findAll();
        assertNotNull( all );
        assertTrue( all.size() > 0 );
    }

    @Test
    public void testFindById()
    {
        final List<U> all = dao.findAll();
        final U u = all.get( 0 );
        final U byId = dao.getById( u.getId() );
        assertNotNull( byId );
        assertEquals( u, byId );
    }
}
