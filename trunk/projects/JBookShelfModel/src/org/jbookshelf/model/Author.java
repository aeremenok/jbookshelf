package org.jbookshelf.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * person who created at least one reading unit
 * 
 * @author eav
 * @model
 */
public interface Author
    extends
        Categorizable,
        EObject
{
    /**
     * @return author's creations
     * @model
     */
    EList<ReadingUnit> getReadingUnits();
}