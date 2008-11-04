/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import java.io.File;

import junit.textui.TestRunner;

import org.jbookshelf.ArchiveFile;
import org.jbookshelf.JbookshelfFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Archive File</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ArchiveFileTest
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
        TestRunner.run( ArchiveFileTest.class );
    }

    /**
     * Constructs a new Archive File test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ArchiveFileTest(
        String name )
    {
        super( name );
    }

    /**
     * Returns the fixture for this Archive File test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected ArchiveFile getFixture()
    {
        return (ArchiveFile) fixture;
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
        setFixture( JbookshelfFactory.eINSTANCE.createArchiveFile() );
        getFixture().setArchiveFile( new File( "test.zip" ) );
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

} // ArchiveFileTest
