package org.jbookshelf.swinggui.widgets.panel;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jbookshelf.model.Book;

public class BookPanel
    extends JPanel
{
    /**
     * Parameter set to excange between classes
     * 
     * @author eav
     */
    public class Parameters
    {
        private final String   bookName;
        private final String[] authorNames;
        private final String[] categoryNames;
        private final File     file;
        private final boolean  isRead;
        private final String   viewer;

        public Parameters(
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

    public static void changeBook(
        final Book book,
        final Parameters parameters )
    {
        // TODO Auto-generated method stub

    }

    public BookPanel()
    {
        super( new BorderLayout() );
        // todo
        add( new JLabel( "777" ), BorderLayout.CENTER );
    }

    public void clear(
        final boolean b )
    {
        // TODO Auto-generated method stub

    }

    public Parameters extractParameters()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
