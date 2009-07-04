/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

import javax.annotation.Nonnull;

import org.jbookshelf.model.db.PhysicalBook;

/**
 * imports zip files
 * 
 * @author eav
 */
public class ZipFileImporter
    implements
    PhysicalBookImporter
{

    /* (non-Javadoc)
     * @see java.io.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(
        final File pathname )
    {
        // todo mime-type?
        return !pathname.isDirectory() && pathname.getName().toLowerCase().endsWith( ".zip" );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.controller.importer.PhysicalBookImporter#create(java.io.File)
     */
    @Override
    public PhysicalBook create(
        @Nonnull final File file )
    {
        final PhysicalBook book = new PhysicalBook();
        book.setFile( file );
        // todo add archive properties
        return book;
    }

}
