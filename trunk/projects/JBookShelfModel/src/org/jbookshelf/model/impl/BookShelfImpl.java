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
package org.jbookshelf.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.Commentable;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.Unique;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Book Shelf</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.jbookshelf.model.impl.BookShelfImpl#getAuthors <em>Authors</em>}</li>
 * <li>{@link org.jbookshelf.model.impl.BookShelfImpl#getCategories <em>Categories</em>}</li>
 * <li>{@link org.jbookshelf.model.impl.BookShelfImpl#getReadingUnits <em>Reading Units</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BookShelfImpl
    extends EObjectImpl
    implements
        BookShelf
{
    /**
     * The cached value of the '{@link #getAuthors() <em>Authors</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getAuthors()
     * @generated
     * @ordered
     */
    protected EList<Author>   authors;
    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category> categories;
    /**
     * The cached value of the '{@link #getReadingUnits() <em>Reading Units</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReadingUnits()
     * @generated
     * @ordered
     */
    protected EList<Book>     readingUnits;

    private Author            unknown;

    private Category          common;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BookShelfImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public Author addAuthor(
        final String name )
    {
        if ( name == null || "".equals( name ) )
        {
            return getUnknown();
        }

        Author author = getAuthorByName( name );
        if ( author == null )
        {
            author = ModelFactory.eINSTANCE.createAuthor();
            author.setName( name );
            getAuthors().add( author );
        }
        return author;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Book addBook(
        final String name,
        final Author author,
        final Category category,
        final PhysicalUnit physicalUnit )
    {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public Category addCategory(
        final String name )
    {
        if ( name == null || "".equals( name ) )
        {
            return getCommon();
        }

        Category category = getCategoryByName( name );
        if ( category == null )
        {
            category = ModelFactory.eINSTANCE.createCategory();
            category.setName( name );
            getCategories().add( category );
        }
        return category;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public Book addReadingUnit(
        final String name,
        final Author author,
        final Category category,
        final PhysicalUnit physicalUnit )
    {
        if ( name == null || "".equals( name ) )
        {
            return null;
        }

        Book readingUnit = getReadingUnitByName( name );
        if ( readingUnit == null )
        {
            readingUnit = ModelFactory.eINSTANCE.createBook();
            readingUnit.setName( name );
            readingUnit.setPhysical( physicalUnit );
            // todo make relations optional
            if ( author != null )
            {
                readingUnit.getAuthors().add( author );
            }
            if ( category != null )
            {
                readingUnit.getCategories().add( category );
            }
            getReadingUnits().add( readingUnit );
        }
        return readingUnit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(
        final int featureID,
        final boolean resolve,
        final boolean coreType )
    {
        switch ( featureID )
        {
            case ModelPackage.BOOK_SHELF__AUTHORS:
                return getAuthors();
            case ModelPackage.BOOK_SHELF__CATEGORIES:
                return getCategories();
            case ModelPackage.BOOK_SHELF__READING_UNITS:
                return getReadingUnits();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(
        final InternalEObject otherEnd,
        final int featureID,
        final NotificationChain msgs )
    {
        switch ( featureID )
        {
            case ModelPackage.BOOK_SHELF__AUTHORS:
                return ((InternalEList<?>) getAuthors()).basicRemove( otherEnd, msgs );
            case ModelPackage.BOOK_SHELF__CATEGORIES:
                return ((InternalEList<?>) getCategories()).basicRemove( otherEnd, msgs );
            case ModelPackage.BOOK_SHELF__READING_UNITS:
                return ((InternalEList<?>) getReadingUnits()).basicRemove( otherEnd, msgs );
        }
        return super.eInverseRemove( otherEnd, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(
        final int featureID )
    {
        switch ( featureID )
        {
            case ModelPackage.BOOK_SHELF__AUTHORS:
                return authors != null && !authors.isEmpty();
            case ModelPackage.BOOK_SHELF__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case ModelPackage.BOOK_SHELF__READING_UNITS:
                return readingUnits != null && !readingUnits.isEmpty();
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public void eSet(
        final int featureID,
        final Object newValue )
    {
        switch ( featureID )
        {
            case ModelPackage.BOOK_SHELF__AUTHORS:
                getAuthors().clear();
                getAuthors().addAll( (Collection<? extends Author>) newValue );
                return;
            case ModelPackage.BOOK_SHELF__CATEGORIES:
                getCategories().clear();
                getCategories().addAll( (Collection<? extends Category>) newValue );
                return;
            case ModelPackage.BOOK_SHELF__READING_UNITS:
                getReadingUnits().clear();
                getReadingUnits().addAll( (Collection<? extends Book>) newValue );
                return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(
        final int featureID )
    {
        switch ( featureID )
        {
            case ModelPackage.BOOK_SHELF__AUTHORS:
                getAuthors().clear();
                return;
            case ModelPackage.BOOK_SHELF__CATEGORIES:
                getCategories().clear();
                return;
            case ModelPackage.BOOK_SHELF__READING_UNITS:
                getReadingUnits().clear();
                return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Author> getAuthors()
    {
        if ( authors == null )
        {
            authors = new EObjectContainmentEList<Author>( Author.class, this, ModelPackage.BOOK_SHELF__AUTHORS );
        }
        return authors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Category> getCategories()
    {
        if ( categories == null )
        {
            categories =
                new EObjectContainmentEList<Category>( Category.class, this, ModelPackage.BOOK_SHELF__CATEGORIES );
        }
        return categories;
    }

    public Category getCommon()
    {
        if ( common == null )
        {
            common = ModelFactory.eINSTANCE.createCategory();
            common.setName( "Common" );
            getCategories().add( common );
        }
        return common;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Book> getReadingUnits()
    {
        if ( readingUnits == null )
        {
            readingUnits = new EObjectContainmentEList<Book>( Book.class, this, ModelPackage.BOOK_SHELF__READING_UNITS );
        }
        return readingUnits;
    }

    public Author getUnknown()
    {
        if ( unknown == null )
        {
            unknown = ModelFactory.eINSTANCE.createAuthor();
            unknown.setName( "Unknown" );
            getAuthors().add( unknown );
        }
        return unknown;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Author> queryAuthors(
        final String query )
    {
        final String lowerCase = query.toLowerCase();
        final EList<Author> result = new BasicEList<Author>();

        for ( final Author author : getAuthors() )
        {
            if ( author.getName().toLowerCase().contains( lowerCase ) )
            {
                result.add( author );
            }
        }

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Book> queryBooks(
        final String query,
        final Boolean isRead )
    {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Category> queryCategories(
        final String query )
    {
        final String lowerCase = query.toLowerCase();
        final EList<Category> result = new BasicEList<Category>();

        for ( final Category category : getCategories() )
        {
            if ( category.getName().toLowerCase().contains( lowerCase ) )
            {
                result.add( category );
            }
        }

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Unique> queryUniques(
        final String query )
    {
        final EList<Unique> result = new BasicEList<Unique>();

        result.addAll( queryAuthors( query ) );
        result.addAll( queryCategories( query ) );
        result.addAll( queryUnits( query, null ) );

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Book> queryUnits(
        final String query,
        final Boolean isRead )
    {
        final String lowerCase = query.toLowerCase();
        final EList<Book> result = new BasicEList<Book>();

        if ( isRead == null )
        {
            for ( final Book unit : getReadingUnits() )
            {
                if ( unit.getName().toLowerCase().contains( lowerCase ) )
                {
                    result.add( unit );
                }
            }
        }
        else
        {
            for ( final Book unit : getReadingUnits() )
            {
                final boolean read = !isRead.booleanValue() || isRead.booleanValue() && unit.getRead() == 1;
                if ( read && unit.getName().toLowerCase().contains( lowerCase ) )
                {
                    result.add( unit );
                }
            }
        }

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public void removeUnique(
        final Unique unique )
    {
        unique.getRelated().clear();
        ((Commentable) unique).getComments().clear();
        if ( unique instanceof Book )
        {
            getReadingUnits().remove( unique );
            final Book readingUnit = (Book) unique;
            readingUnit.getAuthors().clear();
            readingUnit.getCategories().clear();
        }
        else if ( unique instanceof Author )
        {
            getAuthors().remove( unique );
            final Author author = (Author) unique;
            author.getBooks().clear();
            author.getCategories().clear();
        }
        else if ( unique instanceof Category )
        {
            getCategories().remove( unique );
            final Category category = (Category) unique;
            category.getCategorizables().clear();
        }
    }

    private Author getAuthorByName(
        final String name )
    {
        // todo optimize
        for ( final Author author : getAuthors() )
        {
            if ( name.equals( author.getName() ) )
            {
                return author;
            }
        }
        return null;
    }

    private Category getCategoryByName(
        final String name )
    {
        // todo optimize
        for ( final Category category : getCategories() )
        {
            if ( name.equals( category.getName() ) )
            {
                return category;
            }
        }
        return null;
    }

    private Book getReadingUnitByName(
        final String name )
    {
        // todo optimize
        for ( final Book readingUnit : getReadingUnits() )
        {
            if ( name.equals( readingUnit.getName() ) )
            {
                return readingUnit;
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return ModelPackage.Literals.BOOK_SHELF;
    }

} // BookShelfImpl
