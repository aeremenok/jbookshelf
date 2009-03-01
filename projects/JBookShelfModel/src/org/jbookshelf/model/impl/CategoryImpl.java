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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
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
 *   <li>{@link org.jbookshelf.model.impl.CategoryImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.CategoryImpl#getChildren <em>Children</em>}</li>
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
     * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParent()
     * @generated
     * @ordered
     */
    protected Category parent;
    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<Category> children;

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
            case ModelPackage.CATEGORY__PARENT:
                if (resolve) return getParent();
                return basicGetParent();
            case ModelPackage.CATEGORY__CHILDREN:
                return getChildren();
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
            case ModelPackage.CATEGORY__PARENT:
                if (parent != null)
                    msgs = ((InternalEObject)parent).eInverseRemove(this, ModelPackage.CATEGORY__CHILDREN, Category.class, msgs);
                return basicSetParent((Category)otherEnd, msgs);
            case ModelPackage.CATEGORY__CHILDREN:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
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
            case ModelPackage.CATEGORY__PARENT:
                return basicSetParent(null, msgs);
            case ModelPackage.CATEGORY__CHILDREN:
                return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.CATEGORY__PARENT:
                return parent != null;
            case ModelPackage.CATEGORY__CHILDREN:
                return children != null && !children.isEmpty();
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
            case ModelPackage.CATEGORY__PARENT:
                setParent((Category)newValue);
                return;
            case ModelPackage.CATEGORY__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection<? extends Category>)newValue);
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
            case ModelPackage.CATEGORY__PARENT:
                setParent((Category)null);
                return;
            case ModelPackage.CATEGORY__CHILDREN:
                getChildren().clear();
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Category getParent()
    {
        if (parent != null && parent.eIsProxy())
        {
            InternalEObject oldParent = (InternalEObject)parent;
            parent = (Category)eResolveProxy(oldParent);
            if (parent != oldParent)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.CATEGORY__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Category basicGetParent()
    {
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(Category newParent, NotificationChain msgs)
    {
        Category oldParent = parent;
        parent = newParent;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.CATEGORY__PARENT, oldParent, newParent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(Category newParent)
    {
        if (newParent != parent)
        {
            NotificationChain msgs = null;
            if (parent != null)
                msgs = ((InternalEObject)parent).eInverseRemove(this, ModelPackage.CATEGORY__CHILDREN, Category.class, msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, ModelPackage.CATEGORY__CHILDREN, Category.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CATEGORY__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Category> getChildren()
    {
        if (children == null)
        {
            children = new EObjectWithInverseEList<Category>(Category.class, this, ModelPackage.CATEGORY__CHILDREN, ModelPackage.CATEGORY__PARENT);
        }
        return children;
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
