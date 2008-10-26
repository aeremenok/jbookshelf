package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @author eav
 * @model
 */
public interface Category
    extends
        Unique,
        EObject
{
    /**
     * @return
     * @model
     */
    EList<Categorizable> getCategorizables();

    /**
     * @return
     * @model
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.jbookshelf.Category#getDescription <em>Description</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(
        String value );
}
