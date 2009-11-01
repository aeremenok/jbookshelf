/**
 * 
 */
package org.jbookshelf.model.db.api.spec.dao;

import org.jbookshelf.model.db.api.DAO;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

/**
 * @author eav
 */
public interface IPhusicalBookDAO
    extends
    DAO<IPhysicalBook>
{
    void update(
        IPhysicalBook physical );
}
