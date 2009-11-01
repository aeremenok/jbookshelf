/**
 * 
 */
package org.jbookshelf.model.db.api.spec.dao;

import java.util.List;

import org.jbookshelf.model.db.api.Bookmark;
import org.jbookshelf.model.db.api.DAO;
import org.jbookshelf.model.db.api.spec.INote;

/**
 * @author eav
 */
public interface INoteDAO
    extends
    DAO<INote>
{
    List<INote> getByPage(
        Bookmark bookmark );

    List<INote> getByPosition(
        Bookmark bookmark );

    void updateNote(
        INote note );
}
