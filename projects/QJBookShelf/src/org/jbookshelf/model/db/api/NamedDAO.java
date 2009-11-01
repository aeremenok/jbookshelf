/**
 * 
 */
package org.jbookshelf.model.db.api;

/**
 * @author eav
 * @param <T>
 */
public interface NamedDAO<T extends Named>
    extends
    DAO<T>
{
    T getByName(
        String name );

    T getOrAdd(
        String name );

    void rename(
        T named,
        String newName );
}
