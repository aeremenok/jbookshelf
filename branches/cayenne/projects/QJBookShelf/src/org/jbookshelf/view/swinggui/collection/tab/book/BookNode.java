/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab.book;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.collection.tab.DefaultLazyNode;

/**
 * tree node representing a {@link Book}
 * 
 * @author eav 2009
 */
public class BookNode
    extends DefaultLazyNode
{
    private final Book book;

    public BookNode()
    {
        super();
        book = null;
    }

    public BookNode(
        final Book book )
    {
        super( book.getName() );
        this.book = book;
    }

    /**
     * @return the book
     */
    public Book getBook()
    {
        return this.book;
    }

    @Override
    public boolean isLeaf()
    {
        return true;
    }
}
