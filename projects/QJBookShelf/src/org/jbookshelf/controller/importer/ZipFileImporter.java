/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

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
    @Override
    public boolean accept(
        final File pathname )
    {
        // todo mime-type?
        return !pathname.isDirectory() && pathname.getName().toLowerCase().endsWith( ".zip" );
    }

    @Override
    public PhysicalBook create(
        final File file )
    {
        final PhysicalBook book = new PhysicalBook();
        book.setFile( file );
        // todo add archive properties
        return book;
    }
}
