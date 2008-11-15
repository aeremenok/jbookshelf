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
        bookShelf = impl.loadCollection();
        return bookShelf;
    }

    public static void saveCollection()
    {
        impl.saveCollection( getBookShelf() );
    }

    public static void backupCollection(
        File file )
    {
        impl.backupCollection( getBookShelf(), file );
    }

    public static BookShelf restoreCollection(
        File file )
    {
        bookShelf = impl.restoreCollection( file );
        return bookShelf;
    }
}
