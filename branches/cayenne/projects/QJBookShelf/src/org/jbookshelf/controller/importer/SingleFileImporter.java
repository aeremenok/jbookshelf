/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

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
    @Override
    public boolean accept(
        final File pathname )
    {
        return !pathname.isDirectory();
    }

    @Override
    public PhysicalBook create(
        final File file )
    {
        final PhysicalBook book = new PhysicalBook();
        book.setFile( file );
        return book;
    }
}
