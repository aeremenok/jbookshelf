/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */
package org.jbookshelf.controller.storage;

import java.io.File;

import org.jbookshelf.model.BookShelf;

public class SingleFileStorageImpl
    extends StorageImpl
{
    private File collectionStorageFile;

    public File getCollectionStorageFile()
    {
        return collectionStorageFile;
    }

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
