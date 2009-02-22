/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbookshelf.model.Book#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.jbookshelf.model.Book#getPhysical <em>Physical</em>}</li>
 *   <li>{@link org.jbookshelf.model.Book#getRead <em>Read</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbookshelf.model.ModelPackage#getBook()
 * @model
 * @generated
 */
public interface Book extends Categorizable, EObject
{
    /**
     * Returns the value of the '<em><b>Authors</b></em>' reference list.
     * The list contents are of type {@link org.jbookshelf.model.Author}.
     * It is bidirectional and its opposite is '{@link org.jbookshelf.model.Author#getBooks <em>Books</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Authors</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Authors</em>' reference list.
     * @see org.jbookshelf.model.ModelPackage#getBook_Authors()
     * @see org.jbookshelf.model.Author#getBooks
     * @model opposite="books"
     * @generated
     */
    EList<Author> getAuthors();

    /**
     * Returns the value of the '<em><b>Physical</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Physical</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Physical</em>' containment reference.
     * @see #setPhysical(PhysicalUnit)
     * @see org.jbookshelf.model.ModelPackage#getBook_Physical()
     * @model containment="true" required="true"
     * @generated
     */
    PhysicalUnit getPhysical();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Book#getPhysical <em>Physical</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Physical</em>' containment reference.
     * @see #getPhysical()
     * @generated
     */
    void setPhysical(PhysicalUnit value);

    /**
     * Returns the value of the '<em><b>Read</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Read</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Read</em>' attribute.
     * @see #setRead(float)
     * @see org.jbookshelf.model.ModelPackage#getBook_Read()
     * @model default="0" required="true"
     * @generated
     */
    float getRead();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Book#getRead <em>Read</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Read</em>' attribute.
     * @see #getRead()
     * @generated
     */
    void setRead(float value);

} // Book
