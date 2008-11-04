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
     * @param name new {@link Author} name
     * @return new {@link Author}
     * @model
     */
    Author addAuthor(
        String name );

    /**
     * @param name new {@link Category} name
     * @return new {@link Category}
     * @model
     */
    Category addCategory(
        String name );

    /**
     * @param name new {@link ReadingUnit} name
     * @param author unit's author (optional, if null then will be set "Unknown")
     * @param category units's category (optional)
     * @param physicalUnit unit's storage
     * @return new {@link ReadingUnit}
     * @model
     */
    ReadingUnit addReadingUnit(
        String name,
        Author author,
        Category category,
        PhysicalUnit physicalUnit );

    /**
     * @return all authors in collection
     * @model containment="true"
     */
    EList<Author> getAuthors();

    /**
     * @return all categories in collection
     * @model containment="true"
     */
    EList<Category> getCategories();

    /**
     * @return all units in collection
     * @model containment="true"
     */
    EList<ReadingUnit> getReadingUnits();

    /**
     * @return physical collection storage
     * @model containment="true" lowerBound="1"
     */
    BookShelfStorage getStorage();

    /**
     * @return all uniques in collection todo probably useless
     * @model containment="true" derived="true"
     */
    EList<Unique> getUniques();

    /**
     * @param query search text
     * @return authors, whose data contains text
     * @model
     */
    EList<Author> queryAuthors(
        String query );

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @model
     * @generated
     */
    EList<Category> queryCategories(
        String query );

    /**
     * @param query search text
     * @return all uniques, whose data contains text
     * @model
     */
    EList<Unique> queryUniques(
        String query );

    /**
     * @param query search text
     * @param isRead desired units status, null = read + unread
     * @return reading units, whose data contains text
     * @model
     */
    EList<ReadingUnit> queryUnits(
        String query,
        Boolean isRead );

    /**
     * Sets the value of the '{@link org.jbookshelf.BookShelf#getStorage <em>Storage</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Storage</em>' reference.
     * @see #getStorage()
     * @generated
     */
    void setStorage(
        BookShelfStorage value );
}
