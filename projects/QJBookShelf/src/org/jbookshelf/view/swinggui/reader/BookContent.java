/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.io.File;

/**
 * @author eav 2009
 * @param <T>
 */
public abstract class BookContent<T>
{
    protected int  pageCount;
    protected File file;

    public BookContent(
        final File file )
    {
        super();
        this.file = file;
    }

    public abstract T getPage(
        int pageNumber );

    /**
     * @return the pageCount
     */
    public int getPageCount()
    {
        return this.pageCount;
    }
}
