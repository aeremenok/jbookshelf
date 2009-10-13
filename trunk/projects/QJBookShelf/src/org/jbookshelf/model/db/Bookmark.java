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
    extends
    Identifiable
{
    String POSITION = "POSITION";
    String PAGE     = "PAGE";

    Book getBook();

    Integer getPage();

    Integer getPageCount();

    Float getPosition();

    Float getRelativePageSize();

    void setBook(
        @Nonnull final Book book );

    void setPage(
        @Nonnull @Nonnegative final Integer page );

    void setPageCount(
        final Integer pageCount );

    void setPosition(
        @Nonnull @Nonnegative final Float position );

    void setRelativePageSize(
        Float pageSize );
}
