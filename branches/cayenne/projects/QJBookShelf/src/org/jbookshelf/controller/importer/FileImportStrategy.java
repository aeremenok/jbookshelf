package org.jbookshelf.controller.importer;

import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;

public interface FileImportStrategy
{
    IBook importBook(
        IPhysicalBook physicalBook );

    String longDescription();
}
