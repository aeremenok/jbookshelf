/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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

import java.io.File;

import junit.textui.TestRunner;

import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.SingleFileFolder;

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
        setFixture( ModelFactory.eINSTANCE.createSingleFileFolder() );
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
