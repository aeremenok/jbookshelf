/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.jbookshelf.Author;
import org.jbookshelf.Categorizable;
import org.jbookshelf.Category;
import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reading Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getPhysical <em>Physical</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReadingUnitImpl extends UniqueImpl implements ReadingUnit
{
    /**
     * The cached value of the '{@link #getComments() <em>Comments</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComments()
     * @generated
     * @ordered
     */
    protected EList<Comment> comments;

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
     * The cached value of the '{@link #getAuthors() <em>Authors</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAuthors()
     * @generated
     * @ordered
     */
    protected EList<Author> authors;
    /**
     * The cached value of the '{@link #getPhysical() <em>Physical</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPhysical()
     * @generated
     * @ordered
     */
    protected PhysicalUnit physical;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ReadingUnitImpl()
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
        return JbookshelfPackage.Literals.READING_UNIT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Comment> getComments()
    {
        if (comments == null)
        {
            comments = new EObjectResolvingEList<Comment>(Comment.class, this, JbookshelfPackage.READING_UNIT__COMMENTS);
        }
        return comments;
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
            authors = new EObjectResolvingEList<Author>(Author.class, this, JbookshelfPackage.READING_UNIT__AUTHORS);
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
        if (physical != null && physical.eIsProxy())
        {
            InternalEObject oldPhysical = (InternalEObject)physical;
            physical = (PhysicalUnit)eResolveProxy(oldPhysical);
            if (physical != oldPhysical)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, JbookshelfPackage.READING_UNIT__PHYSICAL, oldPhysical, physical));
            }
        }
        return physical;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PhysicalUnit basicGetPhysical()
    {
        return physical;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPhysical(PhysicalUnit newPhysical)
    {
        PhysicalUnit oldPhysical = physical;
        physical = newPhysical;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.READING_UNIT__PHYSICAL, oldPhysical, physical));
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
            categories = new EObjectResolvingEList<Category>(Category.class, this, JbookshelfPackage.READING_UNIT__CATEGORIES);
        }
        return categories;
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                return getComments();
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                return getCategories();
            case JbookshelfPackage.READING_UNIT__AUTHORS:
                return getAuthors();
            case JbookshelfPackage.READING_UNIT__PHYSICAL:
                if (resolve) return getPhysical();
                return basicGetPhysical();
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                getComments().clear();
                getComments().addAll((Collection<? extends Comment>)newValue);
                return;
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends Category>)newValue);
                return;
            case JbookshelfPackage.READING_UNIT__AUTHORS:
                getAuthors().clear();
                getAuthors().addAll((Collection<? extends Author>)newValue);
                return;
            case JbookshelfPackage.READING_UNIT__PHYSICAL:
                setPhysical((PhysicalUnit)newValue);
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                getComments().clear();
                return;
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                getCategories().clear();
                return;
            case JbookshelfPackage.READING_UNIT__AUTHORS:
                getAuthors().clear();
                return;
            case JbookshelfPackage.READING_UNIT__PHYSICAL:
                setPhysical((PhysicalUnit)null);
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                return comments != null && !comments.isEmpty();
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case JbookshelfPackage.READING_UNIT__AUTHORS:
                return authors != null && !authors.isEmpty();
            case JbookshelfPackage.READING_UNIT__PHYSICAL:
                return physical != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
    {
        if (baseClass == Commentable.class)
        {
            switch (derivedFeatureID)
            {
                case JbookshelfPackage.READING_UNIT__COMMENTS: return JbookshelfPackage.COMMENTABLE__COMMENTS;
                default: return -1;
            }
        }
        if (baseClass == Categorizable.class)
        {
            switch (derivedFeatureID)
            {
                case JbookshelfPackage.READING_UNIT__CATEGORIES: return JbookshelfPackage.CATEGORIZABLE__CATEGORIES;
                default: return -1;
            }
        }
        if (baseClass == EObject.class)
        {
            switch (derivedFeatureID)
            {
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
    {
        if (baseClass == Commentable.class)
        {
            switch (baseFeatureID)
            {
                case JbookshelfPackage.COMMENTABLE__COMMENTS: return JbookshelfPackage.READING_UNIT__COMMENTS;
                default: return -1;
            }
        }
        if (baseClass == Categorizable.class)
        {
            switch (baseFeatureID)
            {
                case JbookshelfPackage.CATEGORIZABLE__CATEGORIES: return JbookshelfPackage.READING_UNIT__CATEGORIES;
                default: return -1;
            }
        }
        if (baseClass == EObject.class)
        {
            switch (baseFeatureID)
            {
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //ReadingUnitImpl
