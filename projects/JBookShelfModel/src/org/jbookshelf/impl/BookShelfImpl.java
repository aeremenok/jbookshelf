/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.jbookshelf.Author;
import org.jbookshelf.BookShelf;
import org.jbookshelf.BookShelfStorage;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book Shelf</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getUniques <em>Uniques</em>}</li>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getReadingUnits <em>Reading Units</em>}</li>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getStorage <em>Storage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookShelfImpl extends EObjectImpl implements BookShelf
{
    /**
     * The cached value of the '{@link #getUniques() <em>Uniques</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUniques()
     * @generated
     * @ordered
     */
    protected EList<Unique> uniques;

    /**
     * The cached value of the '{@link #getAuthors() <em>Authors</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAuthors()
     * @generated
     * @ordered
     */
    protected EList<Author> authors;
    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category> categories;
    /**
     * The cached value of the '{@link #getReadingUnits() <em>Reading Units</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReadingUnits()
     * @generated
     * @ordered
     */
    protected EList<ReadingUnit> readingUnits;

    /**
     * The cached value of the '{@link #getStorage() <em>Storage</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStorage()
     * @generated
     * @ordered
     */
    protected BookShelfStorage storage;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BookShelfImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return JbookshelfPackage.Literals.BOOK_SHELF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Unique> getUniques()
    {
        if (uniques == null)
        {
            uniques = new EObjectResolvingEList<Unique>(Unique.class, this, JbookshelfPackage.BOOK_SHELF__UNIQUES);
        }
        return uniques;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Author> getAuthors()
    {
        if (authors == null)
        {
            authors = new EObjectResolvingEList<Author>(Author.class, this, JbookshelfPackage.BOOK_SHELF__AUTHORS);
        }
        return authors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Category> getCategories()
    {
        if (categories == null)
        {
            categories = new EObjectResolvingEList<Category>(Category.class, this, JbookshelfPackage.BOOK_SHELF__CATEGORIES);
        }
        return categories;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ReadingUnit> getReadingUnits()
    {
        if (readingUnits == null)
        {
            readingUnits = new EObjectResolvingEList<ReadingUnit>(ReadingUnit.class, this, JbookshelfPackage.BOOK_SHELF__READING_UNITS);
        }
        return readingUnits;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BookShelfStorage getStorage()
    {
        if (storage != null && storage.eIsProxy())
        {
            InternalEObject oldStorage = (InternalEObject)storage;
            storage = (BookShelfStorage)eResolveProxy(oldStorage);
            if (storage != oldStorage)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, JbookshelfPackage.BOOK_SHELF__STORAGE, oldStorage, storage));
            }
        }
        return storage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BookShelfStorage basicGetStorage()
    {
        return storage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStorage(BookShelfStorage newStorage)
    {
        BookShelfStorage oldStorage = storage;
        storage = newStorage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.BOOK_SHELF__STORAGE, oldStorage, storage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF__UNIQUES:
                return getUniques();
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                return getAuthors();
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                return getCategories();
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                return getReadingUnits();
            case JbookshelfPackage.BOOK_SHELF__STORAGE:
                if (resolve) return getStorage();
                return basicGetStorage();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF__UNIQUES:
                getUniques().clear();
                getUniques().addAll((Collection<? extends Unique>)newValue);
                return;
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                getAuthors().clear();
                getAuthors().addAll((Collection<? extends Author>)newValue);
                return;
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends Category>)newValue);
                return;
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                getReadingUnits().clear();
                getReadingUnits().addAll((Collection<? extends ReadingUnit>)newValue);
                return;
            case JbookshelfPackage.BOOK_SHELF__STORAGE:
                setStorage((BookShelfStorage)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF__UNIQUES:
                getUniques().clear();
                return;
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                getAuthors().clear();
                return;
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                getCategories().clear();
                return;
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                getReadingUnits().clear();
                return;
            case JbookshelfPackage.BOOK_SHELF__STORAGE:
                setStorage((BookShelfStorage)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF__UNIQUES:
                return uniques != null && !uniques.isEmpty();
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                return authors != null && !authors.isEmpty();
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                return readingUnits != null && !readingUnits.isEmpty();
            case JbookshelfPackage.BOOK_SHELF__STORAGE:
                return storage != null;
        }
        return super.eIsSet(featureID);
    }

} //BookShelfImpl
