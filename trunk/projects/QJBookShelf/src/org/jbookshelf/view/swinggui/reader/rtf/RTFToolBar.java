package org.jbookshelf.view.swinggui.reader.rtf;

import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.txt.PlainTextToolBar;

public class RTFToolBar
    extends PlainTextToolBar
{
    public RTFToolBar(
        final ReaderWindow readerWindow )
    {
        super( readerWindow );
        getScalator().layoutAction.setEnabled( false );
        getPaginator().setVisible( false );
    }
}
