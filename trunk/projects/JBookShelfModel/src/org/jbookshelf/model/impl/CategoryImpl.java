/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.jbookshelf.model.Categorizable;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.model.impl.CategoryImpl#getCategorizables <em>Categorizables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CategoryImpl extends CommentableImpl implements Category
{
    /**
     * The cached value of the '{@link #getCategorizables() <em>Categorizables</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategorizables()
     * @generated
     * @ordered
     */
    protected EList<Categorizable> categorizables;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CategoryImpl()
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
        return ModelPackage.Literals.CATEGORY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Categorizable> getCategorizables()
    {
        if (categorizables == null)
        {
            categorizables = new EObjectResolvingEList<Categorizable>(Categorizable.class, this, ModelPackage.CATEGORY__CATEGORIZABLES);
        }
        return categorizables;
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                return getCategorizables();
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                getCategorizables().clear();
                getCategorizables().addAll((Collection<? extends Categorizable>)newValue);
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                getCategorizables().clear();
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
            case ModelPackage.CATEGORY__CATEGORIZABLES:
                return categorizables != null && !categorizables.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //CategoryImpl
