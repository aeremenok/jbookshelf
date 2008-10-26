package org.jbookshelf;

import java.sql.Date;
import org.eclipse.emf.ecore.EObject;

/**
 * @author eav
 * @model
 */
public interface Comment extends EObject
{
    /**
     * @return
     * @model
     */
    String getContent();

    /**
     * Sets the value of the '{@link org.jbookshelf.Comment#getContent <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content</em>' attribute.
     * @see #getContent()
     * @generated
     */
    void setContent(String value);

    /**
     * @return
     * @model
     */
    Date getCreationDate();

    /**
     * Sets the value of the '{@link org.jbookshelf.Comment#getCreationDate <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Creation Date</em>' attribute.
     * @see #getCreationDate()
     * @generated
     */
    void setCreationDate(Date value);

    /**
     * @return
     * @model
     */
    Commentable getSubject();

    /**
     * Sets the value of the '{@link org.jbookshelf.Comment#getSubject <em>Subject</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Subject</em>' reference.
     * @see #getSubject()
     * @generated
     */
    void setSubject(Commentable value);
}