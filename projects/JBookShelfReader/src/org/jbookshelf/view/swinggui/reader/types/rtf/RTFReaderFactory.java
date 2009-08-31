package org.jbookshelf.view.swinggui.reader.types.rtf;

import javax.swing.text.StyledDocument;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

public class RTFReaderFactory
    extends ReaderFactory<StyledDocument>
{
    public RTFReaderFactory()
    {
        super();
        features.add( ReaderFactory.SCALING );
        features.add( ReaderFactory.SEARCH );
        features.add( ReaderFactory.CHARSET );

        features.add( ReaderFactory.BOOKMARKS );
        features.add( ReaderFactory.NOTES );
    }

    @Override
    public BookContent<StyledDocument> createBookContent(
        final Book book )
    {
        return new RTFContent( book );
    }
}
