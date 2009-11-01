/**
 * 
 */
package test.dao;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.dao.IAuthorDAO;
import org.jbookshelf.model.db.dao.AuthorDAO;

/**
 * @author eav 2009
 */
public class AuthorDAOTests
    extends NamedDAOTests<IAuthor, IAuthorDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( AuthorDAOTests.class );

    public AuthorDAOTests()
    {
        super( new AuthorDAO() );
    }
}
