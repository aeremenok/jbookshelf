/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

import javax.annotation.Nullable;

import junit.framework.Assert;

import org.jbookshelf.model.db.PhysicalBook;

/**
 * @author eav
 */
public class SingleFileDirImporter
    extends PhysicalBookImporter
{
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
        Assert.assertNotNull( currentFile );
        book.setFile( currentFile );
        return book;
    }

}
