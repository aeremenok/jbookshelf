/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.model.impl;

import java.io.File;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.jbookshelf.model.*;
import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.Comment;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ModelFactory init()
    {
        try
        {
            ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/jbookshelf/model.ecore"); 
            if (theModelFactory != null)
            {
                return theModelFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ModelFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelFactoryImpl()
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
            case ModelPackage.ARCHIVE_FILE: return createArchiveFile();
            case ModelPackage.AUTHOR: return createAuthor();
            case ModelPackage.BOOK_SHELF: return createBookShelf();
            case ModelPackage.CATEGORY: return createCategory();
            case ModelPackage.COMMENT: return createComment();
            case ModelPackage.INDEX_FILE_FOLDER: return createIndexFileFolder();
            case ModelPackage.READING_UNIT: return createReadingUnit();
            case ModelPackage.SINGLE_FILE: return createSingleFile();
            case ModelPackage.SINGLE_FILE_FOLDER: return createSingleFileFolder();
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
            case ModelPackage.FILE:
                return createFileFromString(eDataType, initialValue);
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
            case ModelPackage.FILE:
                return convertFileToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ArchiveFile createArchiveFile()
    {
        ArchiveFileImpl archiveFile = new ArchiveFileImpl();
        return archiveFile;
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
    public IndexFileFolder createIndexFileFolder()
    {
        IndexFileFolderImpl indexFileFolder = new IndexFileFolderImpl();
        return indexFileFolder;
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
    public SingleFile createSingleFile()
    {
        SingleFileImpl singleFile = new SingleFileImpl();
        return singleFile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SingleFileFolder createSingleFileFolder()
    {
        SingleFileFolderImpl singleFileFolder = new SingleFileFolderImpl();
        return singleFileFolder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public File createFileFromString(EDataType eDataType, String initialValue)
    {
        return (File)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFileToString(EDataType eDataType, Object instanceValue)
    {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelPackage getModelPackage()
    {
        return (ModelPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ModelPackage getPackage()
    {
        return ModelPackage.eINSTANCE;
    }

} //ModelFactoryImpl
