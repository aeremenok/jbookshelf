/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.jbookshelf.BookShelf;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.Unique;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book Shelf</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getUniques <em>Uniques</em>}</li>
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
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF__UNIQUES:
                return getUniques();
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
        }
        return super.eIsSet(featureID);
    }

} //BookShelfImpl
