package org.jbookshelf.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * something that could be categorized
 * 
 * @author eav
 * @model abstract="true"
 */
public interface Categorizable
    extends
        Commentable,
        EObject
{
    /**
     * @return categories
     * @model
     */
    EList<Category> getCategories();
}
