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
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.model;

import java.io.File;

/**
 * unit contained as a single file in an archive
 * 
 * @author eav
 * @model
 */
public interface ArchiveFile
    extends
        PhysicalUnit
{
    /**
     * @return archive file
     * @model
     */
    File getArchiveFile();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.ArchiveFile#getArchiveFile <em>Archive File</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Archive File</em>' attribute.
     * @see #getArchiveFile()
     * @generated
     */
    void setArchiveFile(
        File value );
}
