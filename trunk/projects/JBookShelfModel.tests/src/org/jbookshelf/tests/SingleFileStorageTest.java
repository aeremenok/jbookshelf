/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import java.io.File;

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
        int oldSize = getFixture().getBookShelf().getUniques().size();
        getFixture().backupCollection( new File( "" ) );
        int newSize = getFixture().getBookShelf().getUniques().size();
        Assert.assertTrue( oldSize == newSize );
        // todo check file size
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
        int oldSize = getFixture().getBookShelf().getUniques().size();
        getFixture().restoreCollection( new File( "" ) );
        int newSize = getFixture().getBookShelf().getUniques().size();
        Assert.assertTrue( oldSize != newSize );
        // todo ?
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
     * @generated
     */
    @Override
    protected void setUp()
        throws Exception
    {
        setFixture( JbookshelfFactory.eINSTANCE.createSingleFileStorage() );
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
