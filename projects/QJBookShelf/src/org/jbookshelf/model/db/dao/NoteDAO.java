/**
 * 
 */
package org.jbookshelf.model.db.dao;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.INote;

/**
 * @author eav 2009
 */
public class NoteDAO
    extends AbstractDAO<INote>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( NoteDAO.class );

    @Override
    public INote makePersistent(
        final INote entity )
    {
        return null;
    }
}
