/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab.book;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.widget.tables.ExpandTableModel;
import org.jbookshelf.view.swinggui.widget.tables.Record;

/**
 * a row of {@link ExpandTableModel}, representing a {@link Book}
 * 
 * @author eav 2009
 */
public class BookRecord
    implements
    Record
{
    private final Object id;
    private final Object bookName;
    private final String authorName;
    private final String categoryName;

    public BookRecord(
        final Object id,
        final Object bookName,
        final String authorName,
        final String categoryName )
    {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.categoryName = categoryName;
    }

    /**
     * @return the authorName
     */
    public String getAuthorName()
    {
        return this.authorName;
    }

    /**
     * @return the bookName
     */
    public Object getBookName()
    {
        return this.bookName;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName()
    {
        return this.categoryName;
    }

    /**
     * @return the id
     */
    public Object getId()
    {
        return this.id;
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widget.tables.Record#getValueAt(int)
     */
    @Override
    public Object getValueAt(
        final int column )
    {
        switch ( column )
        {
            case 0:
                return bookName;
            case 1:
                return authorName;
            case 2:
                return categoryName;
        }
        return id;
    }
}