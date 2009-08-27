package org.jbookshelf.view.swinggui.reader.rtf;

import javax.swing.text.StyledDocument;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.ThumbnailPanel;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;

public class RTFReaderFactory
    implements
    ReaderFactory<StyledDocument>
{

    public BookContent<StyledDocument> createBookContent(
        final Book book )
    {
        return new RTFContent( book );
    }

    public ReaderContentPanel<StyledDocument> createReaderContentPanel(
        final ReaderWindow<StyledDocument> readerWindow )
    {
        return new RTFPanel( readerWindow );
    }

    public ReaderToolBar createReaderToolBar(
        final ReaderWindow<StyledDocument> readerWindow )
    {
        return new ReaderToolBar( readerWindow, Features.SCALING, Features.SEARCH, Features.CHARSET );
    }

    @Override
    public ThumbnailPanel<StyledDocument> createThumbnailPanel(
        final ReaderWindow<StyledDocument> readerWindow )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
