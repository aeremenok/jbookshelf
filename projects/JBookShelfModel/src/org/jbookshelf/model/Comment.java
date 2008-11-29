package org.jbookshelf.model;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * comment to some information
 * 
 * @author eav
 * @model
 */
public interface Comment
    extends
        EObject
{
    /**
     * @return comment text
     * @model
     */
    String getContent();

    /**
     * @return creation date
     * @model
     */
    Date getCreationDate();

    /**
     * @return what was commented
     * @model container="true"
     */
    Commentable getSubject();

    /**
     * Returns the value of the '<em><b>Title</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Title</em>' attribute.
     * @see #setTitle(String)
     * @see org.jbookshelf.model.ModelPackage#getComment_Title()
     * @model required="true"
     * @generated
     */
    String getTitle();

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getContent <em>Content</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Content</em>' attribute.
     * @see #getContent()
     * @generated
     */
    void setContent(
        String value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getCreationDate <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Creation Date</em>' attribute.
     * @see #getCreationDate()
     * @generated
     */
    void setCreationDate(
        Date value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getSubject <em>Subject</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Subject</em>' reference.
     * @see #getSubject()
     * @generated
     */
    void setSubject(
        Commentable value );

    /**
     * Sets the value of the '{@link org.jbookshelf.model.Comment#getTitle <em>Title</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Title</em>' attribute.
     * @see #getTitle()
     * @generated
     */
    void setTitle(
        String value );
}
