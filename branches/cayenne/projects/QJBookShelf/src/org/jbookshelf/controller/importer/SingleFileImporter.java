/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

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
    public IPhysicalBook create(
        final File file )
    {
        final IPhysicalBook book = BookShelf.createPhysicalBook();
        book.setFile( file );
        return book;
    }
}
