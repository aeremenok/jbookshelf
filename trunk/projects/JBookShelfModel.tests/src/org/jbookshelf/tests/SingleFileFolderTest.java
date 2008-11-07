/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import java.io.File;

import junit.textui.TestRunner;

import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.SingleFileFolder;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Single File Folder</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class SingleFileFolderTest
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
        TestRunner.run( SingleFileFolderTest.class );
    }

    /**
     * Constructs a new Single File Folder test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SingleFileFolderTest(
        String name )
    {
        super( name );
    }

    /**
     * Returns the fixture for this Single File Folder test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected SingleFileFolder getFixture()
    {
        return (SingleFileFolder) fixture;
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
        setFixture( JbookshelfFactory.eINSTANCE.createSingleFileFolder() );
        File value = new File( "test" );
        getFixture().setSingleFile( value );
        getFixture().setFolder( value.getCanonicalFile().getParentFile() );
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

} // SingleFileFolderTest
