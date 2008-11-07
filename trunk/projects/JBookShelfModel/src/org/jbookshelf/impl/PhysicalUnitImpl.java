/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.PhysicalUnit;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Physical Unit</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public abstract class PhysicalUnitImpl
    extends EObjectImpl
    implements
        PhysicalUnit
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PhysicalUnitImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public void openFolder()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public void openUnit()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return JbookshelfPackage.Literals.PHYSICAL_UNIT;
    }

} // PhysicalUnitImpl
