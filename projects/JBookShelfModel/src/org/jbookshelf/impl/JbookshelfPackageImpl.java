/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.io.File;
import java.sql.Date;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.jbookshelf.ArchiveFile;
import org.jbookshelf.Author;
import org.jbookshelf.BookShelf;
import org.jbookshelf.BookShelfStorage;
import org.jbookshelf.Categorizable;
import org.jbookshelf.Category;
import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.IndexFileFolder;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.SingleFile;
import org.jbookshelf.SingleFileFolder;
import org.jbookshelf.SingleFileStorage;
import org.jbookshelf.Unique;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JbookshelfPackageImpl extends EPackageImpl implements JbookshelfPackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass authorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass readingUnitEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass categoryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass commentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass commentableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass categorizableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass uniqueEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bookShelfEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass archiveFileEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass indexFileFolderEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass physicalUnitEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass singleFileEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass singleFileFolderEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bookShelfStorageEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass singleFileStorageEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType fileEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.jbookshelf.JbookshelfPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private JbookshelfPackageImpl()
    {
        super(eNS_URI, JbookshelfFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static JbookshelfPackage init()
    {
        if (isInited) return (JbookshelfPackage)EPackage.Registry.INSTANCE.getEPackage(JbookshelfPackage.eNS_URI);

        // Obtain or create and register package
        JbookshelfPackageImpl theJbookshelfPackage = (JbookshelfPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof JbookshelfPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new JbookshelfPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theJbookshelfPackage.createPackageContents();

        // Initialize created meta-data
        theJbookshelfPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theJbookshelfPackage.freeze();

        return theJbookshelfPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAuthor()
    {
        return authorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAuthor_ReadingUnits()
    {
        return (EReference)authorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getReadingUnit()
    {
        return readingUnitEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getReadingUnit_Authors()
    {
        return (EReference)readingUnitEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getReadingUnit_Physical()
    {
        return (EReference)readingUnitEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getReadingUnit_Read()
    {
        return (EAttribute)readingUnitEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCategory()
    {
        return categoryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCategory_Description()
    {
        return (EAttribute)categoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCategory_Categorizables()
    {
        return (EReference)categoryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComment()
    {
        return commentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComment_Content()
    {
        return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComment_CreationDate()
    {
        return (EAttribute)commentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComment_Subject()
    {
        return (EReference)commentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComment_Title()
    {
        return (EAttribute)commentEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCommentable()
    {
        return commentableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCommentable_Comments()
    {
        return (EReference)commentableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCategorizable()
    {
        return categorizableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCategorizable_Categories()
    {
        return (EReference)categorizableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUnique()
    {
        return uniqueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUnique_Name()
    {
        return (EAttribute)uniqueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUnique_Related()
    {
        return (EReference)uniqueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBookShelf()
    {
        return bookShelfEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBookShelf_Authors()
    {
        return (EReference)bookShelfEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBookShelf_Categories()
    {
        return (EReference)bookShelfEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBookShelf_ReadingUnits()
    {
        return (EReference)bookShelfEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBookShelf_Storage()
    {
        return (EReference)bookShelfEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArchiveFile()
    {
        return archiveFileEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getArchiveFile_ArchiveFile()
    {
        return (EAttribute)archiveFileEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIndexFileFolder()
    {
        return indexFileFolderEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIndexFileFolder_IndexFile()
    {
        return (EAttribute)indexFileFolderEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIndexFileFolder_IndexFolder()
    {
        return (EAttribute)indexFileFolderEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPhysicalUnit()
    {
        return physicalUnitEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSingleFile()
    {
        return singleFileEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSingleFile_File()
    {
        return (EAttribute)singleFileEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSingleFileFolder()
    {
        return singleFileFolderEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSingleFileFolder_Folder()
    {
        return (EAttribute)singleFileFolderEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSingleFileFolder_SingleFile()
    {
        return (EAttribute)singleFileFolderEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBookShelfStorage()
    {
        return bookShelfStorageEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBookShelfStorage_BookShelf()
    {
        return (EReference)bookShelfStorageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSingleFileStorage()
    {
        return singleFileStorageEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSingleFileStorage_CollectionStorageFile()
    {
        return (EAttribute)singleFileStorageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getFile()
    {
        return fileEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public JbookshelfFactory getJbookshelfFactory()
    {
        return (JbookshelfFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents()
    {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        authorEClass = createEClass(AUTHOR);
        createEReference(authorEClass, AUTHOR__READING_UNITS);

        readingUnitEClass = createEClass(READING_UNIT);
        createEReference(readingUnitEClass, READING_UNIT__AUTHORS);
        createEReference(readingUnitEClass, READING_UNIT__PHYSICAL);
        createEAttribute(readingUnitEClass, READING_UNIT__READ);

        categoryEClass = createEClass(CATEGORY);
        createEAttribute(categoryEClass, CATEGORY__DESCRIPTION);
        createEReference(categoryEClass, CATEGORY__CATEGORIZABLES);

        commentEClass = createEClass(COMMENT);
        createEAttribute(commentEClass, COMMENT__CONTENT);
        createEAttribute(commentEClass, COMMENT__CREATION_DATE);
        createEReference(commentEClass, COMMENT__SUBJECT);
        createEAttribute(commentEClass, COMMENT__TITLE);

        commentableEClass = createEClass(COMMENTABLE);
        createEReference(commentableEClass, COMMENTABLE__COMMENTS);

        categorizableEClass = createEClass(CATEGORIZABLE);
        createEReference(categorizableEClass, CATEGORIZABLE__CATEGORIES);

        uniqueEClass = createEClass(UNIQUE);
        createEAttribute(uniqueEClass, UNIQUE__NAME);
        createEReference(uniqueEClass, UNIQUE__RELATED);

        bookShelfEClass = createEClass(BOOK_SHELF);
        createEReference(bookShelfEClass, BOOK_SHELF__AUTHORS);
        createEReference(bookShelfEClass, BOOK_SHELF__CATEGORIES);
        createEReference(bookShelfEClass, BOOK_SHELF__READING_UNITS);
        createEReference(bookShelfEClass, BOOK_SHELF__STORAGE);

        archiveFileEClass = createEClass(ARCHIVE_FILE);
        createEAttribute(archiveFileEClass, ARCHIVE_FILE__ARCHIVE_FILE);

        indexFileFolderEClass = createEClass(INDEX_FILE_FOLDER);
        createEAttribute(indexFileFolderEClass, INDEX_FILE_FOLDER__INDEX_FILE);
        createEAttribute(indexFileFolderEClass, INDEX_FILE_FOLDER__INDEX_FOLDER);

        physicalUnitEClass = createEClass(PHYSICAL_UNIT);

        singleFileEClass = createEClass(SINGLE_FILE);
        createEAttribute(singleFileEClass, SINGLE_FILE__FILE);

        singleFileFolderEClass = createEClass(SINGLE_FILE_FOLDER);
        createEAttribute(singleFileFolderEClass, SINGLE_FILE_FOLDER__FOLDER);
        createEAttribute(singleFileFolderEClass, SINGLE_FILE_FOLDER__SINGLE_FILE);

        bookShelfStorageEClass = createEClass(BOOK_SHELF_STORAGE);
        createEReference(bookShelfStorageEClass, BOOK_SHELF_STORAGE__BOOK_SHELF);

        singleFileStorageEClass = createEClass(SINGLE_FILE_STORAGE);
        createEAttribute(singleFileStorageEClass, SINGLE_FILE_STORAGE__COLLECTION_STORAGE_FILE);

        // Create data types
        fileEDataType = createEDataType(FILE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents()
    {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        authorEClass.getESuperTypes().add(this.getUnique());
        authorEClass.getESuperTypes().add(this.getCategorizable());
        authorEClass.getESuperTypes().add(theEcorePackage.getEObject());
        readingUnitEClass.getESuperTypes().add(this.getUnique());
        readingUnitEClass.getESuperTypes().add(this.getCategorizable());
        readingUnitEClass.getESuperTypes().add(theEcorePackage.getEObject());
        categoryEClass.getESuperTypes().add(this.getUnique());
        categoryEClass.getESuperTypes().add(theEcorePackage.getEObject());
        uniqueEClass.getESuperTypes().add(this.getCommentable());
        uniqueEClass.getESuperTypes().add(theEcorePackage.getEObject());
        archiveFileEClass.getESuperTypes().add(this.getPhysicalUnit());
        indexFileFolderEClass.getESuperTypes().add(this.getPhysicalUnit());
        singleFileEClass.getESuperTypes().add(this.getPhysicalUnit());
        singleFileFolderEClass.getESuperTypes().add(this.getPhysicalUnit());
        singleFileStorageEClass.getESuperTypes().add(this.getBookShelfStorage());

        // Initialize classes and features; add operations and parameters
        initEClass(authorEClass, Author.class, "Author", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAuthor_ReadingUnits(), this.getReadingUnit(), this.getReadingUnit_Authors(), "readingUnits", null, 0, -1, Author.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(readingUnitEClass, ReadingUnit.class, "ReadingUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getReadingUnit_Authors(), this.getAuthor(), this.getAuthor_ReadingUnits(), "authors", null, 0, -1, ReadingUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReadingUnit_Physical(), this.getPhysicalUnit(), null, "physical", null, 1, 1, ReadingUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getReadingUnit_Read(), ecorePackage.getEBoolean(), "read", "false", 1, 1, ReadingUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCategory_Description(), ecorePackage.getEString(), "description", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCategory_Categorizables(), this.getCategorizable(), this.getCategorizable_Categories(), "categorizables", null, 0, -1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComment_Content(), ecorePackage.getEString(), "content", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComment_CreationDate(), ecorePackage.getEDate(), "creationDate", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComment_Subject(), this.getCommentable(), this.getCommentable_Comments(), "subject", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComment_Title(), ecorePackage.getEString(), "title", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(commentableEClass, Commentable.class, "Commentable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCommentable_Comments(), this.getComment(), this.getComment_Subject(), "comments", null, 0, -1, Commentable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(commentableEClass, this.getComment(), "queryComments", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(categorizableEClass, Categorizable.class, "Categorizable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCategorizable_Categories(), this.getCategory(), this.getCategory_Categorizables(), "categories", null, 0, -1, Categorizable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(uniqueEClass, Unique.class, "Unique", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUnique_Name(), ecorePackage.getEString(), "name", null, 1, 1, Unique.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUnique_Related(), this.getUnique(), null, "related", null, 0, -1, Unique.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bookShelfEClass, BookShelf.class, "BookShelf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBookShelf_Authors(), this.getAuthor(), null, "authors", null, 0, -1, BookShelf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBookShelf_Categories(), this.getCategory(), null, "categories", null, 0, -1, BookShelf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBookShelf_ReadingUnits(), this.getReadingUnit(), null, "readingUnits", null, 0, -1, BookShelf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBookShelf_Storage(), this.getBookShelfStorage(), this.getBookShelfStorage_BookShelf(), "storage", null, 1, 1, BookShelf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(bookShelfEClass, this.getAuthor(), "addAuthor", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(bookShelfEClass, this.getCategory(), "addCategory", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(bookShelfEClass, this.getReadingUnit(), "addReadingUnit", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getAuthor(), "author", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getCategory(), "category", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getPhysicalUnit(), "physicalUnit", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(bookShelfEClass, this.getAuthor(), "queryAuthors", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(bookShelfEClass, this.getCategory(), "queryCategories", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(bookShelfEClass, this.getUnique(), "queryUniques", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(bookShelfEClass, this.getReadingUnit(), "queryUnits", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEBooleanObject(), "isRead", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(archiveFileEClass, ArchiveFile.class, "ArchiveFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getArchiveFile_ArchiveFile(), this.getFile(), "archiveFile", null, 0, 1, ArchiveFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(indexFileFolderEClass, IndexFileFolder.class, "IndexFileFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIndexFileFolder_IndexFile(), this.getFile(), "indexFile", null, 0, 1, IndexFileFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIndexFileFolder_IndexFolder(), this.getFile(), "indexFolder", null, 0, 1, IndexFileFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(physicalUnitEClass, PhysicalUnit.class, "PhysicalUnit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(physicalUnitEClass, null, "openUnit", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(singleFileEClass, SingleFile.class, "SingleFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSingleFile_File(), this.getFile(), "file", null, 0, 1, SingleFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(singleFileFolderEClass, SingleFileFolder.class, "SingleFileFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSingleFileFolder_Folder(), this.getFile(), "folder", null, 0, 1, SingleFileFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSingleFileFolder_SingleFile(), this.getFile(), "singleFile", null, 0, 1, SingleFileFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(singleFileFolderEClass, null, "openFolder", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(bookShelfStorageEClass, BookShelfStorage.class, "BookShelfStorage", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBookShelfStorage_BookShelf(), this.getBookShelf(), this.getBookShelf_Storage(), "bookShelf", null, 1, 1, BookShelfStorage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(bookShelfStorageEClass, null, "loadCollection", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(bookShelfStorageEClass, null, "saveCollection", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(singleFileStorageEClass, SingleFileStorage.class, "SingleFileStorage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSingleFileStorage_CollectionStorageFile(), this.getFile(), "collectionStorageFile", null, 0, 1, SingleFileStorage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(singleFileStorageEClass, null, "backupCollection", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getFile(), "externalFile", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(singleFileStorageEClass, null, "restoreCollection", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getFile(), "externalFile", 0, 1, IS_UNIQUE, IS_ORDERED);

        // Initialize data types
        initEDataType(fileEDataType, File.class, "File", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //JbookshelfPackageImpl
