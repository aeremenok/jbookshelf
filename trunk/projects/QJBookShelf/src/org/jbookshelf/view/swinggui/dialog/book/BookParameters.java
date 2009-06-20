package org.jbookshelf.view.swinggui.dialog.book;

import java.io.File;
import java.util.Collection;

import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Category;

/**
 * Parameter set to excange between classes
 * 
 * @author eav
 */
public class BookParameters
{
    private final String               bookName;
    private final Collection<Author>   authors;
    private final Collection<Category> categories;
    private final File                 file;
    private final boolean              isRead;
    private final String               viewer;

    public BookParameters(
        final String bookName,
        final Collection<Author> authors,
        final Collection<Category> categories,
        final File file,
        final boolean isRead,
        final String viewer )
    {
        super();
        this.bookName = bookName;
        this.authors = authors;
        this.categories = categories;
        this.file = file;
        this.isRead = isRead;
        this.viewer = viewer;
    }

    /**
     * @return the authors
     */
    public Collection<Author> getAuthors()
    {
        return this.authors;
    }

    public String getBookName()
    {
        return bookName;
    }

    /**
     * @return the categories
     */
    public Collection<Category> getCategories()
    {
        return this.categories;
    }

    public File getFile()
    {
        return file;
    }

    public String getViewer()
    {
        return viewer;
    }

    public boolean isRead()
    {
        return isRead;
    }
}