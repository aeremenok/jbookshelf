/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.ReadingUnit;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Author</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.model.impl.AuthorImpl#getReadingUnits <em>Reading Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AuthorImpl
    extends CategorizableImpl
    implements
        Author
{
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
    public Object eGet(
        int featureID,
        boolean resolve,
        boolean coreType )
    {
        switch (featureID)
        {
            case ModelPackage.AUTHOR__READING_UNITS:
                return getReadingUnits();
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
            case ModelPackage.AUTHOR__READING_UNITS:
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
            case ModelPackage.AUTHOR__READING_UNITS:
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
            case ModelPackage.AUTHOR__READING_UNITS:
                getReadingUnits().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<ReadingUnit> getReadingUnits()
    {
        if (readingUnits == null)
        {
            readingUnits = new EObjectResolvingEList<ReadingUnit>(ReadingUnit.class, this, ModelPackage.AUTHOR__READING_UNITS);
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
        return ModelPackage.Literals.AUTHOR;
    }

} // AuthorImpl
