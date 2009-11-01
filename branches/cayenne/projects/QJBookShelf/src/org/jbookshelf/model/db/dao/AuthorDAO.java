/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.EJBQLQuery;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.dao.IAuthorDAO;
import org.jbookshelf.model.db.cayenne.Author;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;

/**
 * @author eav 2009
 */
public class AuthorDAO
    extends CayenneNamedDAO<IAuthor>
    implements
    IAuthorDAO
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( AuthorDAO.class );

    @Override
    public IAuthor create()
    {
        return new Author();
    }

    @Override
    public Class<? extends IAuthor> getEntityClass()
    {
        return Author.class;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<IAuthor> query(
        final Parameters parameters )
    {
        final ObjectContext context = context();
        final String text = parameters.get( Keys.SEARCH_TEXT );
        final EJBQLQuery query = new EJBQLQuery( "select a from author where lower(a.name) like lower(:n%) " );
        query.setParameter( 0, text );
        return context.performQuery( query );
    }
}
