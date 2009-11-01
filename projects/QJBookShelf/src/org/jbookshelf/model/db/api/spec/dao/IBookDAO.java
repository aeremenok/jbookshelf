/**
 * 
 */
package org.jbookshelf.model.db.api.spec.dao;

import java.util.List;
import java.util.Set;

import org.jbookshelf.model.db.api.NamedDAO;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

/**
 * @author eav
 */
public interface IBookDAO
    extends
    NamedDAO<IBook>
{
    IBook create(
        String bookName,
        String authorName,
        String categoryName,
        IPhysicalBook physicalUnit );

    Set<IAuthor> getAuthors(
        IBook book );

    Set<ICategory> getCategories(
        IBook book );

    Set<INote> getNotes(
        IBook book );

    void mergeRelatedBooks(
        IBook book,
        List<IBook> relatedBooks );

    int totalCount();

    void update(
        IBook book );
}
