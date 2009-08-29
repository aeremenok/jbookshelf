package org.jbookshelf.view.swinggui.reader.types.rtf;

import javax.swing.text.StyledDocument;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;

public class RTFReaderFactory
    extends ReaderFactory<StyledDocument>
{
    public RTFReaderFactory()
    {
        super();
        features.add( Features.SCALING );
        features.add( Features.SEARCH );
        features.add( Features.CHARSET );
    }

    @Override
    public BookContent<StyledDocument> createBookContent(
        final Book book )
    {
        return new RTFContent( book );
    }
}
