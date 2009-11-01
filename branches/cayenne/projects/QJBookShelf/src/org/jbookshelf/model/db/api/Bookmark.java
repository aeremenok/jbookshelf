/**
 * 
 */
package org.jbookshelf.model.db.api;

import org.jbookshelf.model.db.api.spec.IBook;

/**
 * @author eav 2009
 */
public interface Bookmark
    extends
    Identifiable
{
    String POSITION = "POSITION";
    String PAGE     = "PAGE";

    IBook getBook();

    Integer getPage();

    Integer getPageCount();

    Float getPosition();

    Float getRelativePageSize();

    void setBook(
        IBook book );

    void setPage(
        final Integer page );

    void setPageCount(
        final Integer pageCount );

    void setPosition(
        final Float position );

    void setRelativePageSize(
        Float pageSize );
}
