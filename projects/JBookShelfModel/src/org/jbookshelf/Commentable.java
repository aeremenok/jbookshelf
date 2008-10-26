package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @author eav
 * @model
 */
public interface Commentable
    extends
        EObject
{
    /**
     * @return
     * @model
     */
    EList<Comment> getComments();

}
