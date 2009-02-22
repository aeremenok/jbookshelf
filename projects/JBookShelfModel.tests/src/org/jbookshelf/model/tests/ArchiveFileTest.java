/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.tests;

import junit.textui.TestRunner;

import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.ModelFactory;

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
    {
        setFixture( ModelFactory.eINSTANCE.createArchiveFile() );
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

} // ArchiveFileTest
