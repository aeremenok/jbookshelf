/**
 * 
 */
package org.jbookshelf.controller.importer.impl;

import java.io.File;



import org.jbookshelf.controller.importer.PhysicalBookImporter;
import org.jbookshelf.model.db.PhysicalBook;

/**
 * imports directories with single non-zip files
 * 
 * @author eav
 */
public class SingleFileDirImporter
    implements
    PhysicalBookImporter
{
    
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

        final File[] listFiles = pathname.listFiles();

        if ( listFiles.length == 1 && listFiles[0].isFile() &&
        // zip file will be imported later
            !listFiles[0].getName().toLowerCase().endsWith( ".zip" ) )
        {
            currentFile = listFiles[0];
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
        assert currentFile != null;
        book.setFile( currentFile );
        return book;
    }

}
