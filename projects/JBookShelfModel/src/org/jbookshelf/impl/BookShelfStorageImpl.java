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
        if (eContainerFeatureID != JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF) return null;
        return (BookShelf)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBookShelf(BookShelf newBookShelf, NotificationChain msgs)
    {
        msgs = eBasicSetContainer((InternalEObject)newBookShelf, JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBookShelf(BookShelf newBookShelf)
    {
        if (newBookShelf != eInternalContainer() || (eContainerFeatureID != JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF && newBookShelf != null))
        {
            if (EcoreUtil.isAncestor(this, newBookShelf))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newBookShelf != null)
                msgs = ((InternalEObject)newBookShelf).eInverseAdd(this, JbookshelfPackage.BOOK_SHELF__STORAGE, BookShelf.class, msgs);
            msgs = basicSetBookShelf(newBookShelf, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF, newBookShelf, newBookShelf));
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetBookShelf((BookShelf)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF:
                return basicSetBookShelf(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
    {
        switch (eContainerFeatureID)
        {
            case JbookshelfPackage.BOOK_SHELF_STORAGE__BOOK_SHELF:
                return eInternalContainer().eInverseRemove(this, JbookshelfPackage.BOOK_SHELF__STORAGE, BookShelf.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
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
                return getBookShelf();
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
                return getBookShelf() != null;
        }
        return super.eIsSet(featureID);
    }

} //BookShelfStorageImpl
