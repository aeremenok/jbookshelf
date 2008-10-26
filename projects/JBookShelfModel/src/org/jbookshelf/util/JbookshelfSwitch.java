/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.jbookshelf.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.jbookshelf.JbookshelfPackage
 * @generated
 */
public class JbookshelfSwitch<T>
{
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static JbookshelfPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JbookshelfSwitch()
    {
        if (modelPackage == null)
        {
            modelPackage = JbookshelfPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject)
    {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject)
    {
        if (theEClass.eContainer() == modelPackage)
        {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else
        {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject)
    {
        switch (classifierID)
        {
            case JbookshelfPackage.AUTHOR:
            {
                Author author = (Author)theEObject;
                T result = caseAuthor(author);
                if (result == null) result = caseUnique(author);
                if (result == null) result = caseCommentable(author);
                if (result == null) result = caseCategorizable(author);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case JbookshelfPackage.READING_UNIT:
            {
                ReadingUnit readingUnit = (ReadingUnit)theEObject;
                T result = caseReadingUnit(readingUnit);
                if (result == null) result = caseUnique(readingUnit);
                if (result == null) result = caseCommentable(readingUnit);
                if (result == null) result = caseCategorizable(readingUnit);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case JbookshelfPackage.CATEGORY:
            {
                Category category = (Category)theEObject;
                T result = caseCategory(category);
                if (result == null) result = caseUnique(category);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case JbookshelfPackage.COMMENT:
            {
                Comment comment = (Comment)theEObject;
                T result = caseComment(comment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case JbookshelfPackage.COMMENTABLE:
            {
                Commentable commentable = (Commentable)theEObject;
                T result = caseCommentable(commentable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case JbookshelfPackage.CATEGORIZABLE:
            {
                Categorizable categorizable = (Categorizable)theEObject;
                T result = caseCategorizable(categorizable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case JbookshelfPackage.UNIQUE:
            {
                Unique unique = (Unique)theEObject;
                T result = caseUnique(unique);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case JbookshelfPackage.BOOK_SHELF:
            {
                BookShelf bookShelf = (BookShelf)theEObject;
                T result = caseBookShelf(bookShelf);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Author</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Author</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAuthor(Author object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reading Unit</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reading Unit</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReadingUnit(ReadingUnit object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Category</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCategory(Category object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComment(Comment object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Commentable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Commentable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCommentable(Commentable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Categorizable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Categorizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCategorizable(Categorizable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Unique</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unique</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUnique(Unique object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Book Shelf</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Book Shelf</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBookShelf(BookShelf object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object)
    {
        return null;
    }

} //JbookshelfSwitch
