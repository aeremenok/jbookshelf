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
public class IndexFileImporter
    extends PhysicalBookImporter
{
    @Nullable
    private File currentIndexFile;

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

        final File[] files = pathname.listFiles( new FileFilter()
        {
            public boolean accept(
                final File pathname )
            {
                final String name = pathname.getName().toLowerCase();
                return name.equals( "index.html" ) || name.equals( "index.htm" ) || name.equals( "index.shtml" );
            }
        } );
        // at least one index file
        if ( files.length > 1 )
        {
            currentIndexFile = files[0];
            return true;
        }
        currentIndexFile = null;
        return false;
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.controller.importer.PhysicalBookImporter#create(java.io.File)
     */
    @Override
    public PhysicalBook create(
        @Nonnull final File file )
    {
        final PhysicalBook book = new PhysicalBook();
        Assert.assertNotNull( currentIndexFile );
        book.setFile( currentIndexFile );
        return book;
    }

}
