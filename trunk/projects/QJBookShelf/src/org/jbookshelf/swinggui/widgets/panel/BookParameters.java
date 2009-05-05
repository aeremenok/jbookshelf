package org.jbookshelf.swinggui.widgets.panel;

import java.io.File;

/**
 * Parameter set to excange between classes
 * 
 * @author eav
 */
public class BookParameters
{
    private final String   bookName;
    private final String[] authorNames;
    private final String[] categoryNames;
    private final File     file;
    private final boolean  isRead;
    private final String   viewer;

    public BookParameters(
        final String bookName,
        final String[] authorNames,
        final String[] categoryNames,
        final File file,
        final boolean isRead,
        final String viewer )
    {
        super();
        this.bookName = bookName;
        this.authorNames = authorNames;
        this.categoryNames = categoryNames;
        this.file = file;
        this.isRead = isRead;
        this.viewer = viewer;
    }

    public String[] getAuthorNames()
    {
        return authorNames;
    }

    public String getBookName()
    {
        return bookName;
    }

    public String[] getCategoryNames()
    {
        return categoryNames;
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