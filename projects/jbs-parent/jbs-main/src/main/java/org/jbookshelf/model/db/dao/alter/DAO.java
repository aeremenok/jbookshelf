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
        final Serializable id );

    List<I> findAll();

    I getById(
        final Serializable id );

    I persist(
        final I book );

    void update(
        final I book );
}
