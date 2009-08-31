/**
 * 
 */
package org.jbookshelf.model.db;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * @author eav 2009
 */
public interface Bookmark
{
    Book getBook();

    Long getId();

    Integer getPage();

    Integer getPageCount();

    Float getPosition();

    void setBook(
        @Nonnull final Book book );

    void setPage(
        @Nonnull @Nonnegative final Integer page );

    void setPageCount(
        final Integer pageCount );

    void setPosition(
        @Nonnull @Nonnegative final Float position );
}
