/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.jbookshelf.model.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.jbookshelf.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ModelPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelAdapterFactory()
    {
        if (modelPackage == null)
        {
            modelPackage = ModelPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object)
    {
        if (object == modelPackage)
        {
            return true;
        }
        if (object instanceof EObject)
        {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ModelSwitch<Adapter> modelSwitch =
        new ModelSwitch<Adapter>()
        {
            @Override
            public Adapter caseArchiveFile(ArchiveFile object)
            {
                return createArchiveFileAdapter();
            }
            @Override
            public Adapter caseAuthor(Author object)
            {
                return createAuthorAdapter();
            }
            @Override
            public Adapter caseBookShelf(BookShelf object)
            {
                return createBookShelfAdapter();
            }
            @Override
            public Adapter caseCategorizable(Categorizable object)
            {
                return createCategorizableAdapter();
            }
            @Override
            public Adapter caseCategory(Category object)
            {
                return createCategoryAdapter();
            }
            @Override
            public Adapter caseComment(Comment object)
            {
                return createCommentAdapter();
            }
            @Override
            public Adapter caseCommentable(Commentable object)
            {
                return createCommentableAdapter();
            }
            @Override
            public Adapter caseIndexFileFolder(IndexFileFolder object)
            {
                return createIndexFileFolderAdapter();
            }
            @Override
            public Adapter casePhysicalUnit(PhysicalUnit object)
            {
                return createPhysicalUnitAdapter();
            }
            @Override
            public Adapter caseReadingUnit(ReadingUnit object)
            {
                return createReadingUnitAdapter();
            }
            @Override
            public Adapter caseSingleFile(SingleFile object)
            {
                return createSingleFileAdapter();
            }
            @Override
            public Adapter caseSingleFileFolder(SingleFileFolder object)
            {
                return createSingleFileFolderAdapter();
            }
            @Override
            public Adapter caseUnique(Unique object)
            {
                return createUniqueAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object)
            {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target)
    {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.ArchiveFile <em>Archive File</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.ArchiveFile
     * @generated
     */
    public Adapter createArchiveFileAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Author <em>Author</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Author
     * @generated
     */
    public Adapter createAuthorAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.BookShelf <em>Book Shelf</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.BookShelf
     * @generated
     */
    public Adapter createBookShelfAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Categorizable <em>Categorizable</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Categorizable
     * @generated
     */
    public Adapter createCategorizableAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Category <em>Category</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Category
     * @generated
     */
    public Adapter createCategoryAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Comment <em>Comment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Comment
     * @generated
     */
    public Adapter createCommentAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Commentable <em>Commentable</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Commentable
     * @generated
     */
    public Adapter createCommentableAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.IndexFileFolder <em>Index File Folder</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.IndexFileFolder
     * @generated
     */
    public Adapter createIndexFileFolderAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.PhysicalUnit <em>Physical Unit</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.PhysicalUnit
     * @generated
     */
    public Adapter createPhysicalUnitAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.ReadingUnit <em>Reading Unit</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.ReadingUnit
     * @generated
     */
    public Adapter createReadingUnitAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.SingleFile <em>Single File</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.SingleFile
     * @generated
     */
    public Adapter createSingleFileAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.SingleFileFolder <em>Single File Folder</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.SingleFileFolder
     * @generated
     */
    public Adapter createSingleFileFolderAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.jbookshelf.model.Unique <em>Unique</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.jbookshelf.model.Unique
     * @generated
     */
    public Adapter createUniqueAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter()
    {
        return null;
    }

} //ModelAdapterFactory
