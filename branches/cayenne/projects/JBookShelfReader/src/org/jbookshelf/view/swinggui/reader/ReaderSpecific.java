/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.model.db.api.spec.IBook;

/**
 * knows which features are supported and (re)creates a specific {@link BookContent}
 * 
 * @author eav 2009
 * @param <PageType> type of displayed pages
 */
public abstract class ReaderSpecific<PageType>
{
    // todo enum?  
    public static final String   BOOKMARKS         = "BOOKMARKS";
    public static final String   THUMBNAILS        = "THUMBNAILS";
    public static final String   NOTES             = "NOTES";
    public static final String   CHARSET           = "CHARSET";
    public static final String   FONT              = "FONT";
    public static final String   SEARCH            = "SEARCH";
    public static final String   LAYOUT            = "LAYOUT";
    public static final String   SCALING           = "SCALING";
    public static final String   PAGING            = "PAGING";
    public static final String   BROWSER           = "BROWSER";

    protected final List<String> supportedFeatures = new ArrayList<String>();

    public abstract BookContent<PageType> createBookContent(
        IBook book );

    public List<String> getSupportedFeatures()
    {
        return this.supportedFeatures;
    }
}
