package org.jbookshelf;

import org.eclipse.emf.ecore.EObject;

/**
 * stores metadata on hdd<br>
 * now - a single file <br>
 * further - consider using javadb
 * 
 * @author eav
 * @model abstract="true"
 */
public interface BookShelfStorage
    extends
        EObject
{
    /**
     * @model
     */
    BookShelf getBookShelf();

    /**
     * @model
     */
    void loadCollection();

    /**
     * @model
     */
    void saveCollection();

    /**
     * Sets the value of the '{@link org.jbookshelf.BookShelfStorage#getBookShelf <em>Book Shelf</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Book Shelf</em>' reference.
     * @see #getBookShelf()
     * @generated
     */
    void setBookShelf(
        BookShelf value );
}
