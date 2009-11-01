/**
 * 
 */
package org.jbookshelf.model.db.api;

import java.io.Serializable;
import java.util.List;

/**
 * @author eav 2009
 * @param <T>
 */
public interface DAO<T extends Identifiable>
{
    List<T> findAll();

    T getById(
        Serializable id );

    Class<T> getEntityClass();

    T makePersistent(
        T entity );

    void makeTransient(
        final T entity );
}
