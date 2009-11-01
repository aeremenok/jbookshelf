/**
 * 
 */
package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.api.NamedDAO;
import org.junit.Test;

/**
 * @author eav 2009
 * @param <U>
 * @param <D>
 */
public abstract class NamedDAOTests<U extends Named, D extends NamedDAO<U>>
    extends CommonDAOTests<U, D>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( NamedDAOTests.class );

    public NamedDAOTests(
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
        final U u = dao.create();
        u.setName( u.getClass().getSimpleName() + System.currentTimeMillis() );
        return u;
    }

}
