package org.jbookshelf;

import org.eclipse.emf.ecore.EObject;

/**
 * stores metadata on hdd<br>
 * now - a single file <br>
 * further - consider using javadb
 * 
 * @author eav
 * @model
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
     * Sets the value of the '{@link org.jbookshelf.BookShelfStorage#getBookShelf <em>Book Shelf</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Book Shelf</em>' container reference.
     * @see #getBookShelf()
     * @generated
     */
    void setBookShelf(BookShelf value);

    /**
     * @model
     */
    void loadCollection();

    /**
     * @model
     */
    void saveCollection();
}
