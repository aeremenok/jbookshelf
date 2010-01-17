/**
 * 
 */
package org.jbookshelf.model.db.dao;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Note;

/**
 * @author eav 2009
 */
public class NoteDAO
    extends AbstractGenericDAO<Note>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( NoteDAO.class );

    @Override
    public Note makePersistent(
        final Note entity )
    {
        return null;
    }
}
