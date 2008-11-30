/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */
package org.jbookshelf.model;

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
     * @param query search text
     * @return authors, whose data contains text
     * @model
     */
    EList<Author> queryAuthors(
        String query );

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
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
     * @param unique item to remove
     * @model
     */
    void removeUnique(
        Unique unique );
}
