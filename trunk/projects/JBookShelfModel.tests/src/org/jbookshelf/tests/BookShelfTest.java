/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import java.io.File;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.EList;
import org.jbookshelf.ArchiveFile;
import org.jbookshelf.Author;
import org.jbookshelf.BookShelf;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Book Shelf</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.jbookshelf.BookShelf#addAuthor(java.lang.String) <em>Add Author</em>}</li>
 * <li>{@link org.jbookshelf.BookShelf#addCategory(java.lang.String) <em>Add Category</em>}</li>
 * <li>
 * {@link org.jbookshelf.BookShelf#addReadingUnit(java.lang.String, org.jbookshelf.Author, org.jbookshelf.Category, org.jbookshelf.PhysicalUnit)
 * <em>Add Reading Unit</em>}</li>
 * <li>{@link org.jbookshelf.BookShelf#queryAuthors(java.lang.String) <em>Query Authors</em>}</li>
 * <li>{@link org.jbookshelf.BookShelf#queryCategories(java.lang.String) <em>Query Categories</em>}</li>
 * <li>{@link org.jbookshelf.BookShelf#queryUniques(java.lang.String) <em>Query Uniques</em>}</li>
 * <li>{@link org.jbookshelf.BookShelf#queryUnits(java.lang.String, java.lang.Boolean) <em>Query Units</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BookShelfTest
    extends TestCase
{
    /**
     * The fixture for this Book Shelf test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BookShelf fixture = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run( BookShelfTest.class );
    }

    /**
     * Constructs a new Book Shelf test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BookShelfTest(
        String name )
    {
        super( name );
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
        getFixture().getAuthors().clear();

        String name = getName();
        Author author = getFixture().addAuthor( name );

        Assert.assertNotNull( author );
        Assert.assertEquals( author.getName(), name );
        Assert.assertTrue( getFixture().getAuthors().contains( author ) );
        Assert.assertEquals( getFixture().getAuthors().size(), 1 );
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
        getFixture().getCategories().clear();

        String name = getName();
        Category category = getFixture().addCategory( name );

        Assert.assertNotNull( category );
        Assert.assertEquals( category.getName(), name );
        Assert.assertTrue( getFixture().getCategories().contains( category ) );
        Assert.assertEquals( getFixture().getCategories().size(), 1 );
    }

    /**
     * Tests the '
     * {@link org.jbookshelf.BookShelf#addReadingUnit(java.lang.String, org.jbookshelf.Author, org.jbookshelf.Category, org.jbookshelf.PhysicalUnit)
     * <em>Add Reading Unit</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelf#addReadingUnit(java.lang.String, org.jbookshelf.Author, org.jbookshelf.Category,
     *      org.jbookshelf.PhysicalUnit)
     * @generated NOT
     */
    public void testAddReadingUnit__String_Author_Category_PhysicalUnit()
    {
        getFixture().getReadingUnits().clear();

        Author author = getFixture().addAuthor( "author" );
        Category category = getFixture().addCategory( "category" );
        ArchiveFile archiveFile = JbookshelfFactory.eINSTANCE.createArchiveFile();
        archiveFile.setArchiveFile( new File( "" ) );

        String name = getName();
        ReadingUnit readingUnit = getFixture().addReadingUnit( name, author, category, archiveFile );

        Assert.assertNotNull( readingUnit );
        Assert.assertTrue( getFixture().getReadingUnits().contains( readingUnit ) );
        Assert.assertEquals( getFixture().getReadingUnits().size(), 1 );
        Assert.assertEquals( readingUnit.getName(), name );
        Assert.assertTrue( readingUnit.getAuthors().contains( author ) );
        Assert.assertTrue( readingUnit.getCategories().contains( category ) );
        Assert.assertEquals( readingUnit.getComments().size(), 0 );
        Assert.assertEquals( readingUnit.getRelated().size(), 0 );
        Assert.assertEquals( readingUnit.getPhysical(), archiveFile );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryAuthors(java.lang.String) <em>Query Authors</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelf#queryAuthors(java.lang.String)
     * @generated NOT
     */
    public void testQueryAuthors__String()
    {
        getFixture().getAuthors().clear();

        Author author1 = getFixture().addAuthor( "author1" );
        Author author2 = getFixture().addAuthor( "author2" );
        Author author3 = getFixture().addAuthor( "author3" );

        EList<Author> authors1 = getFixture().queryAuthors( "author" );
        Assert.assertNotNull( authors1 );
        Assert.assertEquals( authors1.size(), 3 );
        Assert.assertTrue( authors1.contains( author1 ) );
        Assert.assertTrue( authors1.contains( author2 ) );
        Assert.assertTrue( authors1.contains( author3 ) );

        EList<Author> authors2 = getFixture().queryAuthors( "author1" );
        Assert.assertNotNull( authors2 );
        Assert.assertEquals( authors2.size(), 1 );
        Assert.assertTrue( authors2.contains( author1 ) );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryCategories(java.lang.String) <em>Query Categories</em>}'
     * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelf#queryCategories(java.lang.String)
     * @generated NOT
     */
    public void testQueryCategories__String()
    {
        getFixture().getCategories().clear();

        Category category1 = getFixture().addCategory( "category1" );
        Category category2 = getFixture().addCategory( "category2" );
        Category category3 = getFixture().addCategory( "category3" );

        EList<Category> categories1 = getFixture().queryCategories( "category" );
        Assert.assertNotNull( categories1 );
        Assert.assertEquals( categories1.size(), 3 );
        Assert.assertTrue( categories1.contains( category1 ) );
        Assert.assertTrue( categories1.contains( category2 ) );
        Assert.assertTrue( categories1.contains( category3 ) );

        EList<Category> categories2 = getFixture().queryCategories( "category1" );
        Assert.assertNotNull( categories2 );
        Assert.assertEquals( categories2.size(), 1 );
        Assert.assertTrue( categories2.contains( category1 ) );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryUniques(java.lang.String) <em>Query Uniques</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelf#queryUniques(java.lang.String)
     * @generated NOT
     */
    public void testQueryUniques__String()
    {
        getFixture().getCategories().clear();
        getFixture().getAuthors().clear();

        Category category1 = getFixture().addCategory( "category1" );
        Category category2 = getFixture().addCategory( "category2" );
        Author author1 = getFixture().addAuthor( "author1" );
        Author author2 = getFixture().addAuthor( "author2" );

        EList<Unique> uniques1 = getFixture().queryUniques( "1" );
        Assert.assertNotNull( uniques1 );
        Assert.assertTrue( uniques1.contains( author1 ) );
        Assert.assertTrue( uniques1.contains( category1 ) );
        Assert.assertFalse( uniques1.contains( author2 ) );
        Assert.assertFalse( uniques1.contains( category2 ) );
        Assert.assertEquals( uniques1.size(), 2 );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelf#queryUnits(java.lang.String, java.lang.Boolean) <em>Query Units</em>}'
     * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelf#queryUnits(java.lang.String, java.lang.Boolean)
     * @generated NOT
     */
    public void testQueryUnits__String_Boolean()
    {
        getFixture().getReadingUnits().clear();

        Author author = getFixture().addAuthor( "author" );
        Category category = getFixture().addCategory( "category" );
        ArchiveFile archiveFile = JbookshelfFactory.eINSTANCE.createArchiveFile();
        archiveFile.setArchiveFile( new File( "" ) );

        ReadingUnit book1 = getFixture().addReadingUnit( "book1", author, category, archiveFile );
        book1.setIsRead( true );
        ReadingUnit book2 = getFixture().addReadingUnit( "book2", author, category, archiveFile );
        book1.setIsRead( true );
        ReadingUnit book3 = getFixture().addReadingUnit( "book11", author, category, archiveFile );
        book1.setIsRead( false );

        EList<ReadingUnit> units1 = getFixture().queryUnits( "book", null );
        Assert.assertNotNull( units1 );
        Assert.assertTrue( units1.contains( book1 ) );
        Assert.assertTrue( units1.contains( book2 ) );
        Assert.assertTrue( units1.contains( book3 ) );
        Assert.assertEquals( units1.size(), 3 );

        EList<ReadingUnit> units2 = getFixture().queryUnits( "book", true );
        Assert.assertNotNull( units2 );
        Assert.assertTrue( units2.contains( book1 ) );
        Assert.assertTrue( units2.contains( book2 ) );
        Assert.assertFalse( units2.contains( book3 ) );
        Assert.assertEquals( units1.size(), 2 );

        EList<ReadingUnit> units3 = getFixture().queryUnits( "book1", null );
        Assert.assertNotNull( units3 );
        Assert.assertTrue( units3.contains( book1 ) );
        Assert.assertTrue( units3.contains( book3 ) );
        Assert.assertFalse( units3.contains( book2 ) );
        Assert.assertEquals( units1.size(), 2 );
    }

    /**
     * Returns the fixture for this Book Shelf test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BookShelf getFixture()
    {
        return fixture;
    }

    /**
     * Sets the fixture for this Book Shelf test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void setFixture(
        BookShelf fixture )
    {
        this.fixture = fixture;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp()
        throws Exception
    {
        setFixture( JbookshelfFactory.eINSTANCE.createBookShelf() );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown()
        throws Exception
    {
        setFixture( null );
    }

} // BookShelfTest
