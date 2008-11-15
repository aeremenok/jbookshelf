package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * something that could be commented
 * 
 * @author eav
 * @model abstract="true"
 */
public interface Commentable
    extends
        Unique,
        EObject
{
    /**
     * @return comments
     * @model containment="true"
     */
    EList<Comment> getComments();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @model
     * @generated
     */
    EList<Comment> queryComments(
        String query );

}
