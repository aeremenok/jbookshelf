/**
 * 
 */
package test.dao;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.dao.IAuthorDAO;

/**
 * @author eav 2009
 */
public class AuthorDAOTests
    extends UniqueDAOTests<IAuthor, IAuthorDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( AuthorDAOTests.class );

    public AuthorDAOTests()
    {
        super( null );
    }
}
