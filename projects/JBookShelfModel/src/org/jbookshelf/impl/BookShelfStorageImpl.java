/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jbookshelf.BookShelf;
import org.jbookshelf.BookShelfStorage;
import org.jbookshelf.JbookshelfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book Shelf Storage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.BookShelfStorageImpl#getBookShelf <em>Book Shelf</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BookShelfStorageImpl extends EObjectImpl implements BookShelfStorage
{
    /**
     * The cached value of the '{@link #getBookShelf() <em>Book Shelf</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBookShelf()
     * @generated
     * @ordered
     */
    protected BookShelf bookShelf;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BookShelfStorageImpl()
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
        return JbookshelfPackage.Literals.BOOK_SHELF_STORAGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BookShelf getBookShelf()
    {
        if (bookShelf != null && bookShelf.eIsProxy())
        {
            InternalEObject oldBookShelf = (InternalEObject)bookShelf;
            bookShelf = (BookShelf)eResolveProxy(oldBookShelf);
            if (bookShelf != oldBookShelf)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF, oldBookShelf, bookShelf));
            }
        }
        return bookShelf;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BookShelf basicGetBookShelf()
    {
        return bookShelf;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBookShelf(BookShelf newBookShelf)
    {
        BookShelf oldBookShelf = bookShelf;
        bookShelf = newBookShelf;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF, oldBookShelf, bookShelf));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void loadCollection()
    {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void saveCollection()
    {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
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
            case JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF:
                if (resolve) return getBookShelf();
                return basicGetBookShelf();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF:
                setBookShelf((BookShelf)newValue);
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
            case JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF:
                setBookShelf((BookShelf)null);
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
            case JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF:
                return bookShelf != null;
        }
        return super.eIsSet(featureID);
    }

} //BookShelfStorageImpl
