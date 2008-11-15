package org.util.storage;

import java.io.File;

import org.jbookshelf.BookShelf;

/**
 * storage facade, hides physical storage
 * 
 * @author eav
 */
public abstract class Storage
{
    /**
     * physical storage implementation
     */
    private final static StorageImpl impl = new SingleFileStorageImpl();

    private static BookShelf         bookShelf;

    public static BookShelf getBookShelf()
    {
        return bookShelf;
    }

    public static StorageImpl getImpl()
    {
        return impl;
    }

    public static BookShelf loadCollection()
    {
        return impl.loadCollection();
    }

    public static void saveCollection()
    {
        impl.saveCollection( getBookShelf() );
    }

    public void backupCollection(
        File file )
    {
        impl.backupCollection( getBookShelf(), file );
    }

    public BookShelf restoreCollection(
        File file )
    {
        return impl.restoreCollection( file );
    }
}
