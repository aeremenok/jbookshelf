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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.jbookshelf.model.ModelPackage
 * @generated
 */
public interface ModelFactory
    extends
        EFactory
{
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    ModelFactory eINSTANCE = org.jbookshelf.model.impl.ModelFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Archive File</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Archive File</em>'.
     * @generated
     */
    ArchiveFile createArchiveFile();

    /**
     * Returns a new object of class '<em>Author</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Author</em>'.
     * @generated
     */
    Author createAuthor();

    /**
     * Returns a new object of class '<em>Book Shelf</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Book Shelf</em>'.
     * @generated
     */
    BookShelf createBookShelf();

    /**
     * Returns a new object of class '<em>Category</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Category</em>'.
     * @generated
     */
    Category createCategory();

    /**
     * Returns a new object of class '<em>Comment</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Comment</em>'.
     * @generated
     */
    Comment createComment();

    /**
     * Returns a new object of class '<em>Index File Folder</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Index File Folder</em>'.
     * @generated
     */
    IndexFileFolder createIndexFileFolder();

    /**
     * Returns a new object of class '<em>Reading Unit</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Reading Unit</em>'.
     * @generated
     */
    ReadingUnit createReadingUnit();

    /**
     * Returns a new object of class '<em>Single File</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Single File</em>'.
     * @generated
     */
    SingleFile createSingleFile();

    /**
     * Returns a new object of class '<em>Single File Folder</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Single File Folder</em>'.
     * @generated
     */
    SingleFileFolder createSingleFileFolder();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    ModelPackage getModelPackage();

} // ModelFactory
