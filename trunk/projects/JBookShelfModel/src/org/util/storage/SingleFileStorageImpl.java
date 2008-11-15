package org.util.storage;

import java.io.File;

import org.jbookshelf.BookShelf;

public class SingleFileStorageImpl
    extends StorageImpl
{
    private File collectionStorageFile;

    @Override
    public BookShelf loadCollection()
    {
        return restoreCollection( collectionStorageFile );
    }

    @Override
    public void saveCollection(
        BookShelf bookShelf )
    {
        backupCollection( bookShelf, collectionStorageFile );
    }

    public void setCollectionStorageFile(
        File collectionStorageFile )
    {
        this.collectionStorageFile = collectionStorageFile;
    }
}
