package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * collection root
 * 
 * @author eav
 * @model
 */
public interface BookShelf
    extends
        EObject
{
    /**
     * @return all authors in collection
     * @model
     */
    EList<Author> getAuthors();

    /**
     * @return all categories in collection
     * @model
     */
    EList<Category> getCategories();

    /**
     * @return all units in collection
     * @model
     */
    EList<ReadingUnit> getReadingUnits();

    /**
     * @return all uniques in collection
     * @model
     */
    EList<Unique> getUniques();
}
