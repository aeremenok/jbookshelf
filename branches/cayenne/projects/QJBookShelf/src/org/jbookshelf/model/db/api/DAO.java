/**
 * 
 */
package org.jbookshelf.model.db.api;

import java.io.Serializable;
import java.util.List;

import org.jbookshelf.view.logic.Parameters;

/**
 * @author eav 2009
 * @param <T>
 */
public interface DAO<T extends Identifiable>
{
    T create();

    List<T> findAll();

    T getById(
        Serializable id );

    Class<? extends T> getEntityClass();

    T makePersistent(
        T entity );

    void makeTransient(
        final T entity );

    List<T> query(
        Parameters parameters );
}
