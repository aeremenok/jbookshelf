/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;
import java.io.FileFilter;

import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

import com.sun.istack.internal.Nullable;

/**
 * imports directories with index.html
 * 
 * @author eav
 */
public class IndexFileImporter
    implements
    PhysicalBookImporter
{
    @Nullable
    private File currentIndexFile;

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

    @Override
    public IPhysicalBook create(
        final File file )
    {
        final IPhysicalBook book = BookShelf.createPhysicalBook();
        assert currentIndexFile != null;
        book.setFile( currentIndexFile );
        return book;
    }

}
