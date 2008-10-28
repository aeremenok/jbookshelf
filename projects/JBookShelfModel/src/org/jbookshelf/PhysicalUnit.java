package org.jbookshelf;

import org.eclipse.emf.ecore.EObject;

/**
 * physical implementation of {@link ReadingUnit}
 * 
 * @author eav
 * @model
 */
public interface PhysicalUnit
    extends
        EObject
{
    /**
     * open unit in default browser
     * 
     * @model
     */
    void openUnit();
}
