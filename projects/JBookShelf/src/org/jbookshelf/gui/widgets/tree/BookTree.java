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
package org.jbookshelf.gui.widgets.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.ReadingUnit;

public class BookTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        DefaultMutableTreeNode authors = new DefaultMutableTreeNode( "Authors" );
        DefaultMutableTreeNode categories = new DefaultMutableTreeNode( "Categories" );
        ReadingUnit readingUnit = (ReadingUnit) parent.getUnique();

        for ( Category category : readingUnit.getCategories() )
        {
            categories.add( new UniqueNode( category ) );
        }
        for ( Author author : readingUnit.getAuthors() )
        {
            authors.add( new UniqueNode( author ) );
        }

        if ( authors.getChildCount() > 0 )
        {
            parent.add( authors );
        }
        if ( categories.getChildCount() > 0 )
        {
            parent.add( categories );
        }
    }

    @Override
    protected EReference getReference()
    {
        return ModelPackage.eINSTANCE.getBookShelf_ReadingUnits();
    }

}
