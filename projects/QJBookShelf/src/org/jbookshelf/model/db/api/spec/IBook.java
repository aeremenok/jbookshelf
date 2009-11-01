/**
 * 
 */
package org.jbookshelf.model.db.api.spec;

import java.util.Set;

import javax.persistence.Transient;

import org.jbookshelf.model.db.api.HasBooks;

/**
 * @author eav
 */
public interface IBook
    extends
    HasBooks
{
    public Set<IAuthor> getAuthors();

    Set<ICategory> getCategories();

    INote getLastRead();

    Set<INote> getNotes();

    IPhysicalBook getPhysicalBook();

    Set<IBook> getRelatedBooks();

    @Transient
    boolean isRead();

    void setLastRead(
        final INote lastRead );

    void setPhysicalBook(
        final IPhysicalBook physicalBook );

    void setRead(
        final boolean isRead );
}
