package org.jbookshelf.controller.importer.strategy;

import javax.annotation.Nullable;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;

public interface FileImportStrategy
{
    @Nullable
    Book importBook(
        PhysicalBook physicalBook );

    String longDescription();
}
