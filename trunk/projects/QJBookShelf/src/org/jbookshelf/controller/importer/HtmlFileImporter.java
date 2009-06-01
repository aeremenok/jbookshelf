/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;
import java.io.FileFilter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import junit.framework.Assert;

import org.jbookshelf.model.db.PhysicalBook;

/**
 * @author eav
 */
public class HtmlFileImporter
    extends PhysicalBookImporter
{
    private static class HtmlFolderFileFilter
        implements
        FileFilter
    {
        @Nonnull
        File mainFolder;
        @Nonnull
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

    @Nullable
    private File currentFile;

    /* (non-Javadoc)
     * @see java.io.FileFilter#accept(java.io.File)
     */
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

    /* (non-Javadoc)
     * @see org.jbookshelf.controller.importer.PhysicalBookImporter#create(java.io.File)
     */
    @Override
    public PhysicalBook create(
        final File file )
    {
        final PhysicalBook book = new PhysicalBook();
        Assert.assertNotNull( currentFile );
        // file.html with file_files/
        book.setFile( currentFile );
        return book;
    }
}
