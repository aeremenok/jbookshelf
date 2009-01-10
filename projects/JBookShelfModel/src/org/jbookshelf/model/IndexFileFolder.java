/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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
package org.jbookshelf.model;

import java.io.File;

/**
 * unit contained as many html-files in a folder, containig index.html or index.htm...
 * 
 * @author eav
 * @model
 */
public interface IndexFileFolder
    extends
        PhysicalUnit
{
    /**
     * @return file to start browsing
     * @model
     */
    File getIndexFile();

    /**
     * @return containing folder
     * @model
     */
    File getIndexFolder();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.IndexFileFolder#getIndexFile <em>Index File</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Index File</em>' attribute.
     * @see #getIndexFile()
     * @generated
     */
    void setIndexFile(
        File value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.IndexFileFolder#getIndexFolder <em>Index Folder</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Index Folder</em>' attribute.
     * @see #getIndexFolder()
     * @generated
     */
    void setIndexFolder(
        File value );
}
