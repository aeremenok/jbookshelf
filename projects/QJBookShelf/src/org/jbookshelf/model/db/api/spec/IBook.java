/**
 * 
 */
package org.jbookshelf.model.db.api.spec;

import java.util.Set;

import org.jbookshelf.model.db.api.HasBooks;

/**
 * @author eav
 * @param <B>
 * @param <A>
 * @param <C>
 * @param <N>
 */
public interface IBook<B extends IBook, A extends IAuthor, C extends ICategory, N extends INote>
    extends
    HasBooks
{
    Set<A> getAuthors();

    Set<C> getCategories();

    INote getLastRead();

    Set<N> getNotes();

    IPhysicalBook getPhysicalBook();

    Set<B> getRelatedBooks();

    boolean isRead();

    void setLastRead(
        final INote lastRead );

    void setPhysicalBook(
        final IPhysicalBook physicalBook );

    void setRead(
        final boolean isRead );
}
