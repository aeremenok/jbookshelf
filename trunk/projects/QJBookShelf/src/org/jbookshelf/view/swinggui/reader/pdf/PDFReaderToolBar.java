/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.pdf;

import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

/**
 * @author eav 2009
 */
public class PDFReaderToolBar
    extends ReaderToolBar
{
    /**
     * @param readerWindow
     */
    public PDFReaderToolBar(
        final ReaderWindow readerWindow )
    {
        super( readerWindow );
    }

    @Override
    protected void init()
    {
        super.init();
        // todo implement pdf scaling
        scalator.zoomInAction.setEnabled( false );
        scalator.zoomOutAction.setEnabled( false );
        scalator.scaleComboBox.setEnabled( false );
    }
}
