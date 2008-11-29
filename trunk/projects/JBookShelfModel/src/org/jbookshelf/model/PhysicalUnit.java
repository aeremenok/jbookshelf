package org.jbookshelf.model;

import org.eclipse.emf.ecore.EObject;

/**
 * physical implementation of {@link ReadingUnit}
 * 
 * @author eav
 * @model abstract="true"
 */
public interface PhysicalUnit
    extends
        EObject
{
    /**
     * open unit in default browser
     * 
     * @model
     */
    void openUnit();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    void openFolder();
}
