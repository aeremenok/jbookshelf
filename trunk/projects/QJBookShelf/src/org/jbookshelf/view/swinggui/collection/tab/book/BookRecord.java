/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab.book;

import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.widget.tables.Record;

public class BookRecord
    implements
    Record
{
    private Object id;
    private Object bookName;
    private String authorName;
    private String categoryName;

    public BookRecord()
    {
        super();
    }

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

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widget.tables.Record#getColumnNames()
     */
    @Override
    public String[] getColumnNames()
    {
        return new String[]
        { I18N.tr( "Name" ), I18N.tr( "Author" ), I18N.tr( "Category" ) };
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