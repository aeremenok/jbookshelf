/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.PhysicalUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.model.impl.BookImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.BookImpl#getPhysical <em>Physical</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.BookImpl#isRead <em>Read</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookImpl extends CategorizableImpl implements Book
{
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
     * The cached value of the '{@link #getPhysical() <em>Physical</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPhysical()
     * @generated
     * @ordered
     */
    protected PhysicalUnit physical;

    /**
     * The default value of the '{@link #isRead() <em>Read</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isRead()
     * @generated
     * @ordered
     */
    protected static final boolean READ_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isRead() <em>Read</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isRead()
     * @generated
     * @ordered
     */
    protected boolean read = READ_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BookImpl()
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
        return ModelPackage.Literals.BOOK;
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
            authors = new EObjectWithInverseResolvingEList.ManyInverse<Author>(Author.class, this, ModelPackage.BOOK__AUTHORS, ModelPackage.AUTHOR__BOOKS);
        }
        return authors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PhysicalUnit getPhysical()
    {
        return physical;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPhysical(PhysicalUnit newPhysical, NotificationChain msgs)
    {
        PhysicalUnit oldPhysical = physical;
        physical = newPhysical;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.BOOK__PHYSICAL, oldPhysical, newPhysical);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPhysical(PhysicalUnit newPhysical)
    {
        if (newPhysical != physical)
        {
            NotificationChain msgs = null;
            if (physical != null)
                msgs = ((InternalEObject)physical).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.BOOK__PHYSICAL, null, msgs);
            if (newPhysical != null)
                msgs = ((InternalEObject)newPhysical).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.BOOK__PHYSICAL, null, msgs);
            msgs = basicSetPhysical(newPhysical, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.BOOK__PHYSICAL, newPhysical, newPhysical));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isRead()
    {
        return read;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRead(boolean newRead)
    {
        boolean oldRead = read;
        read = newRead;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.BOOK__READ, oldRead, read));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case ModelPackage.BOOK__AUTHORS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getAuthors()).basicAdd(otherEnd, msgs);
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
            case ModelPackage.BOOK__AUTHORS:
                return ((InternalEList<?>)getAuthors()).basicRemove(otherEnd, msgs);
            case ModelPackage.BOOK__PHYSICAL:
                return basicSetPhysical(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
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
            case ModelPackage.BOOK__AUTHORS:
                return getAuthors();
            case ModelPackage.BOOK__PHYSICAL:
                return getPhysical();
            case ModelPackage.BOOK__READ:
                return isRead() ? Boolean.TRUE : Boolean.FALSE;
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
            case ModelPackage.BOOK__AUTHORS:
                getAuthors().clear();
                getAuthors().addAll((Collection<? extends Author>)newValue);
                return;
            case ModelPackage.BOOK__PHYSICAL:
                setPhysical((PhysicalUnit)newValue);
                return;
            case ModelPackage.BOOK__READ:
                setRead(((Boolean)newValue).booleanValue());
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
            case ModelPackage.BOOK__AUTHORS:
                getAuthors().clear();
                return;
            case ModelPackage.BOOK__PHYSICAL:
                setPhysical((PhysicalUnit)null);
                return;
            case ModelPackage.BOOK__READ:
                setRead(READ_EDEFAULT);
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
            case ModelPackage.BOOK__AUTHORS:
                return authors != null && !authors.isEmpty();
            case ModelPackage.BOOK__PHYSICAL:
                return physical != null;
            case ModelPackage.BOOK__READ:
                return read != READ_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (read: ");
        result.append(read);
        result.append(')');
        return result.toString();
    }

} //BookImpl
