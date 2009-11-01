/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;

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
        File pathname );

    /**
     * @param file a file to import
     * @return imported book implementation
     */
    PhysicalBook create(
        File file );
}
