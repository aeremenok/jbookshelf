/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author eav 2009
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO<T extends Serializable, ID extends Serializable>
{
    void clear();

    List<T> findAll();

    List<T> findByExample(
        T example,
        String... excludeProperties );

    void flush();

    T getById(
        ID id );

    T makePersistent(
        T entity );

    void makeTransient(
        final T entity );
}
