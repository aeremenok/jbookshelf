package org.jbookshelf.controller.importer.strategy;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;

public interface FileImportStrategy
{
    Book importBook(
        PhysicalBook physicalBook );

    String longDescription();
}
