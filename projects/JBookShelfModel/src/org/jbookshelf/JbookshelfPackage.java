/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.jbookshelf.JbookshelfFactory
 * @model kind="package"
 * @generated
 */
public interface JbookshelfPackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "jbookshelf";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http:///org/jbookshelf.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "org.jbookshelf";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    JbookshelfPackage eINSTANCE = org.jbookshelf.impl.JbookshelfPackageImpl.init();

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.UniqueImpl <em>Unique</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.UniqueImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getUnique()
     * @generated
     */
    int UNIQUE = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIQUE__NAME = 0;

    /**
     * The number of structural features of the '<em>Unique</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIQUE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.CommentableImpl <em>Commentable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.CommentableImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getCommentable()
     * @generated
     */
    int COMMENTABLE = 4;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.AuthorImpl <em>Author</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.AuthorImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getAuthor()
     * @generated
     */
    int AUTHOR = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AUTHOR__NAME = UNIQUE__NAME;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AUTHOR__COMMENTS = UNIQUE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Categories</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AUTHOR__CATEGORIES = UNIQUE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Reading Units</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AUTHOR__READING_UNITS = UNIQUE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Author</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AUTHOR_FEATURE_COUNT = UNIQUE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.ReadingUnitImpl <em>Reading Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.ReadingUnitImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getReadingUnit()
     * @generated
     */
    int READING_UNIT = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int READING_UNIT__NAME = UNIQUE__NAME;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int READING_UNIT__COMMENTS = UNIQUE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Categories</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int READING_UNIT__CATEGORIES = UNIQUE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Authors</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int READING_UNIT__AUTHORS = UNIQUE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Physical</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int READING_UNIT__PHYSICAL = UNIQUE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Reading Unit</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int READING_UNIT_FEATURE_COUNT = UNIQUE_FEATURE_COUNT + 4;


    /**
     * The meta object id for the '{@link org.jbookshelf.impl.CategoryImpl <em>Category</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.CategoryImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getCategory()
     * @generated
     */
    int CATEGORY = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY__NAME = UNIQUE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY__DESCRIPTION = UNIQUE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Categorizables</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY__CATEGORIZABLES = UNIQUE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Category</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORY_FEATURE_COUNT = UNIQUE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.CommentImpl <em>Comment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.CommentImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getComment()
     * @generated
     */
    int COMMENT = 3;

    /**
     * The feature id for the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMMENT__CONTENT = 0;

    /**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMMENT__CREATION_DATE = 1;

    /**
     * The feature id for the '<em><b>Subject</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMMENT__SUBJECT = 2;

    /**
     * The number of structural features of the '<em>Comment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMMENT_FEATURE_COUNT = 3;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMMENTABLE__COMMENTS = 0;

    /**
     * The number of structural features of the '<em>Commentable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMMENTABLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.CategorizableImpl <em>Categorizable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.CategorizableImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getCategorizable()
     * @generated
     */
    int CATEGORIZABLE = 5;

    /**
     * The feature id for the '<em><b>Categories</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORIZABLE__CATEGORIES = 0;

    /**
     * The number of structural features of the '<em>Categorizable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CATEGORIZABLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.BookShelfImpl <em>Book Shelf</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.BookShelfImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getBookShelf()
     * @generated
     */
    int BOOK_SHELF = 7;

    /**
     * The feature id for the '<em><b>Uniques</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK_SHELF__UNIQUES = 0;

    /**
     * The feature id for the '<em><b>Authors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK_SHELF__AUTHORS = 1;

    /**
     * The feature id for the '<em><b>Categories</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK_SHELF__CATEGORIES = 2;

    /**
     * The feature id for the '<em><b>Reading Units</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK_SHELF__READING_UNITS = 3;

    /**
     * The number of structural features of the '<em>Book Shelf</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOK_SHELF_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.PhysicalUnitImpl <em>Physical Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.PhysicalUnitImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getPhysicalUnit()
     * @generated
     */
    int PHYSICAL_UNIT = 10;

    /**
     * The number of structural features of the '<em>Physical Unit</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHYSICAL_UNIT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.ArchiveFileImpl <em>Archive File</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.ArchiveFileImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getArchiveFile()
     * @generated
     */
    int ARCHIVE_FILE = 8;

    /**
     * The feature id for the '<em><b>Archive File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARCHIVE_FILE__ARCHIVE_FILE = PHYSICAL_UNIT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Archive File</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARCHIVE_FILE_FEATURE_COUNT = PHYSICAL_UNIT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.IndexFileFolderImpl <em>Index File Folder</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.IndexFileFolderImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getIndexFileFolder()
     * @generated
     */
    int INDEX_FILE_FOLDER = 9;

    /**
     * The feature id for the '<em><b>Index File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDEX_FILE_FOLDER__INDEX_FILE = PHYSICAL_UNIT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Index Folder</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDEX_FILE_FOLDER__INDEX_FOLDER = PHYSICAL_UNIT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Index File Folder</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDEX_FILE_FOLDER_FEATURE_COUNT = PHYSICAL_UNIT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.SingleFileImpl <em>Single File</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.SingleFileImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getSingleFile()
     * @generated
     */
    int SINGLE_FILE = 11;

    /**
     * The feature id for the '<em><b>File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SINGLE_FILE__FILE = PHYSICAL_UNIT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Single File</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SINGLE_FILE_FEATURE_COUNT = PHYSICAL_UNIT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.jbookshelf.impl.SingleFileFolderImpl <em>Single File Folder</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.impl.SingleFileFolderImpl
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getSingleFileFolder()
     * @generated
     */
    int SINGLE_FILE_FOLDER = 12;

    /**
     * The feature id for the '<em><b>Folder</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SINGLE_FILE_FOLDER__FOLDER = PHYSICAL_UNIT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Single File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SINGLE_FILE_FOLDER__SINGLE_FILE = PHYSICAL_UNIT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Single File Folder</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SINGLE_FILE_FOLDER_FEATURE_COUNT = PHYSICAL_UNIT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '<em>Date</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.sql.Date
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getDate()
     * @generated
     */
    int DATE = 13;


    /**
     * The meta object id for the '<em>File</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.io.File
     * @see org.jbookshelf.impl.JbookshelfPackageImpl#getFile()
     * @generated
     */
    int FILE = 14;


    /**
     * Returns the meta object for class '{@link org.jbookshelf.Author <em>Author</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Author</em>'.
     * @see org.jbookshelf.Author
     * @generated
     */
    EClass getAuthor();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.Author#getReadingUnits <em>Reading Units</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Reading Units</em>'.
     * @see org.jbookshelf.Author#getReadingUnits()
     * @see #getAuthor()
     * @generated
     */
    EReference getAuthor_ReadingUnits();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.ReadingUnit <em>Reading Unit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reading Unit</em>'.
     * @see org.jbookshelf.ReadingUnit
     * @generated
     */
    EClass getReadingUnit();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.ReadingUnit#getAuthors <em>Authors</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Authors</em>'.
     * @see org.jbookshelf.ReadingUnit#getAuthors()
     * @see #getReadingUnit()
     * @generated
     */
    EReference getReadingUnit_Authors();

    /**
     * Returns the meta object for the containment reference '{@link org.jbookshelf.ReadingUnit#getPhysical <em>Physical</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Physical</em>'.
     * @see org.jbookshelf.ReadingUnit#getPhysical()
     * @see #getReadingUnit()
     * @generated
     */
    EReference getReadingUnit_Physical();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.Category <em>Category</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Category</em>'.
     * @see org.jbookshelf.Category
     * @generated
     */
    EClass getCategory();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.Category#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.jbookshelf.Category#getDescription()
     * @see #getCategory()
     * @generated
     */
    EAttribute getCategory_Description();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.Category#getCategorizables <em>Categorizables</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Categorizables</em>'.
     * @see org.jbookshelf.Category#getCategorizables()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Categorizables();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.Comment <em>Comment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Comment</em>'.
     * @see org.jbookshelf.Comment
     * @generated
     */
    EClass getComment();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.Comment#getContent <em>Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content</em>'.
     * @see org.jbookshelf.Comment#getContent()
     * @see #getComment()
     * @generated
     */
    EAttribute getComment_Content();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.Comment#getCreationDate <em>Creation Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Creation Date</em>'.
     * @see org.jbookshelf.Comment#getCreationDate()
     * @see #getComment()
     * @generated
     */
    EAttribute getComment_CreationDate();

    /**
     * Returns the meta object for the reference '{@link org.jbookshelf.Comment#getSubject <em>Subject</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Subject</em>'.
     * @see org.jbookshelf.Comment#getSubject()
     * @see #getComment()
     * @generated
     */
    EReference getComment_Subject();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.Commentable <em>Commentable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Commentable</em>'.
     * @see org.jbookshelf.Commentable
     * @generated
     */
    EClass getCommentable();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.Commentable#getComments <em>Comments</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Comments</em>'.
     * @see org.jbookshelf.Commentable#getComments()
     * @see #getCommentable()
     * @generated
     */
    EReference getCommentable_Comments();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.Categorizable <em>Categorizable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Categorizable</em>'.
     * @see org.jbookshelf.Categorizable
     * @generated
     */
    EClass getCategorizable();

    /**
     * Returns the meta object for the reference list '{@link org.jbookshelf.Categorizable#getCategories <em>Categories</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Categories</em>'.
     * @see org.jbookshelf.Categorizable#getCategories()
     * @see #getCategorizable()
     * @generated
     */
    EReference getCategorizable_Categories();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.Unique <em>Unique</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Unique</em>'.
     * @see org.jbookshelf.Unique
     * @generated
     */
    EClass getUnique();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.Unique#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.jbookshelf.Unique#getName()
     * @see #getUnique()
     * @generated
     */
    EAttribute getUnique_Name();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.BookShelf <em>Book Shelf</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Book Shelf</em>'.
     * @see org.jbookshelf.BookShelf
     * @generated
     */
    EClass getBookShelf();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.BookShelf#getUniques <em>Uniques</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Uniques</em>'.
     * @see org.jbookshelf.BookShelf#getUniques()
     * @see #getBookShelf()
     * @generated
     */
    EReference getBookShelf_Uniques();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.BookShelf#getAuthors <em>Authors</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Authors</em>'.
     * @see org.jbookshelf.BookShelf#getAuthors()
     * @see #getBookShelf()
     * @generated
     */
    EReference getBookShelf_Authors();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.BookShelf#getCategories <em>Categories</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Categories</em>'.
     * @see org.jbookshelf.BookShelf#getCategories()
     * @see #getBookShelf()
     * @generated
     */
    EReference getBookShelf_Categories();

    /**
     * Returns the meta object for the containment reference list '{@link org.jbookshelf.BookShelf#getReadingUnits <em>Reading Units</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Reading Units</em>'.
     * @see org.jbookshelf.BookShelf#getReadingUnits()
     * @see #getBookShelf()
     * @generated
     */
    EReference getBookShelf_ReadingUnits();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.ArchiveFile <em>Archive File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Archive File</em>'.
     * @see org.jbookshelf.ArchiveFile
     * @generated
     */
    EClass getArchiveFile();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.ArchiveFile#getArchiveFile <em>Archive File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Archive File</em>'.
     * @see org.jbookshelf.ArchiveFile#getArchiveFile()
     * @see #getArchiveFile()
     * @generated
     */
    EAttribute getArchiveFile_ArchiveFile();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.IndexFileFolder <em>Index File Folder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Index File Folder</em>'.
     * @see org.jbookshelf.IndexFileFolder
     * @generated
     */
    EClass getIndexFileFolder();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.IndexFileFolder#getIndexFile <em>Index File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Index File</em>'.
     * @see org.jbookshelf.IndexFileFolder#getIndexFile()
     * @see #getIndexFileFolder()
     * @generated
     */
    EAttribute getIndexFileFolder_IndexFile();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.IndexFileFolder#getIndexFolder <em>Index Folder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Index Folder</em>'.
     * @see org.jbookshelf.IndexFileFolder#getIndexFolder()
     * @see #getIndexFileFolder()
     * @generated
     */
    EAttribute getIndexFileFolder_IndexFolder();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.PhysicalUnit <em>Physical Unit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Physical Unit</em>'.
     * @see org.jbookshelf.PhysicalUnit
     * @generated
     */
    EClass getPhysicalUnit();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.SingleFile <em>Single File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Single File</em>'.
     * @see org.jbookshelf.SingleFile
     * @generated
     */
    EClass getSingleFile();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.SingleFile#getFile <em>File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File</em>'.
     * @see org.jbookshelf.SingleFile#getFile()
     * @see #getSingleFile()
     * @generated
     */
    EAttribute getSingleFile_File();

    /**
     * Returns the meta object for class '{@link org.jbookshelf.SingleFileFolder <em>Single File Folder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Single File Folder</em>'.
     * @see org.jbookshelf.SingleFileFolder
     * @generated
     */
    EClass getSingleFileFolder();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.SingleFileFolder#getFolder <em>Folder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Folder</em>'.
     * @see org.jbookshelf.SingleFileFolder#getFolder()
     * @see #getSingleFileFolder()
     * @generated
     */
    EAttribute getSingleFileFolder_Folder();

    /**
     * Returns the meta object for the attribute '{@link org.jbookshelf.SingleFileFolder#getSingleFile <em>Single File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Single File</em>'.
     * @see org.jbookshelf.SingleFileFolder#getSingleFile()
     * @see #getSingleFileFolder()
     * @generated
     */
    EAttribute getSingleFileFolder_SingleFile();

    /**
     * Returns the meta object for data type '{@link java.sql.Date <em>Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Date</em>'.
     * @see java.sql.Date
     * @model instanceClass="java.sql.Date"
     * @generated
     */
    EDataType getDate();

    /**
     * Returns the meta object for data type '{@link java.io.File <em>File</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>File</em>'.
     * @see java.io.File
     * @model instanceClass="java.io.File"
     * @generated
     */
    EDataType getFile();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    JbookshelfFactory getJbookshelfFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals
    {
        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.AuthorImpl <em>Author</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.AuthorImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getAuthor()
         * @generated
         */
        EClass AUTHOR = eINSTANCE.getAuthor();

        /**
         * The meta object literal for the '<em><b>Reading Units</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference AUTHOR__READING_UNITS = eINSTANCE.getAuthor_ReadingUnits();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.ReadingUnitImpl <em>Reading Unit</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.ReadingUnitImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getReadingUnit()
         * @generated
         */
        EClass READING_UNIT = eINSTANCE.getReadingUnit();

        /**
         * The meta object literal for the '<em><b>Authors</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference READING_UNIT__AUTHORS = eINSTANCE.getReadingUnit_Authors();

        /**
         * The meta object literal for the '<em><b>Physical</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference READING_UNIT__PHYSICAL = eINSTANCE.getReadingUnit_Physical();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.CategoryImpl <em>Category</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.CategoryImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getCategory()
         * @generated
         */
        EClass CATEGORY = eINSTANCE.getCategory();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CATEGORY__DESCRIPTION = eINSTANCE.getCategory_Description();

        /**
         * The meta object literal for the '<em><b>Categorizables</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CATEGORY__CATEGORIZABLES = eINSTANCE.getCategory_Categorizables();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.CommentImpl <em>Comment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.CommentImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getComment()
         * @generated
         */
        EClass COMMENT = eINSTANCE.getComment();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMMENT__CONTENT = eINSTANCE.getComment_Content();

        /**
         * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMMENT__CREATION_DATE = eINSTANCE.getComment_CreationDate();

        /**
         * The meta object literal for the '<em><b>Subject</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMMENT__SUBJECT = eINSTANCE.getComment_Subject();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.CommentableImpl <em>Commentable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.CommentableImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getCommentable()
         * @generated
         */
        EClass COMMENTABLE = eINSTANCE.getCommentable();

        /**
         * The meta object literal for the '<em><b>Comments</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference COMMENTABLE__COMMENTS = eINSTANCE.getCommentable_Comments();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.CategorizableImpl <em>Categorizable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.CategorizableImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getCategorizable()
         * @generated
         */
        EClass CATEGORIZABLE = eINSTANCE.getCategorizable();

        /**
         * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CATEGORIZABLE__CATEGORIES = eINSTANCE.getCategorizable_Categories();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.UniqueImpl <em>Unique</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.UniqueImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getUnique()
         * @generated
         */
        EClass UNIQUE = eINSTANCE.getUnique();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute UNIQUE__NAME = eINSTANCE.getUnique_Name();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.BookShelfImpl <em>Book Shelf</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.BookShelfImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getBookShelf()
         * @generated
         */
        EClass BOOK_SHELF = eINSTANCE.getBookShelf();

        /**
         * The meta object literal for the '<em><b>Uniques</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOOK_SHELF__UNIQUES = eINSTANCE.getBookShelf_Uniques();

        /**
         * The meta object literal for the '<em><b>Authors</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOOK_SHELF__AUTHORS = eINSTANCE.getBookShelf_Authors();

        /**
         * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOOK_SHELF__CATEGORIES = eINSTANCE.getBookShelf_Categories();

        /**
         * The meta object literal for the '<em><b>Reading Units</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BOOK_SHELF__READING_UNITS = eINSTANCE.getBookShelf_ReadingUnits();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.ArchiveFileImpl <em>Archive File</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.ArchiveFileImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getArchiveFile()
         * @generated
         */
        EClass ARCHIVE_FILE = eINSTANCE.getArchiveFile();

        /**
         * The meta object literal for the '<em><b>Archive File</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ARCHIVE_FILE__ARCHIVE_FILE = eINSTANCE.getArchiveFile_ArchiveFile();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.IndexFileFolderImpl <em>Index File Folder</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.IndexFileFolderImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getIndexFileFolder()
         * @generated
         */
        EClass INDEX_FILE_FOLDER = eINSTANCE.getIndexFileFolder();

        /**
         * The meta object literal for the '<em><b>Index File</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INDEX_FILE_FOLDER__INDEX_FILE = eINSTANCE.getIndexFileFolder_IndexFile();

        /**
         * The meta object literal for the '<em><b>Index Folder</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INDEX_FILE_FOLDER__INDEX_FOLDER = eINSTANCE.getIndexFileFolder_IndexFolder();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.PhysicalUnitImpl <em>Physical Unit</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.PhysicalUnitImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getPhysicalUnit()
         * @generated
         */
        EClass PHYSICAL_UNIT = eINSTANCE.getPhysicalUnit();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.SingleFileImpl <em>Single File</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.SingleFileImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getSingleFile()
         * @generated
         */
        EClass SINGLE_FILE = eINSTANCE.getSingleFile();

        /**
         * The meta object literal for the '<em><b>File</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SINGLE_FILE__FILE = eINSTANCE.getSingleFile_File();

        /**
         * The meta object literal for the '{@link org.jbookshelf.impl.SingleFileFolderImpl <em>Single File Folder</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.jbookshelf.impl.SingleFileFolderImpl
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getSingleFileFolder()
         * @generated
         */
        EClass SINGLE_FILE_FOLDER = eINSTANCE.getSingleFileFolder();

        /**
         * The meta object literal for the '<em><b>Folder</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SINGLE_FILE_FOLDER__FOLDER = eINSTANCE.getSingleFileFolder_Folder();

        /**
         * The meta object literal for the '<em><b>Single File</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SINGLE_FILE_FOLDER__SINGLE_FILE = eINSTANCE.getSingleFileFolder_SingleFile();

        /**
         * The meta object literal for the '<em>Date</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.sql.Date
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getDate()
         * @generated
         */
        EDataType DATE = eINSTANCE.getDate();

        /**
         * The meta object literal for the '<em>File</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.io.File
         * @see org.jbookshelf.impl.JbookshelfPackageImpl#getFile()
         * @generated
         */
        EDataType FILE = eINSTANCE.getFile();

    }

} //JbookshelfPackage
