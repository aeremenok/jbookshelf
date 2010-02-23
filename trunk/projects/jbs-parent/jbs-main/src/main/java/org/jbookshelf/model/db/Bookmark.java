/**
 * 
 */
package org.jbookshelf.model.db;

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
        final Book book );

    void setPage(
        final Integer page );

    void setPageCount(
        final Integer pageCount );

    void setPosition(
        final Float position );

    void setRelativePageSize(
        Float pageSize );
}
