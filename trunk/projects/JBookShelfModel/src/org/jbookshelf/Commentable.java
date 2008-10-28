package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * something that could be commented
 * 
 * @author eav
 * @model
 */
public interface Commentable
    extends
        EObject
{
    /**
     * @return comments
     * @model
     */
    EList<Comment> getComments();

}
