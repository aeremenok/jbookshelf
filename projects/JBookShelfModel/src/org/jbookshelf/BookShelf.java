package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @author eav
 * @model
 */
public interface BookShelf extends EObject
{
    /**
     * @return
     * @model
     */
    EList<Unique> getUniques();
}
