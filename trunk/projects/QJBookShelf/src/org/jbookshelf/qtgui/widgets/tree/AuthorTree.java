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
package org.jbookshelf.qtgui.widgets.tree;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.ModelPackage;

public class AuthorTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        // DefaultMutableTreeNode books = new DefaultMutableTreeNode( "Books" );
        // DefaultMutableTreeNode categories = new DefaultMutableTreeNode( "Categories" );
        // Author author = (Author) parent.getUnique();
        //
        // for ( ReadingUnit book : author.getReadingUnits() )
        // {
        // books.add( new UniqueNode( book ) );
        // }
        // for ( Category category : author.getCategories() )
        // {
        // categories.add( new UniqueNode( category ) );
        // }
        //
        // if ( books.getChildCount() > 0 )
        // {
        // parent.add( books );
        // }
        // if ( categories.getChildCount() > 0 )
        // {
        // parent.add( categories );
        // }
    }

    @Override
    protected EReference getReference()
    {
        return ModelPackage.eINSTANCE.getBookShelf_Authors();
    }

}
