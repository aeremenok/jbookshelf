/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.tests;

import junit.textui.TestRunner;

import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.ModelFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Index File Folder</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class IndexFileFolderTest
    extends PhysicalUnitTest
{

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run( IndexFileFolderTest.class );
    }

    /**
     * Constructs a new Index File Folder test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public IndexFileFolderTest(
        String name )
    {
        super( name );
    }

    /**
     * Returns the fixture for this Index File Folder test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected IndexFileFolder getFixture()
    {
        return (IndexFileFolder) fixture;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp()
    {
        setFixture( ModelFactory.eINSTANCE.createIndexFileFolder() );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown()
    {
        setFixture( null );
    }

} // IndexFileFolderTest
