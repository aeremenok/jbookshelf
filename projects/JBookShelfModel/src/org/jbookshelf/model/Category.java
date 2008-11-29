package org.jbookshelf.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * category of some information
 * 
 * @author eav
 * @model
 */
public interface Category
    extends
        Commentable,
        EObject
{
    /**
     * @return what category includes
     * @model
     */
    EList<Categorizable> getCategorizables();
}
