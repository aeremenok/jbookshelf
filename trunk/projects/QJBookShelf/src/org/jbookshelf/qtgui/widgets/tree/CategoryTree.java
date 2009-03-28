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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.Categorizable;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;

import com.trolltech.qt.gui.QTreeWidgetItem;

/**
 * Displays {@link Category}'s
 * 
 * @author eav
 */
public class CategoryTree
    extends CollectionTree
{

    public CategoryTree()
    {
        super();
        List<String> list = new ArrayList<String>();
        list.add( tr( "Name" ) );
        list.add( tr( "Count" ) );
        setHeaderLabels( list );

        setColumnWidth( 0, 440 );
        setColumnWidth( 1, 15 );
    }

    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        Category category = (Category) parent.getUnique();

        QTreeWidgetItem authors = new QTreeWidgetItem();
        authors.setText( 0, tr( "Authors" ) );
        QTreeWidgetItem books = new QTreeWidgetItem();
        books.setText( 0, tr( "Books" ) );

        for ( Categorizable categorizable : category.getCategorizables() )
        {
            UniqueNode node = new UniqueNode( categorizable );
            if ( categorizable instanceof Book )
            {
                books.addChild( node );
            }
            else
            {
                authors.addChild( node );
            }
        }

        addToParent( parent, books );
        addToParent( parent, authors );

        for ( Category child : category.getChildren() )
        {
            parent.addChild( new UniqueNode( child ) );
        }
        // todo drag and drop nodes
    }

    @Override
    protected EReference getReference()
    {
        return ModelPackage.eINSTANCE.getBookShelf_Categories();
    }
}
