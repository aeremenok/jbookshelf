/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.sql.Date;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.jbookshelf.Author;
import org.jbookshelf.BookShelf;
import org.jbookshelf.Categorizable;
import org.jbookshelf.Category;
import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.ReadingUnit;
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
    private EDataType dateEDataType = null;

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
    public EClass getBookShelf()
    {
        return bookShelfEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBookShelf_Uniques()
    {
        return (EReference)bookShelfEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getDate()
    {
        return dateEDataType;
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

        categoryEClass = createEClass(CATEGORY);
        createEAttribute(categoryEClass, CATEGORY__DESCRIPTION);
        createEReference(categoryEClass, CATEGORY__CATEGORIZABLES);

        commentEClass = createEClass(COMMENT);
        createEAttribute(commentEClass, COMMENT__CONTENT);
        createEAttribute(commentEClass, COMMENT__CREATION_DATE);
        createEReference(commentEClass, COMMENT__SUBJECT);

        commentableEClass = createEClass(COMMENTABLE);
        createEReference(commentableEClass, COMMENTABLE__COMMENTS);

        categorizableEClass = createEClass(CATEGORIZABLE);
        createEReference(categorizableEClass, CATEGORIZABLE__CATEGORIES);

        uniqueEClass = createEClass(UNIQUE);
        createEAttribute(uniqueEClass, UNIQUE__NAME);

        bookShelfEClass = createEClass(BOOK_SHELF);
        createEReference(bookShelfEClass, BOOK_SHELF__UNIQUES);

        // Create data types
        dateEDataType = createEDataType(DATE);
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
        authorEClass.getESuperTypes().add(this.getCommentable());
        authorEClass.getESuperTypes().add(this.getCategorizable());
        authorEClass.getESuperTypes().add(theEcorePackage.getEObject());
        readingUnitEClass.getESuperTypes().add(this.getUnique());
        readingUnitEClass.getESuperTypes().add(this.getCommentable());
        readingUnitEClass.getESuperTypes().add(this.getCategorizable());
        readingUnitEClass.getESuperTypes().add(theEcorePackage.getEObject());
        categoryEClass.getESuperTypes().add(this.getUnique());
        categoryEClass.getESuperTypes().add(theEcorePackage.getEObject());

        // Initialize classes and features; add operations and parameters
        initEClass(authorEClass, Author.class, "Author", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAuthor_ReadingUnits(), this.getReadingUnit(), null, "readingUnits", null, 0, -1, Author.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(readingUnitEClass, ReadingUnit.class, "ReadingUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getReadingUnit_Authors(), this.getAuthor(), null, "authors", null, 0, -1, ReadingUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCategory_Description(), ecorePackage.getEString(), "description", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCategory_Categorizables(), this.getCategorizable(), null, "categorizables", null, 0, -1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComment_Content(), ecorePackage.getEString(), "content", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComment_CreationDate(), this.getDate(), "creationDate", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComment_Subject(), this.getCommentable(), null, "subject", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(commentableEClass, Commentable.class, "Commentable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCommentable_Comments(), this.getComment(), null, "comments", null, 0, -1, Commentable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(categorizableEClass, Categorizable.class, "Categorizable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCategorizable_Categories(), this.getCategory(), null, "categories", null, 0, -1, Categorizable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(uniqueEClass, Unique.class, "Unique", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUnique_Name(), ecorePackage.getEString(), "name", null, 0, 1, Unique.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bookShelfEClass, BookShelf.class, "BookShelf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBookShelf_Uniques(), this.getUnique(), null, "uniques", null, 0, -1, BookShelf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize data types
        initEDataType(dateEDataType, Date.class, "Date", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //JbookshelfPackageImpl
