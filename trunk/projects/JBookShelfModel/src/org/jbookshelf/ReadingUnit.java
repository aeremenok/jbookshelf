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
     * @model containment="true" lowerBound="1"
     */
    PhysicalUnit getPhysical();

    /**
     * Returns the value of the '<em><b>Is Read</b></em>' attribute. The default value is <code>"false"</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Read</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Is Read</em>' attribute.
     * @see #setIsRead(boolean)
     * @see org.jbookshelf.JbookshelfPackage#getReadingUnit_IsRead()
     * @model default="false" required="true"
     */
    boolean isRead();

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
     * Sets the value of the '{@link org.jbookshelf.ReadingUnit#isRead <em>Read</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Read</em>' attribute.
     * @see #isRead()
     * @generated
     */
    void setRead(
        boolean value );
}
