package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * something that could be categorized
 * 
 * @author eav
 * @model
 */
public interface Categorizable
    extends
        EObject
{
    /**
     * @return categories
     * @model
     */
    EList<Category> getCategories();
}
