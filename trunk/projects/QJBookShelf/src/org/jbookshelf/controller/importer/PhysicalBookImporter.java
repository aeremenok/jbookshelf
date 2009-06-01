/**
 * 
 */
package org.jbookshelf.controller.importer;

import java.io.File;
import java.io.FileFilter;

import javax.annotation.Nonnull;

import org.jbookshelf.model.db.PhysicalBook;

/**
 * @author eav
 */
public abstract class PhysicalBookImporter
    implements
    FileFilter
{
    @Nonnull
    public abstract PhysicalBook create(
        @Nonnull File file );
}
