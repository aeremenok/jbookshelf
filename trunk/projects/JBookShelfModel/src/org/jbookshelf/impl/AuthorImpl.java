/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbookshelf.Author;
import org.jbookshelf.Categorizable;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.ReadingUnit;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Author</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.AuthorImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.jbookshelf.impl.AuthorImpl#getReadingUnits <em>Reading Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AuthorImpl
    extends UniqueImpl
    implements
        Author
{
    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category>    categories;

    /**
     * The cached value of the '{@link #getReadingUnits() <em>Reading Units</em>}' reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getReadingUnits()
     * @generated
     * @ordered
     */
    protected EList<ReadingUnit> readingUnits;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AuthorImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(
        int derivedFeatureID,
        Class<?> baseClass )
    {
        if (baseClass == Categorizable.class)
        {
            switch (derivedFeatureID)
            {
                case JbookshelfPackage.AUTHOR__CATEGORIES: return JbookshelfPackage.CATEGORIZABLE__CATEGORIES;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(
        int baseFeatureID,
        Class<?> baseClass )
    {
        if (baseClass == Categorizable.class)
        {
            switch (baseFeatureID)
            {
                case JbookshelfPackage.CATEGORIZABLE__CATEGORIES: return JbookshelfPackage.AUTHOR__CATEGORIES;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(
        int featureID,
        boolean resolve,
        boolean coreType )
    {
        switch (featureID)
        {
            case JbookshelfPackage.AUTHOR__CATEGORIES:
                return getCategories();
            case JbookshelfPackage.AUTHOR__READING_UNITS:
                return getReadingUnits();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public NotificationChain eInverseAdd(
        InternalEObject otherEnd,
        int featureID,
        NotificationChain msgs )
    {
        switch (featureID)
        {
            case JbookshelfPackage.AUTHOR__CATEGORIES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getCategories()).basicAdd(otherEnd, msgs);
            case JbookshelfPackage.AUTHOR__READING_UNITS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getReadingUnits()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(
        InternalEObject otherEnd,
        int featureID,
        NotificationChain msgs )
    {
        switch (featureID)
        {
            case JbookshelfPackage.AUTHOR__CATEGORIES:
                return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
            case JbookshelfPackage.AUTHOR__READING_UNITS:
                return ((InternalEList<?>)getReadingUnits()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(
        int featureID )
    {
        switch (featureID)
        {
            case JbookshelfPackage.AUTHOR__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case JbookshelfPackage.AUTHOR__READING_UNITS:
                return readingUnits != null && !readingUnits.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public void eSet(
        int featureID,
        Object newValue )
    {
        switch (featureID)
        {
            case JbookshelfPackage.AUTHOR__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends Category>)newValue);
                return;
            case JbookshelfPackage.AUTHOR__READING_UNITS:
                getReadingUnits().clear();
                getReadingUnits().addAll((Collection<? extends ReadingUnit>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(
        int featureID )
    {
        switch (featureID)
        {
            case JbookshelfPackage.AUTHOR__CATEGORIES:
                getCategories().clear();
                return;
            case JbookshelfPackage.AUTHOR__READING_UNITS:
                getReadingUnits().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Category> getCategories()
    {
        if (categories == null)
        {
            categories = new EObjectWithInverseResolvingEList.ManyInverse<Category>(Category.class, this, JbookshelfPackage.AUTHOR__CATEGORIES, JbookshelfPackage.CATEGORY__CATEGORIZABLES);
        }
        return categories;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<ReadingUnit> getReadingUnits()
    {
        if (readingUnits == null)
        {
            readingUnits = new EObjectWithInverseResolvingEList.ManyInverse<ReadingUnit>(ReadingUnit.class, this, JbookshelfPackage.AUTHOR__READING_UNITS, JbookshelfPackage.READING_UNIT__AUTHORS);
        }
        return readingUnits;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return JbookshelfPackage.Literals.AUTHOR;
    }

} // AuthorImpl
