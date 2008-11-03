/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import junit.textui.TestRunner;

import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.SingleFile;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Single File</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class SingleFileTest
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
        TestRunner.run( SingleFileTest.class );
    }

    /**
     * Constructs a new Single File test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SingleFileTest(
        String name )
    {
        super( name );
    }

    /**
     * Returns the fixture for this Single File test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected SingleFile getFixture()
    {
        return (SingleFile) fixture;
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
        setFixture( JbookshelfFactory.eINSTANCE.createSingleFile() );
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

} // SingleFileTest
