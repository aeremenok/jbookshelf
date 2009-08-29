/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 * @param <PageType>
 */
public abstract class ReaderFactory<PageType>
{
    // todo enum?
    public static final String   TOC        = "TOC";
    public static final String   BOOKMARKS  = "BOOKMARKS";
    public static final String   THUMBNAILS = "THUMBNAILS";
    public static final String   NOTES      = "NOTES";
    public static final String   CHARSET    = "CHARSET";
    public static final String   FONT       = "FONT";
    public static final String   SEARCH     = "SEARCH";
    public static final String   LAYOUT     = "LAYOUT";
    public static final String   SCALING    = "SCALING";
    public static final String   PAGING     = "PAGING";

    protected final List<String> features   = new ArrayList<String>();

    public abstract BookContent<PageType> createBookContent(
        Book book );

    public List<String> getFeatures()
    {
        return this.features;
    }
}
