/**
 * 
 */
package org.jbookshelf.model.db.dao.alter;

import java.util.List;

import org.jbookshelf.model.db.Identifiable;

/**
 * @author eav
 * @param <I>
 */
public interface DAO<I extends Identifiable>
{
    List<I> find();
}
