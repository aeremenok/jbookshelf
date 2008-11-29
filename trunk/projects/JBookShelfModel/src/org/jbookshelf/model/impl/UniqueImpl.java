/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.Unique;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Unique</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.jbookshelf.model.impl.UniqueImpl#getName <em>Name</em>}</li>
 * <li>{@link org.jbookshelf.model.impl.UniqueImpl#getRelated <em>Related</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class UniqueImpl
    extends EObjectImpl
    implements
        Unique
{
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String              name          = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getRelated() <em>Related</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRelated()
     * @generated
     * @ordered
     */
    protected EList<Unique>       related;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected UniqueImpl()
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
            case ModelPackage.UNIQUE__NAME:
                return getName();
            case ModelPackage.UNIQUE__RELATED:
                return getRelated();
        }
        return super.eGet( featureID, resolve, coreType );
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
            case ModelPackage.UNIQUE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals( name );
            case ModelPackage.UNIQUE__RELATED:
                return related != null && !related.isEmpty();
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
            case ModelPackage.UNIQUE__NAME:
                setName( (String) newValue );
                return;
            case ModelPackage.UNIQUE__RELATED:
                getRelated().clear();
                getRelated().addAll( (Collection<? extends Unique>) newValue );
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
            case ModelPackage.UNIQUE__NAME:
                setName( NAME_EDEFAULT );
                return;
            case ModelPackage.UNIQUE__RELATED:
                getRelated().clear();
                return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName()
    {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Unique> getRelated()
    {
        if ( related == null )
        {
            related = new EObjectEList<Unique>( Unique.class, this, ModelPackage.UNIQUE__RELATED );
        }
        return related;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(
        String newName )
    {
        String oldName = name;
        name = newName;
        if ( eNotificationRequired() )
        {
            eNotify( new ENotificationImpl( this, Notification.SET, ModelPackage.UNIQUE__NAME, oldName, name ) );
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString()
    {
        if ( eIsProxy() )
        {
            return super.toString();
        }

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (name: " );
        result.append( name );
        result.append( ')' );
        return result.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return ModelPackage.Literals.UNIQUE;
    }

} // UniqueImpl
