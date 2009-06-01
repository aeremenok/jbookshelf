/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

import javax.annotation.Nonnull;

import org.jbookshelf.model.db.PhysicalBook;

/**
 * @author eav
 */
public class SingleFileImporter
    extends PhysicalBookImporter
{

    /* (non-Javadoc)
     * @see java.io.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(
        final File pathname )
    {
        return !pathname.isDirectory();
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
        return book;
    }

}
