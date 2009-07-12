/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.io.File;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;

/**
 * stores the content, performs search, divides it into pages
 * 
 * @author eav 2009
 * @param <PageType> type of divided pages
 */
public abstract class BookContent<PageType>
{
    protected int        pageCount;
    protected final File file;
    protected final Book book;

    public BookContent(
        final Book book )
    {
        this.book = book;
        final PhysicalBook physical = book.getPhysicalBook();
        file = physical.getUnpackedFile() != null
            ? physical.getUnpackedFile() : physical.getFile();
    }

    /**
     * @param text text to find
     * @param direction null - forward from page 0, TRUE - forward from currentPage, FALSE - backward from currentPage
     * @param currentPage page to start search
     * @return first page containing the specified text in the specified direction
     */
    public abstract int findText(
        final String text,
        final Boolean direction,
        final int currentPage );

    /**
     * @param pageNumber page number in the content [0, getPageCount()-1]
     * @return page content
     */
    public abstract PageType getPage(
        int pageNumber );

    /**
     * @return the pageCount
     */
    public int getPageCount()
    {
        return this.pageCount;
    }

    public void onClose()
    {}
}
