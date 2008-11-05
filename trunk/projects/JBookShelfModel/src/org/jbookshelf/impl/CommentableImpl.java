/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Commentable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.jbookshelf.impl.CommentableImpl#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class CommentableImpl
    extends EObjectImpl
    implements
        Commentable
{
    /**
     * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getComments()
     * @generated
     * @ordered
     */
    protected EList<Comment> comments;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CommentableImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(
        int featureID,
        boolean resolve,
        boolean coreType )
    {
        switch ( featureID )
        {
            case JbookshelfPackage.COMMENTABLE__COMMENTS:
                return getComments();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public NotificationChain eInverseAdd(
        InternalEObject otherEnd,
        int featureID,
        NotificationChain msgs )
    {
        switch ( featureID )
        {
            case JbookshelfPackage.COMMENTABLE__COMMENTS:
                return ((InternalEList<InternalEObject>) (InternalEList<?>) getComments()).basicAdd( otherEnd, msgs );
        }
        return super.eInverseAdd( otherEnd, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(
        InternalEObject otherEnd,
        int featureID,
        NotificationChain msgs )
    {
        switch ( featureID )
        {
            case JbookshelfPackage.COMMENTABLE__COMMENTS:
                return ((InternalEList<?>) getComments()).basicRemove( otherEnd, msgs );
        }
        return super.eInverseRemove( otherEnd, featureID, msgs );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(
        int featureID )
    {
        switch ( featureID )
        {
            case JbookshelfPackage.COMMENTABLE__COMMENTS:
                return comments != null && !comments.isEmpty();
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public void eSet(
        int featureID,
        Object newValue )
    {
        switch ( featureID )
        {
            case JbookshelfPackage.COMMENTABLE__COMMENTS:
                getComments().clear();
                getComments().addAll( (Collection<? extends Comment>) newValue );
                return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(
        int featureID )
    {
        switch ( featureID )
        {
            case JbookshelfPackage.COMMENTABLE__COMMENTS:
                getComments().clear();
                return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Comment> getComments()
    {
        if ( comments == null )
        {
            comments =
                new EObjectContainmentWithInverseEList<Comment>( Comment.class, this,
                    JbookshelfPackage.COMMENTABLE__COMMENTS, JbookshelfPackage.COMMENT__SUBJECT );
        }
        return comments;
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return JbookshelfPackage.Literals.COMMENTABLE;
    }

} // CommentableImpl
