/**
 * 
 */
package test.dao;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.dao.ICategoryDAO;
import org.jbookshelf.model.db.dao.CategoryDAO;

/**
 * @author eav 2009
 */
public class CategoryDAOTests
    extends NamedDAOTests<ICategory, ICategoryDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( CategoryDAOTests.class );

    public CategoryDAOTests()
    {
        super( new CategoryDAO() );
    }
}
