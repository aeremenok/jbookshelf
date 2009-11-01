/**
 * 
 */
package org.jbookshelf.model.db.api.spec.dao;

import org.jbookshelf.model.db.api.NamedDAO;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;

/**
 * @author eav
 */
public interface ICategoryDAO
    extends
    NamedDAO<ICategory>
{
    ICategory getOrAdd(
        String name,
        ICategory parent );

    void moveBook(
        IBook book,
        ICategory oldCategory,
        ICategory newCategory );

    void setParent(
        ICategory parentCategory,
        ICategory childCategory );
}
