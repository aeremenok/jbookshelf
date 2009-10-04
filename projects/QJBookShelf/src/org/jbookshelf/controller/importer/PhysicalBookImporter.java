/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

import javax.annotation.Nonnull;

import org.jbookshelf.model.db.PhysicalBook;

/**
 * imports certain types of files
 * 
 * @author eav
 */
public interface PhysicalBookImporter
{
    /**
     * @param pathname a file to import
     * @return file can be imported
     */
    boolean accept(
        @Nonnull File pathname );

    /**
     * @param file a file to import
     * @return imported book implementation
     */
    @Nonnull
    PhysicalBook create(
        @Nonnull File file );
}
