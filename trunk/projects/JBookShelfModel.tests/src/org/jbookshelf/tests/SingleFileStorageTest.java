/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import junit.textui.TestRunner;

import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.SingleFileStorage;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Single File Storage</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.jbookshelf.SingleFileStorage#backupCollection(java.io.File) <em>Backup Collection</em>}</li>
 * <li>{@link org.jbookshelf.SingleFileStorage#restoreCollection(java.io.File) <em>Restore Collection</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SingleFileStorageTest
    extends BookShelfStorageTest
{

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run( SingleFileStorageTest.class );
    }

    /**
     * Constructs a new Single File Storage test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SingleFileStorageTest(
        String name )
    {
        super( name );
    }

    /**
     * Tests the '{@link org.jbookshelf.SingleFileStorage#backupCollection(java.io.File) <em>Backup Collection</em>}'
     * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.SingleFileStorage#backupCollection(java.io.File)
     * @generated NOT
     */
    public void testBackupCollection__File()
    {
        int oldSize = getFixture().getBookShelf().getReadingUnits().size();
        File externalFile = new File( "test" );
        if ( externalFile.exists() )
        {
            externalFile.delete();
            try
            {
                externalFile.createNewFile();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                fail( e.getMessage() );
            }
        }
        long oldLength = externalFile.length();
        getFixture().backupCollection( externalFile );
        int newSize = getFixture().getBookShelf().getReadingUnits().size();
        long newLength = externalFile.length();
        Assert.assertTrue( oldSize == newSize );
        Assert.assertTrue( newLength > oldLength );
    }

    /**
     * Tests the '{@link org.jbookshelf.SingleFileStorage#restoreCollection(java.io.File) <em>Restore Collection</em>}'
     * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.SingleFileStorage#restoreCollection(java.io.File)
     * @generated NOT
     */
    public void testRestoreCollection__File()
    {
        int oldSize = getFixture().getBookShelf().getReadingUnits().size();
        File externalFile = new File( "test" );
        Assert.assertTrue( externalFile.exists() );
        long oldLength = externalFile.length();
        getFixture().restoreCollection( externalFile );
        int newSize = getFixture().getBookShelf().getReadingUnits().size();
        long newLength = externalFile.length();
        Assert.assertTrue( oldSize <= newSize );
        Assert.assertTrue( oldLength == newLength );
    }

    /**
     * Returns the fixture for this Single File Storage test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected SingleFileStorage getFixture()
    {
        return (SingleFileStorage) fixture;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#setUp()
     * @generated NOT
     */
    @Override
    protected void setUp()
        throws Exception
    {
        setFixture( JbookshelfFactory.eINSTANCE.createSingleFileStorage() );
        getFixture().setBookShelf( JbookshelfFactory.eINSTANCE.createBookShelf() );
        getFixture().setCollectionStorageFile( new File( "test" ) );
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

} // SingleFileStorageTest
