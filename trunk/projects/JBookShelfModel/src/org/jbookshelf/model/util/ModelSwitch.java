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
package org.jbookshelf.model.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.jbookshelf.model.*;
import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Categorizable;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.Comment;
import org.jbookshelf.model.Commentable;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;
import org.jbookshelf.model.Unique;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * @see org.jbookshelf.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T>
{
    /**
     * The cached model package
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static ModelPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ModelSwitch()
    {
        if (modelPackage == null)
        {
            modelPackage = ModelPackage.eINSTANCE;
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Archive File</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Archive File</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArchiveFile(
        ArchiveFile object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Author</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Author</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAuthor(
        Author object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Book</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Book</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBook(
        Book object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Book Shelf</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Book Shelf</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBookShelf(
        BookShelf object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Categorizable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Categorizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCategorizable(
        Categorizable object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Category</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCategory(
        Category object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComment(
        Comment object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Commentable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Commentable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCommentable(
        Commentable object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Index File Folder</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Index File Folder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIndexFileFolder(
        IndexFileFolder object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Physical Unit</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Physical Unit</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhysicalUnit(
        PhysicalUnit object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Single File</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Single File</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSingleFile(
        SingleFile object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Single File Folder</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Single File Folder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSingleFileFolder(
        SingleFileFolder object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Unique</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unique</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUnique(
        Unique object )
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(
        EObject object )
    {
        return null;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(
        EObject theEObject )
    {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(
        EClass theEClass,
        EObject theEObject )
    {
        if (theEClass.eContainer() == modelPackage)
        {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else
        {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(
        int classifierID,
        EObject theEObject )
    {
        switch (classifierID)
        {
            case ModelPackage.ARCHIVE_FILE:
            {
                ArchiveFile archiveFile = (ArchiveFile)theEObject;
                T result = caseArchiveFile(archiveFile);
                if (result == null) result = casePhysicalUnit(archiveFile);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.AUTHOR:
            {
                Author author = (Author)theEObject;
                T result = caseAuthor(author);
                if (result == null) result = caseCategorizable(author);
                if (result == null) result = caseCommentable(author);
                if (result == null) result = caseUnique(author);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.BOOK_SHELF:
            {
                BookShelf bookShelf = (BookShelf)theEObject;
                T result = caseBookShelf(bookShelf);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.CATEGORIZABLE:
            {
                Categorizable categorizable = (Categorizable)theEObject;
                T result = caseCategorizable(categorizable);
                if (result == null) result = caseCommentable(categorizable);
                if (result == null) result = caseUnique(categorizable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.CATEGORY:
            {
                Category category = (Category)theEObject;
                T result = caseCategory(category);
                if (result == null) result = caseCommentable(category);
                if (result == null) result = caseUnique(category);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.COMMENT:
            {
                Comment comment = (Comment)theEObject;
                T result = caseComment(comment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.COMMENTABLE:
            {
                Commentable commentable = (Commentable)theEObject;
                T result = caseCommentable(commentable);
                if (result == null) result = caseUnique(commentable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.INDEX_FILE_FOLDER:
            {
                IndexFileFolder indexFileFolder = (IndexFileFolder)theEObject;
                T result = caseIndexFileFolder(indexFileFolder);
                if (result == null) result = casePhysicalUnit(indexFileFolder);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.PHYSICAL_UNIT:
            {
                PhysicalUnit physicalUnit = (PhysicalUnit)theEObject;
                T result = casePhysicalUnit(physicalUnit);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.BOOK:
            {
                Book book = (Book)theEObject;
                T result = caseBook(book);
                if (result == null) result = caseCategorizable(book);
                if (result == null) result = caseCommentable(book);
                if (result == null) result = caseUnique(book);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.SINGLE_FILE:
            {
                SingleFile singleFile = (SingleFile)theEObject;
                T result = caseSingleFile(singleFile);
                if (result == null) result = casePhysicalUnit(singleFile);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.SINGLE_FILE_FOLDER:
            {
                SingleFileFolder singleFileFolder = (SingleFileFolder)theEObject;
                T result = caseSingleFileFolder(singleFileFolder);
                if (result == null) result = casePhysicalUnit(singleFileFolder);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.UNIQUE:
            {
                Unique unique = (Unique)theEObject;
                T result = caseUnique(unique);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

} // ModelSwitch
