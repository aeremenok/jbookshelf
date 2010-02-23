/**
 * 
 */
package org.jbookshelf.controller.importer.impl;

import java.io.File;



import org.jbookshelf.controller.importer.PhysicalBookImporter;
import org.jbookshelf.model.db.PhysicalBook;

/**
 * imports single files
 * 
 * @author eav
 */
public class SingleFileImporter
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
        return !pathname.isDirectory();
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.controller.importer.PhysicalBookImporter#create(java.io.File)
     */
    @Override
    public PhysicalBook create(
         final File file )
    {
        final PhysicalBook book = new PhysicalBook();
        book.setFile( file );
        return book;
    }

}
