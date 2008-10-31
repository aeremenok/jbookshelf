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

    /**
     * Returns the value of the '<em><b>Is Read</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Read</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Read</em>' attribute.
     * @see #setIsRead(boolean)
     * @see org.jbookshelf.JbookshelfPackage#getReadingUnit_IsRead()
     * @model default="false"
     * @generated
     */
    boolean isIsRead();

    /**
     * Sets the value of the '{@link org.jbookshelf.ReadingUnit#isIsRead <em>Is Read</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Read</em>' attribute.
     * @see #isIsRead()
     * @generated
     */
    void setIsRead(boolean value);
}
