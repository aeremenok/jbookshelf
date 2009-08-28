/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.rtf;

import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 */
public class RTFToolBar
    extends ReaderToolBar
{
    public RTFToolBar()
    {
        super( Features.SCALING, Features.SEARCH, Features.CHARSET );
    }
}
