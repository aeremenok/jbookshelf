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

import org.eclipse.emf.ecore.EObject;

/**
 * physical implementation of {@link ReadingUnit}
 * 
 * @author eav
 * @model abstract="true"
 */
public interface PhysicalUnit
    extends
        EObject
{
    String INTERNAL_VIEWER = "internal";
    String SYSTEM_VIEWER   = "system";

    /**
     * Returns the value of the '<em><b>Charset</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Charset</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Charset</em>' attribute.
     * @see #setCharset(String)
     * @see org.jbookshelf.model.ModelPackage#getPhysicalUnit_Charset()
     * @model
     * @generated
     */
    String getCharset();

    /**
     * Returns the value of the '<em><b>Directory</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Directory</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Directory</em>' attribute.
     * @see #setDirectory(File)
     * @see org.jbookshelf.model.ModelPackage#getPhysicalUnit_Directory()
     * @model dataType="org.jbookshelf.model.File"
     * @generated
     */
    File getDirectory();

    /**
     * Returns the value of the '<em><b>File</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>File</em>' attribute.
     * @see #setFile(File)
     * @see org.jbookshelf.model.ModelPackage#getPhysicalUnit_File()
     * @model dataType="org.jbookshelf.model.File"
     * @generated
     */
    File getFile();

    /**
     * Returns the value of the '<em><b>Viewer</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Viewer</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Viewer</em>' attribute.
     * @see #setViewer(String)
     * @see org.jbookshelf.model.ModelPackage#getPhysicalUnit_Viewer()
     * @model
     * @generated
     */
    String getViewer();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.PhysicalUnit#getCharset <em>Charset</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Charset</em>' attribute.
     * @see #getCharset()
     * @generated
     */
    void setCharset(
        String value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.PhysicalUnit#getDirectory <em>Directory</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Directory</em>' attribute.
     * @see #getDirectory()
     * @generated
     */
    void setDirectory(
        File value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.PhysicalUnit#getFile <em>File</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>File</em>' attribute.
     * @see #getFile()
     * @generated
     */
    void setFile(
        File value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.PhysicalUnit#getViewer <em>Viewer</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Viewer</em>' attribute.
     * @see #getViewer()
     * @generated
     */
    void setViewer(
        String value );
}
