/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 */
public class PlainTextToolBar
    extends ReaderToolBar
{
    public PlainTextToolBar()
    {
        super( Features.PAGING, Features.SCALING, Features.LAYOUT, Features.SEARCH, Features.FONT, Features.CHARSET,
            Features.BOOKMARKS, Features.NOTES );
    }
}
