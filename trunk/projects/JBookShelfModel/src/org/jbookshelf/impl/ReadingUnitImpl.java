/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbookshelf.Author;
import org.jbookshelf.Categorizable;
import org.jbookshelf.Category;
import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Reading Unit</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#getPhysical <em>Physical</em>}</li>
 *   <li>{@link org.jbookshelf.impl.ReadingUnitImpl#isRead <em>Read</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReadingUnitImpl
    extends UniqueImpl
    implements
        ReadingUnit
{
    /**
     * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getComments()
     * @generated
     * @ordered
     */
    protected EList<Comment>       comments;

    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category>      categories;

    /**
     * The cached value of the '{@link #getAuthors() <em>Authors</em>}' reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getAuthors()
     * @generated
     * @ordered
     */
    protected EList<Author>        authors;
    /**
     * The cached value of the '{@link #getPhysical() <em>Physical</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPhysical()
     * @generated
     * @ordered
     */
    protected PhysicalUnit         physical;
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ReadingUnitImpl()
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(
        int baseFeatureID,
        Class<?> baseClass )
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                return getComments();
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                return getCategories();
            case JbookshelfPackage.READING_UNIT__AUTHORS:
                return getAuthors();
            case JbookshelfPackage.READING_UNIT__PHYSICAL:
                return getPhysical();
            case JbookshelfPackage.READING_UNIT__READ:
                return isRead() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                return comments != null && !comments.isEmpty();
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case JbookshelfPackage.READING_UNIT__AUTHORS:
                return authors != null && !authors.isEmpty();
            case JbookshelfPackage.READING_UNIT__PHYSICAL:
                return physical != null;
            case JbookshelfPackage.READING_UNIT__READ:
                return read != READ_EDEFAULT;
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
            case JbookshelfPackage.READING_UNIT__READ:
                setRead(((Boolean)newValue).booleanValue());
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
            case JbookshelfPackage.READING_UNIT__READ:
                setRead(READ_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Author> getAuthors()
    {
        if (authors == null)
        {
            authors = new EObjectWithInverseResolvingEList.ManyInverse<Author>(Author.class, this, JbookshelfPackage.READING_UNIT__AUTHORS, JbookshelfPackage.AUTHOR__READING_UNITS);
        }
        return authors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Category> getCategories()
    {
        if (categories == null)
        {
            categories = new EObjectWithInverseResolvingEList.ManyInverse<Category>(Category.class, this, JbookshelfPackage.READING_UNIT__CATEGORIES, JbookshelfPackage.CATEGORY__CATEGORIZABLES);
        }
        return categories;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Comment> getComments()
    {
        if (comments == null)
        {
            comments = new EObjectContainmentWithInverseEList<Comment>(Comment.class, this, JbookshelfPackage.READING_UNIT__COMMENTS, JbookshelfPackage.COMMENT__SUBJECT);
        }
        return comments;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbookshelfPackage.READING_UNIT__PHYSICAL, oldPhysical, newPhysical);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Comment> queryComments(
        String query )
    {
        EList<Comment> result = new BasicEList<Comment>();
        for ( Comment comment : getComments() )
        {
            if ( comment.getTitle().contains( query ) )
            {
                result.add( comment );
            }
        }
        return result; 
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getComments()).basicAdd(otherEnd, msgs);
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getCategories()).basicAdd(otherEnd, msgs);
            case JbookshelfPackage.READING_UNIT__AUTHORS:
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
            case JbookshelfPackage.READING_UNIT__COMMENTS:
                return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
            case JbookshelfPackage.READING_UNIT__CATEGORIES:
                return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
            case JbookshelfPackage.READING_UNIT__AUTHORS:
                return ((InternalEList<?>)getAuthors()).basicRemove(otherEnd, msgs);
            case JbookshelfPackage.READING_UNIT__PHYSICAL:
                return basicSetPhysical(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setPhysical(
        PhysicalUnit newPhysical )
    {
        if (newPhysical != physical)
        {
            NotificationChain msgs = null;
            if (physical != null)
                msgs = ((InternalEObject)physical).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbookshelfPackage.READING_UNIT__PHYSICAL, null, msgs);
            if (newPhysical != null)
                msgs = ((InternalEObject)newPhysical).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbookshelfPackage.READING_UNIT__PHYSICAL, null, msgs);
            msgs = basicSetPhysical(newPhysical, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.READING_UNIT__PHYSICAL, newPhysical, newPhysical));
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
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.READING_UNIT__READ, oldRead, read));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return JbookshelfPackage.Literals.READING_UNIT;
    }

} // ReadingUnitImpl
