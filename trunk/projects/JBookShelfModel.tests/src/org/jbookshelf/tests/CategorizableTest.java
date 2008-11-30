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

import org.jbookshelf.model.Categorizable;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Categorizable</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class CategorizableTest
    extends CommentableTest
{
    /**
     * Constructs a new Categorizable test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CategorizableTest(
        String name )
    {
        super( name );
    }

    /**
     * Returns the fixture for this Categorizable test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected Categorizable getFixture()
    {
        return (Categorizable) fixture;
    }

} // CategorizableTest
