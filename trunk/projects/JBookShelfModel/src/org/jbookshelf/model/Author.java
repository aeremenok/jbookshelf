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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * person who created at least one reading unit
 * 
 * @author eav
 * @model
 */
public interface Author
    extends
        Categorizable,
        EObject
{
    /**
     * Returns the value of the '<em><b>Books</b></em>' reference list.
     * The list contents are of type {@link org.jbookshelf.model.Book}.
     * It is bidirectional and its opposite is '{@link org.jbookshelf.model.Book#getAuthors <em>Authors</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Books</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Books</em>' reference list.
     * @see org.jbookshelf.model.ModelPackage#getAuthor_Books()
     * @see org.jbookshelf.model.Book#getAuthors
     * @model opposite="authors"
     * @generated
     */
    EList<Book> getBooks();
}
