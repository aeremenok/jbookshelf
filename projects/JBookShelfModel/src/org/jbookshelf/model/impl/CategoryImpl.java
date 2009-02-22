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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbookshelf.model.Categorizable;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Category</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.model.impl.CategoryImpl#getCategorizables <em>Categorizables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CategoryImpl
    extends CommentableImpl
    implements
        Category
{
    /**
     * The cached value of the '{@link #getCategorizables() <em>Categorizables</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCategorizables()
     * @generated
     * @ordered
     */
    protected EList<Categorizable> categorizables;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected CategoryImpl()
    {
        super();
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                return getCategorizables();
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getCategorizables()).basicAdd(otherEnd, msgs);
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                return ((InternalEList<?>)getCategorizables()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                return categorizables != null && !categorizables.isEmpty();
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                getCategorizables().clear();
                getCategorizables().addAll((Collection<? extends Categorizable>)newValue);
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                getCategorizables().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Categorizable> getCategorizables()
    {
        if (categorizables == null)
        {
            categorizables = new EObjectWithInverseResolvingEList.ManyInverse<Categorizable>(Categorizable.class, this, ModelPackage.CATEGORY__CATEGORIZABLES, ModelPackage.CATEGORIZABLE__CATEGORIES);
        }
        return categorizables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return ModelPackage.Literals.CATEGORY;
    }

} // CategoryImpl
