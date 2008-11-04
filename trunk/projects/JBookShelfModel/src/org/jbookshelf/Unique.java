package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * something that has unique name
 * 
 * @author eav
 * @model abstract="true"
 */
public interface Unique
    extends
        EObject
{
    /**
     * @return name
     * @model
     */
    String getName();

    /**
     * @return collection of items, related to this
     * @model
     */
    EList<Unique> getRelated();

    /**
     * Sets the value of the '{@link org.jbookshelf.Unique#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(
        String value );
}
