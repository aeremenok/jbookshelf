/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 */
public class BookDAO
    extends BookShelfDAO<Book>
{
    private static final Logger log = Logger.getLogger( BookDAO.class );

    /* (non-Javadoc)
     * @see org.jbookshelf.model.db.dao.GenericDAO#findByExample(java.io.Serializable, java.lang.String[])
     */
    @Override
    public List<Book> findByExample(
        final Book example,
        final String... excludeProperties )
    {
        log.debug( "findByExample" );
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.model.db.dao.GenericDAO#makePersistent(java.io.Serializable)
     */
    @Override
    public Book makePersistent(
        final Book entity )
    {
        log.debug( "makePersistent" );
        // TODO Auto-generated method stub
        return null;
    }
}
