/**
 * 
 */
package test.dao;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.dao.AuthorDAO;

/**
 * @author eav 2009
 */
public class AuthorDAOTests
    extends UniqueDAOTests<Author, AuthorDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( AuthorDAOTests.class );

    public AuthorDAOTests()
    {
        super( new AuthorDAO() );
    }
}
