/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab;

import javax.annotation.Nonnull;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 */
public class BookNode
    extends DefaultMutableTreeNode
{
    private final Book book;

    public BookNode()
    {
        super();
        book = null;
    }

    public BookNode(
        @Nonnull final Book book )
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
