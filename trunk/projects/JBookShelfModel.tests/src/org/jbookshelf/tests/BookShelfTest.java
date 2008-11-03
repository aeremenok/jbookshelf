/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.jbookshelf.Author;
import org.jbookshelf.BookShelf;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Book Shelf</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.jbookshelf.BookShelf#addAuthor(java.lang.String) <em>Add Author</em>}</li>
 *   <li>{@link org.jbookshelf.BookShelf#addCategory(java.lang.String) <em>Add Category</em>}</li>
 *   <li>{@link org.jbookshelf.BookShelf#addReadingUnit(java.lang.String, org.jbookshelf.Author, org.jbookshelf.Category, org.jbookshelf.PhysicalUnit) <em>Add Reading Unit</em>}</li>
 *   <li>{@link org.jbookshelf.BookShelf#queryAuthors(java.lang.String) <em>Query Authors</em>}</li>
 *   <li>{@link org.jbookshelf.BookShelf#queryCategories(java.lang.String) <em>Query Categories</em>}</li>
 *   <li>{@link org.jbookshelf.BookShelf#queryUniques(java.lang.String) <em>Query Uniques</em>}</li>
 *   <li>{@link org.jbookshelf.BookShelf#queryUnits(java.lang.String, java.lang.Boolean) <em>Query Units</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class BookShelfTest
    extends TestCase
{
    /**
     * The fixture for this Book Shelf test case.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BookShelf fixture = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run(BookShelfTest.class);
    }

    /**
     * Constructs a new Book Shelf test case with the given name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BookShelfTest(
        String name )
    {
        super(name);
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#addAuthor(java.lang.String) <em>Add Author</em>}' operation. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelf#addAuthor(java.lang.String)
     * @generated NOT
     */
    public void testAddAuthor__String()
    {
        String name = getName();
        Author author = getFixture().addAuthor( name );
        Assert.assertNotNull( author );
        Assert.assertEquals( author.getName(), name );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#addCategory(java.lang.String) <em>Add Category</em>}' operation. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelf#addCategory(java.lang.String)
     * @generated NOT
     */
    public void testAddCategory__String()
    {
        String name = getName();
        Category category = getFixture().addCategory( name );
        Assert.assertNotNull( category );
        Assert.assertEquals( category.getName(), name );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#addReadingUnit(java.lang.String, org.jbookshelf.Author, org.jbookshelf.Category, org.jbookshelf.PhysicalUnit) <em>Add Reading Unit</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.jbookshelf.BookShelf#addReadingUnit(java.lang.String, org.jbookshelf.Author, org.jbookshelf.Category, org.jbookshelf.PhysicalUnit)
     * @generated
     */
    public void testAddReadingUnit__String_Author_Category_PhysicalUnit()
    {
        // TODO: implement this operation test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryAuthors(java.lang.String) <em>Query Authors</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.jbookshelf.BookShelf#queryAuthors(java.lang.String)
     * @generated
     */
    public void testQueryAuthors__String()
    {
        // TODO: implement this operation test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryCategories(java.lang.String) <em>Query Categories</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.jbookshelf.BookShelf#queryCategories(java.lang.String)
     * @generated
     */
    public void testQueryCategories__String()
    {
        // TODO: implement this operation test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryUniques(java.lang.String) <em>Query Uniques</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.jbookshelf.BookShelf#queryUniques(java.lang.String)
     * @generated
     */
    public void testQueryUniques__String()
    {
        // TODO: implement this operation test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryUnits(java.lang.String, java.lang.Boolean) <em>Query Units</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.jbookshelf.BookShelf#queryUnits(java.lang.String, java.lang.Boolean)
     * @generated
     */
    public void testQueryUnits__String_Boolean()
    {
        // TODO: implement this operation test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

    /**
     * Returns the fixture for this Book Shelf test case.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BookShelf getFixture()
    {
        return fixture;
    }

    /**
     * Sets the fixture for this Book Shelf test case.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(
        BookShelf fixture )
    {
        this.fixture = fixture;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp()
        throws Exception
    {
        setFixture(JbookshelfFactory.eINSTANCE.createBookShelf());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown()
        throws Exception
    {
        setFixture(null);
    }

} // BookShelfTest
