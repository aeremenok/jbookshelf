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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.Unique;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * @see org.jbookshelf.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory
    extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static ModelPackage  modelPackage;

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ModelSwitch<Adapter> modelSwitch = new ModelSwitch<Adapter>()
        {
            @Override
            public Adapter caseArchiveFile(ArchiveFile object)
            {
                return createArchiveFileAdapter();
            }
            @Override
            public Adapter caseAuthor(Author object)
            {
                return createAuthorAdapter();
            }
            @Override
            public Adapter caseBookShelf(BookShelf object)
            {
                return createBookShelfAdapter();
            }
            @Override
            public Adapter caseCategorizable(Categorizable object)
            {
                return createCategorizableAdapter();
            }
            @Override
            public Adapter caseCategory(Category object)
            {
                return createCategoryAdapter();
            }
            @Override
            public Adapter caseComment(Comment object)
            {
                return createCommentAdapter();
            }
            @Override
            public Adapter caseCommentable(Commentable object)
            {
                return createCommentableAdapter();
            }
            @Override
            public Adapter casePhysicalUnit(PhysicalUnit object)
            {
                return createPhysicalUnitAdapter();
            }
            @Override
            public Adapter caseBook(Book object)
            {
                return createBookAdapter();
            }
            @Override
            public Adapter caseUnique(Unique object)
            {
                return createUniqueAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object)
            {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ModelAdapterFactory()
    {
        if (modelPackage == null)
        {
            modelPackage = ModelPackage.eINSTANCE;
        }
    }

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(
        Notifier target )
    {
        return modelSwitch.doSwitch((EObject)target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.ArchiveFile <em>Archive File</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.ArchiveFile
     * @generated
     */
    public Adapter createArchiveFileAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Author <em>Author</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.jbookshelf.model.Author
     * @generated
     */
    public Adapter createAuthorAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Book <em>Book</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.jbookshelf.model.Book
     * @generated
     */
    public Adapter createBookAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.BookShelf <em>Book Shelf</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.jbookshelf.model.BookShelf
     * @generated
     */
    public Adapter createBookShelfAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Categorizable <em>Categorizable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Categorizable
     * @generated
     */
    public Adapter createCategorizableAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Category <em>Category</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.jbookshelf.model.Category
     * @generated
     */
    public Adapter createCategoryAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Commentable <em>Commentable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Commentable
     * @generated
     */
    public Adapter createCommentableAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Comment <em>Comment</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.jbookshelf.model.Comment
     * @generated
     */
    public Adapter createCommentAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.PhysicalUnit <em>Physical Unit</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.PhysicalUnit
     * @generated
     */
    public Adapter createPhysicalUnitAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Unique <em>Unique</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.jbookshelf.model.Unique
     * @generated
     */
    public Adapter createUniqueAdapter()
    {
        return null;
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(
        Object object )
    {
        if (object == modelPackage)
        {
            return true;
        }
        if (object instanceof EObject)
        {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

} // ModelAdapterFactory
