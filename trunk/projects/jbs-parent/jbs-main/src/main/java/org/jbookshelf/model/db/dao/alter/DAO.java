/**
 * 
 */
package org.jbookshelf.model.db.dao.alter;

import java.io.Serializable;
import java.util.List;

import org.jbookshelf.model.db.Identifiable;

/**
 * @author eav
 * @param <I>
 */
public interface DAO<I extends Identifiable>
{
    void delete(
        final I entity );

    List<I> findAll();

    I getById(
        final Serializable id );

    I persist(
        final I entity );

    void update(
        final I entity );
}
