package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * something that could be read
 * 
 * @author eav
 * @model
 */
public interface ReadingUnit
    extends
        Unique,
        Commentable,
        Categorizable,
        EObject
{
    /**
     * @return unit's authors
     * @model
     */
    EList<Author> getAuthors();

    /**
     * @return physical implementation of unit
     * @model
     */
    PhysicalUnit getPhysical();

    /**
     * Sets the value of the '{@link org.jbookshelf.ReadingUnit#getPhysical <em>Physical</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Physical</em>' reference.
     * @see #getPhysical()
     * @generated
     */
    void setPhysical(
        PhysicalUnit value );
}
