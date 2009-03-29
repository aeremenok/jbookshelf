/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Citation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbookshelf.model.Citation#getCitation <em>Citation</em>}</li>
 *   <li>{@link org.jbookshelf.model.Citation#getPosition <em>Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbookshelf.model.ModelPackage#getCitation()
 * @model
 * @generated
 */
public interface Citation extends Comment
{
    /**
     * Returns the value of the '<em><b>Citation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Citation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Citation</em>' attribute.
     * @see #setCitation(String)
     * @see org.jbookshelf.model.ModelPackage#getCitation_Citation()
     * @model
     * @generated
     */
    String getCitation();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Citation#getCitation <em>Citation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Citation</em>' attribute.
     * @see #getCitation()
     * @generated
     */
    void setCitation(String value);

    /**
     * Returns the value of the '<em><b>Position</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Position</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Position</em>' attribute.
     * @see #setPosition(float)
     * @see org.jbookshelf.model.ModelPackage#getCitation_Position()
     * @model default="0" required="true"
     * @generated
     */
    float getPosition();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Citation#getPosition <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Position</em>' attribute.
     * @see #getPosition()
     * @generated
     */
    void setPosition(float value);

} // Citation
