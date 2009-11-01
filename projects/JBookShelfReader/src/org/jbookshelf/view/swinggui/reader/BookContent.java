/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.io.File;

import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

/**
 * stores the content, performs search, divides it into pages if possible
 * 
 * @author eav 2009
 * @param <PageType> type of displayed pages
 */
public abstract class BookContent<PageType>
{
    protected int         pageCount;
    protected final File  file;
    protected final IBook book;

    public BookContent(
        final IBook book )
    {
        this.book = book;
        final IPhysicalBook physical = book.getPhysicalBook();
        file = physical.getUnpackedFile() != null
            ? physical.getUnpackedFile() : physical.getFile();
    }

    /**
     * @param text text to find
     * @param direction null - forward from page 0, TRUE - forward from currentPage, FALSE - backward from currentPage
     * @param currentPage page to start search
     * @return first page containing the specified text in the specified direction (-1 if not found)
     */
    public abstract int findTextPage(
        final String text,
        final Boolean direction,
        final int currentPage );

    /**
     * @param pageNumber page number in the content [0, getPageCount()-1]
     * @return page content
     */
    public abstract PageType getPageContent(
        int pageNumber );

    public int getPageCount()
    {
        return this.pageCount;
    }
}
