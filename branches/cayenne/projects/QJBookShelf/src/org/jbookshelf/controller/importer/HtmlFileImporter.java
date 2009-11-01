/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;
import java.io.FileFilter;

import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

/**
 * imports html-files
 * 
 * @author eav
 */
public class HtmlFileImporter
    implements
    PhysicalBookImporter
{
    /**
     * accepts a directory if it holds "file" with "file_files/"
     * 
     * @author eav 2009
     */
    private static class HtmlFolderFileFilter
        implements
        FileFilter
    {
        File mainFolder;
        File mainFile;

        public boolean accept(
            final File pathname )
        {
            final String name = pathname.getName();
            final boolean b = pathname.isDirectory() && name.endsWith( "_files" );
            if ( b )
            {
                mainFolder = pathname;
            }
            else if ( pathname.isFile()
                && (name.endsWith( ".htm" ) || name.endsWith( ".html" ) || name.endsWith( ".shtml" )) )
            {
                mainFile = pathname;
            }
            return b;
        }
    }

    private File currentFile;

    @Override
    public boolean accept(
        final File pathname )
    {
        if ( !pathname.isDirectory() )
        {
            return false;
        }

        // try to find if folder holds "file" with "file_files/" todo too complex - simplify!
        final HtmlFolderFileFilter filter = new HtmlFolderFileFilter();
        pathname.listFiles( filter );
        if ( filter.mainFolder != null && filter.mainFile != null )
        {
            currentFile = filter.mainFile;
            return true;
        }

        currentFile = null;
        return false;
    }

    @Override
    public IPhysicalBook create(
        final File file )
    {
        final IPhysicalBook book = BookShelf.createPhysicalBook();
        assert currentFile != null;
        // file.html with file_files/
        book.setFile( currentFile );
        return book;
    }
}
