package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @author eav
 * @model
 */
public interface Categorizable extends EObject
{
    /**
     * @return
     * @model
     */
    EList<Category> getCategories();
}
