/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jbookshelf.BookShelfStorage;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Book Shelf Storage</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.jbookshelf.BookShelfStorage#loadCollection() <em>Load Collection</em>}</li>
 * <li>{@link org.jbookshelf.BookShelfStorage#saveCollection() <em>Save Collection</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class BookShelfStorageTest
    extends TestCase
{

    /**
     * The fixture for this Book Shelf Storage test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BookShelfStorage fixture = null;

    /**
     * Constructs a new Book Shelf Storage test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BookShelfStorageTest(
        String name )
    {
        super( name );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelfStorage#loadCollection() <em>Load Collection</em>}' operation. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelfStorage#loadCollection()
     * @generated NOT
     */
    public void testLoadCollection()
    {
        int oldSize = getFixture().getBookShelf().getReadingUnits().size();
        getFixture().loadCollection();
        int newSize = getFixture().getBookShelf().getReadingUnits().size();
        Assert.assertTrue( newSize >= oldSize );
    }

    /**
     * Tests the '{@link org.jbookshelf.BookShelfStorage#saveCollection() <em>Save Collection</em>}' operation. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.BookShelfStorage#saveCollection()
     * @generated NOT
     */
    public void testSaveCollection()
    {
        int oldSize = getFixture().getBookShelf().getReadingUnits().size();
        getFixture().saveCollection();
        int newSize = getFixture().getBookShelf().getReadingUnits().size();
        Assert.assertTrue( newSize == oldSize );
    }

    /**
     * Returns the fixture for this Book Shelf Storage test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BookShelfStorage getFixture()
    {
        return fixture;
    }

    /**
     * Sets the fixture for this Book Shelf Storage test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void setFixture(
        BookShelfStorage fixture )
    {
        this.fixture = fixture;
    }

} // BookShelfStorageTest
