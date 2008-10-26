/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.jbookshelf.JbookshelfPackage
 * @generated
 */
public interface JbookshelfFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    JbookshelfFactory eINSTANCE = org.jbookshelf.impl.JbookshelfFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Author</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Author</em>'.
     * @generated
     */
    Author createAuthor();

    /**
     * Returns a new object of class '<em>Reading Unit</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Reading Unit</em>'.
     * @generated
     */
    ReadingUnit createReadingUnit();

    /**
     * Returns a new object of class '<em>Category</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Category</em>'.
     * @generated
     */
    Category createCategory();

    /**
     * Returns a new object of class '<em>Comment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Comment</em>'.
     * @generated
     */
    Comment createComment();

    /**
     * Returns a new object of class '<em>Commentable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Commentable</em>'.
     * @generated
     */
    Commentable createCommentable();

    /**
     * Returns a new object of class '<em>Categorizable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Categorizable</em>'.
     * @generated
     */
    Categorizable createCategorizable();

    /**
     * Returns a new object of class '<em>Unique</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Unique</em>'.
     * @generated
     */
    Unique createUnique();

    /**
     * Returns a new object of class '<em>Book Shelf</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Book Shelf</em>'.
     * @generated
     */
    BookShelf createBookShelf();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    JbookshelfPackage getJbookshelfPackage();

} //JbookshelfFactory
