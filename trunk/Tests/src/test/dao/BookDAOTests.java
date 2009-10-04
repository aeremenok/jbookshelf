/**
 * 
 */
package test.dao;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.dao.BookDAO;

/**
 * @author eav 2009
 */
public class BookDAOTests
    extends CommonDAOTests<Book, BookDAO>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookDAOTests.class );

    public BookDAOTests()
    {
        super( new BookDAO() );
    }

    @Override
    public Book randomUnique()
    {
        final Book book = new Book();
        book.setName( "Book" + System.currentTimeMillis() );
        return book;
    }
}
