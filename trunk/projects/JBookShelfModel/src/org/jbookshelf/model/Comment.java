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
package org.jbookshelf.model;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * comment to some information
 * 
 * @author eav
 * @model
 */
public interface Comment
    extends
        EObject
{
    /**
     * @return comment text
     * @model
     */
    String getContent();

    /**
     * @return creation date
     * @model
     */
    Date getCreationDate();

    /**
     * @return what was commented
     * @model container="true"
     */
    Commentable getSubject();

    /**
     * Returns the value of the '<em><b>Title</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Title</em>' attribute.
     * @see #setTitle(String)
     * @see org.jbookshelf.model.ModelPackage#getComment_Title()
     * @model required="true"
     * @generated
     */
    String getTitle();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getContent <em>Content</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Content</em>' attribute.
     * @see #getContent()
     * @generated
     */
    void setContent(
        String value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getCreationDate <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Creation Date</em>' attribute.
     * @see #getCreationDate()
     * @generated
     */
    void setCreationDate(
        Date value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getSubject <em>Subject</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Subject</em>' reference.
     * @see #getSubject()
     * @generated
     */
    void setSubject(
        Commentable value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getTitle <em>Title</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Title</em>' attribute.
     * @see #getTitle()
     * @generated
     */
    void setTitle(
        String value );
}
