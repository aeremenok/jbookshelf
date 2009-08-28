package org.jbookshelf.view.swinggui.reader.types.rtf;

import javax.swing.text.StyledDocument;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

public class RTFReaderFactory
    implements
    ReaderFactory<StyledDocument>
{
    @Override
    public BookContent<StyledDocument> createBookContent(
        final Book book )
    {
        return new RTFContent( book );
    }
}
