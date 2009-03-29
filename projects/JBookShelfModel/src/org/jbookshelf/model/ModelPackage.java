/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.jbookshelf.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage
    extends
        EPackage
{
    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals
    {
        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.ArchiveFileImpl <em>Archive File</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.jbookshelf.model.impl.ArchiveFileImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getArchiveFile()
         * @generated
         */
        EClass     ARCHIVE_FILE                    = eINSTANCE.getArchiveFile();

        /**
         * The meta object literal for the '<em><b>Archive File</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ARCHIVE_FILE__ARCHIVE_FILE      = eINSTANCE.getArchiveFile_ArchiveFile();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.AuthorImpl <em>Author</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.jbookshelf.model.impl.AuthorImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getAuthor()
         * @generated
         */
        EClass     AUTHOR                          = eINSTANCE.getAuthor();

        /**
         * The meta object literal for the '<em><b>Books</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference AUTHOR__BOOKS = eINSTANCE.getAuthor_Books();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.BookShelfImpl <em>Book Shelf</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.jbookshelf.model.impl.BookShelfImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getBookShelf()
         * @generated
         */
        EClass     BOOK_SHELF                      = eINSTANCE.getBookShelf();

        /**
         * The meta object literal for the '<em><b>Authors</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOOK_SHELF__AUTHORS             = eINSTANCE.getBookShelf_Authors();

        /**
         * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOOK_SHELF__CATEGORIES          = eINSTANCE.getBookShelf_Categories();

        /**
         * The meta object literal for the '<em><b>Reading Units</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOOK_SHELF__READING_UNITS       = eINSTANCE.getBookShelf_ReadingUnits();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.CategorizableImpl <em>Categorizable</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.jbookshelf.model.impl.CategorizableImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getCategorizable()
         * @generated
         */
        EClass     CATEGORIZABLE                   = eINSTANCE.getCategorizable();

        /**
         * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CATEGORIZABLE__CATEGORIES       = eINSTANCE.getCategorizable_Categories();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.CategoryImpl <em>Category</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.jbookshelf.model.impl.CategoryImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getCategory()
         * @generated
         */
        EClass     CATEGORY                        = eINSTANCE.getCategory();

        /**
         * The meta object literal for the '<em><b>Categorizables</b></em>' reference list feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference CATEGORY__CATEGORIZABLES        = eINSTANCE.getCategory_Categorizables();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CATEGORY__PARENT = eINSTANCE.getCategory_Parent();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CATEGORY__CHILDREN = eINSTANCE.getCategory_Children();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.CommentImpl <em>Comment</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.jbookshelf.model.impl.CommentImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getComment()
         * @generated
         */
        EClass     COMMENT                         = eINSTANCE.getComment();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute COMMENT__CONTENT                = eINSTANCE.getComment_Content();

        /**
         * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMMENT__CREATION_DATE          = eINSTANCE.getComment_CreationDate();

        /**
         * The meta object literal for the '<em><b>Subject</b></em>' container reference feature.
         * <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference COMMENT__SUBJECT                = eINSTANCE.getComment_Subject();

        /**
         * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute COMMENT__TITLE                  = eINSTANCE.getComment_Title();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.CommentableImpl <em>Commentable</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.jbookshelf.model.impl.CommentableImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getCommentable()
         * @generated
         */
        EClass     COMMENTABLE                     = eINSTANCE.getCommentable();

        /**
         * The meta object literal for the '<em><b>Comments</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMMENTABLE__COMMENTS           = eINSTANCE.getCommentable_Comments();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.PhysicalUnitImpl <em>Physical Unit</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see org.jbookshelf.model.impl.PhysicalUnitImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getPhysicalUnit()
         * @generated
         */
        EClass     PHYSICAL_UNIT                   = eINSTANCE.getPhysicalUnit();

        /**
         * The meta object literal for the '<em><b>File</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PHYSICAL_UNIT__FILE = eINSTANCE.getPhysicalUnit_File();

        /**
         * The meta object literal for the '<em><b>Directory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PHYSICAL_UNIT__DIRECTORY = eINSTANCE.getPhysicalUnit_Directory();

        /**
         * The meta object literal for the '<em><b>Viewer</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PHYSICAL_UNIT__VIEWER = eINSTANCE.getPhysicalUnit_Viewer();

        /**
         * The meta object literal for the '<em><b>Charset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PHYSICAL_UNIT__CHARSET = eINSTANCE.getPhysicalUnit_Charset();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.BookImpl <em>Book</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.model.impl.BookImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getBook()
         * @generated
         */
        EClass BOOK = eINSTANCE.getBook();

        /**
         * The meta object literal for the '<em><b>Authors</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOOK__AUTHORS = eINSTANCE.getBook_Authors();

        /**
         * The meta object literal for the '<em><b>Physical</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOOK__PHYSICAL = eINSTANCE.getBook_Physical();

        /**
         * The meta object literal for the '<em><b>Read</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOOK__READ = eINSTANCE.getBook_Read();

        /**
         * The meta object literal for the '{@link org.jbookshelf.model.impl.UniqueImpl <em>Unique</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.jbookshelf.model.impl.UniqueImpl
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getUnique()
         * @generated
         */
        EClass     UNIQUE                          = eINSTANCE.getUnique();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute UNIQUE__NAME                    = eINSTANCE.getUnique_Name();

        /**
         * The meta object literal for the '<em><b>Related</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference UNIQUE__RELATED                 = eINSTANCE.getUnique_Related();

        /**
         * The meta object literal for the '<em>File</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see java.io.File
         * @see org.jbookshelf.model.impl.ModelPackageImpl#getFile()
         * @generated
         */
        EDataType  FILE                            = eINSTANCE.getFile();

    }

    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String       eNAME                            = "model";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String       eNS_URI                          = "http:///org/jbookshelf/model.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String       eNS_PREFIX                       = "org.jbookshelf.model";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    ModelPackage eINSTANCE                        = org.jbookshelf.model.impl.ModelPackageImpl.init();

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.PhysicalUnitImpl <em>Physical Unit</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.jbookshelf.model.impl.PhysicalUnitImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getPhysicalUnit()
     * @generated
     */
    int          PHYSICAL_UNIT                    = 7;

    /**
     * The feature id for the '<em><b>File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHYSICAL_UNIT__FILE = 0;

    /**
     * The feature id for the '<em><b>Directory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHYSICAL_UNIT__DIRECTORY = 1;

    /**
     * The feature id for the '<em><b>Viewer</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHYSICAL_UNIT__VIEWER = 2;

    /**
     * The feature id for the '<em><b>Charset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHYSICAL_UNIT__CHARSET = 3;

    /**
     * The number of structural features of the '<em>Physical Unit</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          PHYSICAL_UNIT_FEATURE_COUNT      = 4;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.ArchiveFileImpl <em>Archive File</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.impl.ArchiveFileImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getArchiveFile()
     * @generated
     */
    int          ARCHIVE_FILE                     = 0;

    /**
     * The feature id for the '<em><b>File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARCHIVE_FILE__FILE = PHYSICAL_UNIT__FILE;

    /**
     * The feature id for the '<em><b>Directory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARCHIVE_FILE__DIRECTORY = PHYSICAL_UNIT__DIRECTORY;

    /**
     * The feature id for the '<em><b>Viewer</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARCHIVE_FILE__VIEWER = PHYSICAL_UNIT__VIEWER;

    /**
     * The feature id for the '<em><b>Charset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARCHIVE_FILE__CHARSET = PHYSICAL_UNIT__CHARSET;

    /**
     * The feature id for the '<em><b>Archive File</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          ARCHIVE_FILE__ARCHIVE_FILE       = PHYSICAL_UNIT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Archive File</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int          ARCHIVE_FILE_FEATURE_COUNT       = PHYSICAL_UNIT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.UniqueImpl <em>Unique</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.impl.UniqueImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getUnique()
     * @generated
     */
    int          UNIQUE                           = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          UNIQUE__NAME                     = 0;

    /**
     * The feature id for the '<em><b>Related</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          UNIQUE__RELATED                  = 1;

    /**
     * The number of structural features of the '<em>Unique</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          UNIQUE_FEATURE_COUNT             = 2;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.CommentableImpl <em>Commentable</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.impl.CommentableImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getCommentable()
     * @generated
     */
    int          COMMENTABLE                      = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          COMMENTABLE__NAME                = UNIQUE__NAME;

    /**
     * The feature id for the '<em><b>Related</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          COMMENTABLE__RELATED             = UNIQUE__RELATED;

    /**
     * The feature id for the '<em><b>Comments</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          COMMENTABLE__COMMENTS            = UNIQUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Commentable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int          COMMENTABLE_FEATURE_COUNT        = UNIQUE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.CategorizableImpl <em>Categorizable</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.jbookshelf.model.impl.CategorizableImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getCategorizable()
     * @generated
     */
    int          CATEGORIZABLE                    = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORIZABLE__NAME              = COMMENTABLE__NAME;

    /**
     * The feature id for the '<em><b>Related</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORIZABLE__RELATED           = COMMENTABLE__RELATED;

    /**
     * The feature id for the '<em><b>Comments</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORIZABLE__COMMENTS          = COMMENTABLE__COMMENTS;

    /**
     * The feature id for the '<em><b>Categories</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORIZABLE__CATEGORIES        = COMMENTABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Categorizable</em>' class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORIZABLE_FEATURE_COUNT      = COMMENTABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.AuthorImpl <em>Author</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.impl.AuthorImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getAuthor()
     * @generated
     */
    int          AUTHOR                           = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          AUTHOR__NAME                     = CATEGORIZABLE__NAME;

    /**
     * The feature id for the '<em><b>Related</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          AUTHOR__RELATED                  = CATEGORIZABLE__RELATED;

    /**
     * The feature id for the '<em><b>Comments</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          AUTHOR__COMMENTS                 = CATEGORIZABLE__COMMENTS;

    /**
     * The feature id for the '<em><b>Categories</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          AUTHOR__CATEGORIES               = CATEGORIZABLE__CATEGORIES;

    /**
     * The feature id for the '<em><b>Books</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AUTHOR__BOOKS = CATEGORIZABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Author</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          AUTHOR_FEATURE_COUNT             = CATEGORIZABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.BookShelfImpl <em>Book Shelf</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.impl.BookShelfImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getBookShelf()
     * @generated
     */
    int          BOOK_SHELF                       = 2;

    /**
     * The feature id for the '<em><b>Authors</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          BOOK_SHELF__AUTHORS              = 0;

    /**
     * The feature id for the '<em><b>Categories</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          BOOK_SHELF__CATEGORIES           = 1;

    /**
     * The feature id for the '<em><b>Reading Units</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          BOOK_SHELF__READING_UNITS        = 2;

    /**
     * The number of structural features of the '<em>Book Shelf</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int          BOOK_SHELF_FEATURE_COUNT         = 3;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.CategoryImpl <em>Category</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.impl.CategoryImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getCategory()
     * @generated
     */
    int          CATEGORY                         = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORY__NAME                   = COMMENTABLE__NAME;

    /**
     * The feature id for the '<em><b>Related</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORY__RELATED                = COMMENTABLE__RELATED;

    /**
     * The feature id for the '<em><b>Comments</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORY__COMMENTS               = COMMENTABLE__COMMENTS;

    /**
     * The feature id for the '<em><b>Categorizables</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int          CATEGORY__CATEGORIZABLES         = COMMENTABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY__PARENT = COMMENTABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY__CHILDREN = COMMENTABLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Category</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          CATEGORY_FEATURE_COUNT           = COMMENTABLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.CommentImpl <em>Comment</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.impl.CommentImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getComment()
     * @generated
     */
    int          COMMENT                          = 5;

    /**
     * The feature id for the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          COMMENT__CONTENT                 = 0;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          COMMENT__CREATION_DATE           = 1;

    /**
     * The feature id for the '<em><b>Subject</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int          COMMENT__SUBJECT                 = 2;

    /**
     * The feature id for the '<em><b>Title</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          COMMENT__TITLE                   = 3;

    /**
     * The number of structural features of the '<em>Comment</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int          COMMENT_FEATURE_COUNT            = 4;

    /**
     * The meta object id for the '{@link org.jbookshelf.model.impl.BookImpl <em>Book</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.model.impl.BookImpl
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getBook()
     * @generated
     */
    int BOOK = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK__NAME = CATEGORIZABLE__NAME;

    /**
     * The feature id for the '<em><b>Related</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK__RELATED = CATEGORIZABLE__RELATED;

    /**
     * The feature id for the '<em><b>Comments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK__COMMENTS = CATEGORIZABLE__COMMENTS;

    /**
     * The feature id for the '<em><b>Categories</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK__CATEGORIES = CATEGORIZABLE__CATEGORIES;

    /**
     * The feature id for the '<em><b>Authors</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK__AUTHORS = CATEGORIZABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Physical</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK__PHYSICAL = CATEGORIZABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Read</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK__READ = CATEGORIZABLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Book</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK_FEATURE_COUNT = CATEGORIZABLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '<em>File</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see java.io.File
     * @see org.jbookshelf.model.impl.ModelPackageImpl#getFile()
     * @generated
     */
    int          FILE                             = 10;

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.ArchiveFile <em>Archive File</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Archive File</em>'.
     * @see org.jbookshelf.model.ArchiveFile
     * @generated
     */
    EClass getArchiveFile();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.ArchiveFile#getArchiveFile <em>Archive File</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Archive File</em>'.
     * @see org.jbookshelf.model.ArchiveFile#getArchiveFile()
     * @see #getArchiveFile()
     * @generated
     */
    EAttribute getArchiveFile_ArchiveFile();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.Author <em>Author</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Author</em>'.
     * @see org.jbookshelf.model.Author
     * @generated
     */
    EClass getAuthor();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.model.Author#getBooks <em>Books</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Books</em>'.
     * @see org.jbookshelf.model.Author#getBooks()
     * @see #getAuthor()
     * @generated
     */
    EReference getAuthor_Books();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.BookShelf <em>Book Shelf</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Book Shelf</em>'.
     * @see org.jbookshelf.model.BookShelf
     * @generated
     */
    EClass getBookShelf();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.model.BookShelf#getAuthors <em>Authors</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Authors</em>'.
     * @see org.jbookshelf.model.BookShelf#getAuthors()
     * @see #getBookShelf()
     * @generated
     */
    EReference getBookShelf_Authors();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.model.BookShelf#getCategories <em>Categories</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Categories</em>'.
     * @see org.jbookshelf.model.BookShelf#getCategories()
     * @see #getBookShelf()
     * @generated
     */
    EReference getBookShelf_Categories();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.model.BookShelf#getReadingUnits <em>Reading Units</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Reading Units</em>'.
     * @see org.jbookshelf.model.BookShelf#getReadingUnits()
     * @see #getBookShelf()
     * @generated
     */
    EReference getBookShelf_ReadingUnits();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.Categorizable <em>Categorizable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Categorizable</em>'.
     * @see org.jbookshelf.model.Categorizable
     * @generated
     */
    EClass getCategorizable();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.model.Categorizable#getCategories <em>Categories</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Categories</em>'.
     * @see org.jbookshelf.model.Categorizable#getCategories()
     * @see #getCategorizable()
     * @generated
     */
    EReference getCategorizable_Categories();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.Category <em>Category</em>}'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Category</em>'.
     * @see org.jbookshelf.model.Category
     * @generated
     */
    EClass getCategory();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.model.Category#getCategorizables <em>Categorizables</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Categorizables</em>'.
     * @see org.jbookshelf.model.Category#getCategorizables()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Categorizables();

    /**
     * Returns the meta object for the container reference '{@link org.jbookshelf.model.Category#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see org.jbookshelf.model.Category#getParent()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Parent();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.model.Category#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see org.jbookshelf.model.Category#getChildren()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Children();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.Comment <em>Comment</em>}'.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Comment</em>'.
     * @see org.jbookshelf.model.Comment
     * @generated
     */
    EClass getComment();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.Comment#getContent <em>Content</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content</em>'.
     * @see org.jbookshelf.model.Comment#getContent()
     * @see #getComment()
     * @generated
     */
    EAttribute getComment_Content();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.Comment#getCreationDate <em>Creation Date</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Creation Date</em>'.
     * @see org.jbookshelf.model.Comment#getCreationDate()
     * @see #getComment()
     * @generated
     */
    EAttribute getComment_CreationDate();

    /**
     * Returns the meta object for the container reference '{@link org.jbookshelf.model.Comment#getSubject <em>Subject</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Subject</em>'.
     * @see org.jbookshelf.model.Comment#getSubject()
     * @see #getComment()
     * @generated
     */
    EReference getComment_Subject();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.Comment#getTitle <em>Title</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Title</em>'.
     * @see org.jbookshelf.model.Comment#getTitle()
     * @see #getComment()
     * @generated
     */
    EAttribute getComment_Title();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.Commentable <em>Commentable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Commentable</em>'.
     * @see org.jbookshelf.model.Commentable
     * @generated
     */
    EClass getCommentable();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.model.Commentable#getComments <em>Comments</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Comments</em>'.
     * @see org.jbookshelf.model.Commentable#getComments()
     * @see #getCommentable()
     * @generated
     */
    EReference getCommentable_Comments();

    /**
     * Returns the meta object for data type '{@link java.io.File <em>File</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for data type '<em>File</em>'.
     * @see java.io.File
     * @model instanceClass="java.io.File"
     * @generated
     */
    EDataType getFile();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ModelFactory getModelFactory();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.PhysicalUnit <em>Physical Unit</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Physical Unit</em>'.
     * @see org.jbookshelf.model.PhysicalUnit
     * @generated
     */
    EClass getPhysicalUnit();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.PhysicalUnit#getFile <em>File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File</em>'.
     * @see org.jbookshelf.model.PhysicalUnit#getFile()
     * @see #getPhysicalUnit()
     * @generated
     */
    EAttribute getPhysicalUnit_File();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.PhysicalUnit#getDirectory <em>Directory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Directory</em>'.
     * @see org.jbookshelf.model.PhysicalUnit#getDirectory()
     * @see #getPhysicalUnit()
     * @generated
     */
    EAttribute getPhysicalUnit_Directory();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.PhysicalUnit#getViewer <em>Viewer</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Viewer</em>'.
     * @see org.jbookshelf.model.PhysicalUnit#getViewer()
     * @see #getPhysicalUnit()
     * @generated
     */
    EAttribute getPhysicalUnit_Viewer();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.PhysicalUnit#getCharset <em>Charset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Charset</em>'.
     * @see org.jbookshelf.model.PhysicalUnit#getCharset()
     * @see #getPhysicalUnit()
     * @generated
     */
    EAttribute getPhysicalUnit_Charset();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.Book <em>Book</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Book</em>'.
     * @see org.jbookshelf.model.Book
     * @generated
     */
    EClass getBook();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.model.Book#getAuthors <em>Authors</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Authors</em>'.
     * @see org.jbookshelf.model.Book#getAuthors()
     * @see #getBook()
     * @generated
     */
    EReference getBook_Authors();

    /**
     * Returns the meta object for the containment reference '{@link org.jbookshelf.model.Book#getPhysical <em>Physical</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Physical</em>'.
     * @see org.jbookshelf.model.Book#getPhysical()
     * @see #getBook()
     * @generated
     */
    EReference getBook_Physical();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.Book#getRead <em>Read</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Read</em>'.
     * @see org.jbookshelf.model.Book#getRead()
     * @see #getBook()
     * @generated
     */
    EAttribute getBook_Read();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.model.Unique <em>Unique</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Unique</em>'.
     * @see org.jbookshelf.model.Unique
     * @generated
     */
    EClass getUnique();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.model.Unique#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.jbookshelf.model.Unique#getName()
     * @see #getUnique()
     * @generated
     */
    EAttribute getUnique_Name();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.model.Unique#getRelated <em>Related</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Related</em>'.
     * @see org.jbookshelf.model.Unique#getRelated()
     * @see #getUnique()
     * @generated
     */
    EReference getUnique_Related();

} // ModelPackage
