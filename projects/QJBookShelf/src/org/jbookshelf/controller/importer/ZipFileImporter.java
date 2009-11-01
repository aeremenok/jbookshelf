/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

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
    public IPhysicalBook create(
        final File file )
    {
        final IPhysicalBook book = BookShelf.createPhysicalBook();
        book.setFile( file );
        // todo add archive properties
        return book;
    }
}
