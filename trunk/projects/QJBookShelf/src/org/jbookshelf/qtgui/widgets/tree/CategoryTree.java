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
package org.jbookshelf.qtgui.widgets.tree;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.Categorizable;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.ReadingUnit;

import com.trolltech.qt.gui.QTreeWidgetItem;

/**
 * Displays {@link Category}'s
 * 
 * @author eav
 */
public class CategoryTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        QTreeWidgetItem authors = new QTreeWidgetItem();
        authors.setText( 0, tr( "Authors" ) );
        QTreeWidgetItem books = new QTreeWidgetItem();
        books.setText( 0, tr( "Books" ) );

        Category category = (Category) parent.getUnique();
        for ( Categorizable categorizable : category.getCategorizables() )
        {
            UniqueNode node = new UniqueNode( categorizable );
            if ( categorizable instanceof ReadingUnit )
            {
                books.addChild( node );
            }
            else
            {
                authors.addChild( node );
            }
        }

        if ( books.childCount() > 0 )
        {
            parent.addChild( books );
        }
        if ( authors.childCount() > 0 )
        {
            parent.addChild( authors );
        }
    }

    @Override
    protected EReference getReference()
    {
        return ModelPackage.eINSTANCE.getBookShelf_Categories();
    }
}