/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 * @param <PageType>
 */
public interface ReaderFactory<PageType>
{
    BookContent<PageType> createBookContent(
        Book book );
}
