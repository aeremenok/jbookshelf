/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 */
public class PDFToolBar
    extends ReaderToolBar
{
    public PDFToolBar()
    {
        super( Features.PAGING, Features.LAYOUT, Features.SEARCH, Features.SCALING );
    }
}
