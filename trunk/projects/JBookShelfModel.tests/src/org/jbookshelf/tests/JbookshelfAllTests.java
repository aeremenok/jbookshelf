/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test suite for the '<em><b>Jbookshelf</b></em>' model. <!-- end-user-doc -->
 * 
 * @generated
 */
public class JbookshelfAllTests
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
        TestSuite suite = new JbookshelfAllTests( "Jbookshelf Tests" );
        suite.addTest( JbookshelfTests.suite() );
        return suite;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public JbookshelfAllTests(
        String name )
    {
        super( name );
    }

} // JbookshelfAllTests
