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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * something that could be read
 * 
 * @author eav
 * @model
 */
public interface ReadingUnit
    extends
        Categorizable,
        EObject
{
    /**
     * @return unit's authors
     * @model
     */
    EList<Author> getAuthors();

    /**
     * @return physical implementation of unit
     * @model containment="true" lowerBound="1"
     */
    PhysicalUnit getPhysical();

    /**
     * Returns the value of the '<em><b>Is Read</b></em>' attribute. The default value is <code>"false"</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Read</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Is Read</em>' attribute.
     * @see #setIsRead(boolean)
     * @see org.jbookshelf.JbookshelfPackage#getReadingUnit_IsRead()
     * @model default="false" required="true"
     */
    boolean isRead();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.ReadingUnit#getPhysical <em>Physical</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Physical</em>' reference.
     * @see #getPhysical()
     * @generated
     */
    void setPhysical(
        PhysicalUnit value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.ReadingUnit#isRead <em>Read</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Read</em>' attribute.
     * @see #isRead()
     * @generated
     */
    void setRead(
        boolean value );
}
