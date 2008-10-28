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
     * @return physical collection storage
     * @model
     */
    BookShelfStorage getStorage();

    /**
     * Sets the value of the '{@link org.jbookshelf.BookShelf#getStorage <em>Storage</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Storage</em>' reference.
     * @see #getStorage()
     * @generated
     */
    void setStorage(BookShelfStorage value);

    /**
     * @return all uniques in collection todo probably useless
     * @model
     */
    EList<Unique> getUniques();
}
