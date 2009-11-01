/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab.book;

import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.swinggui.collection.tab.DefaultLazyNode;

/**
 * tree node representing a {@link IBook}
 * 
 * @author eav 2009
 */
public class BookNode
    extends DefaultLazyNode
{
    private final IBook book;

    public BookNode()
    {
        super();
        book = null;
    }

    public BookNode(
        final IBook book )
    {
        super( book.getName() );
        this.book = book;
    }

    public IBook getBook()
    {
        return this.book;
    }

    @Override
    public boolean isLeaf()
    {
        return true;
    }
}
