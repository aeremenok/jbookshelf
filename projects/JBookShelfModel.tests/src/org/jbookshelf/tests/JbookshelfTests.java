/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
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
        suite.addTestSuite( CategoryTest.class );
        suite.addTestSuite( BookShelfTest.class );
        suite.addTestSuite( ArchiveFileTest.class );
        suite.addTestSuite( IndexFileFolderTest.class );
        suite.addTestSuite( SingleFileTest.class );
        suite.addTestSuite( SingleFileFolderTest.class );
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
