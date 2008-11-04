/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test suite for the '<em><b>jbookshelf</b></em>' package. <!-- end-user-doc -->
 * 
 * @generated
 */
public class JbookshelfTests
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
        TestSuite suite = new JbookshelfTests( "jbookshelf Tests" );
        suite.addTestSuite( AuthorTest.class );
        suite.addTestSuite( ReadingUnitTest.class );
        // suite.addTestSuite(CategoryTest.class);
        suite.addTestSuite( BookShelfTest.class );
        suite.addTestSuite( ArchiveFileTest.class );
        suite.addTestSuite( IndexFileFolderTest.class );
        suite.addTestSuite( SingleFileTest.class );
        suite.addTestSuite( SingleFileFolderTest.class );
        suite.addTestSuite( SingleFileStorageTest.class );
        return suite;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public JbookshelfTests(
        String name )
    {
        super( name );
    }

} // JbookshelfTests
