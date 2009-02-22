/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test suite for the '<em><b>model</b></em>' package. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ModelTests
    extends TestSuite
{

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run( suite() );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Test suite()
    {
        TestSuite suite = new ModelTests( "model Tests" );
        suite.addTestSuite( ArchiveFileTest.class );
        suite.addTestSuite( AuthorTest.class );
        suite.addTestSuite( BookShelfTest.class );
        suite.addTestSuite( CategoryTest.class );
        suite.addTestSuite( IndexFileFolderTest.class );
        suite.addTestSuite( BookTest.class );
        suite.addTestSuite( SingleFileTest.class );
        suite.addTestSuite( SingleFileFolderTest.class );
        return suite;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ModelTests(
        String name )
    {
        super( name );
    }

} // ModelTests
