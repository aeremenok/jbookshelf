/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.sql.Date;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.jbookshelf.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JbookshelfFactoryImpl extends EFactoryImpl implements JbookshelfFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static JbookshelfFactory init()
    {
        try
        {
            JbookshelfFactory theJbookshelfFactory = (JbookshelfFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/jbookshelf.ecore"); 
            if (theJbookshelfFactory != null)
            {
                return theJbookshelfFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new JbookshelfFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JbookshelfFactoryImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass)
    {
        switch (eClass.getClassifierID())
        {
            case JbookshelfPackage.AUTHOR: return createAuthor();
            case JbookshelfPackage.READING_UNIT: return createReadingUnit();
            case JbookshelfPackage.CATEGORY: return createCategory();
            case JbookshelfPackage.COMMENT: return createComment();
            case JbookshelfPackage.COMMENTABLE: return createCommentable();
            case JbookshelfPackage.CATEGORIZABLE: return createCategorizable();
            case JbookshelfPackage.UNIQUE: return createUnique();
            case JbookshelfPackage.BOOK_SHELF: return createBookShelf();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue)
    {
        switch (eDataType.getClassifierID())
        {
            case JbookshelfPackage.DATE:
                return createDateFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue)
    {
        switch (eDataType.getClassifierID())
        {
            case JbookshelfPackage.DATE:
                return convertDateToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Author createAuthor()
    {
        AuthorImpl author = new AuthorImpl();
        return author;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReadingUnit createReadingUnit()
    {
        ReadingUnitImpl readingUnit = new ReadingUnitImpl();
        return readingUnit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Category createCategory()
    {
        CategoryImpl category = new CategoryImpl();
        return category;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Comment createComment()
    {
        CommentImpl comment = new CommentImpl();
        return comment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Commentable createCommentable()
    {
        CommentableImpl commentable = new CommentableImpl();
        return commentable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Categorizable createCategorizable()
    {
        CategorizableImpl categorizable = new CategorizableImpl();
        return categorizable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Unique createUnique()
    {
        UniqueImpl unique = new UniqueImpl();
        return unique;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BookShelf createBookShelf()
    {
        BookShelfImpl bookShelf = new BookShelfImpl();
        return bookShelf;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date createDateFromString(EDataType eDataType, String initialValue)
    {
        return (Date)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertDateToString(EDataType eDataType, Object instanceValue)
    {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JbookshelfPackage getJbookshelfPackage()
    {
        return (JbookshelfPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static JbookshelfPackage getPackage()
    {
        return JbookshelfPackage.eINSTANCE;
    }

} //JbookshelfFactoryImpl
