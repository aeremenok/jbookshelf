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
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.Unique;

import com.trolltech.qt.gui.QTreeWidgetItem;

/**
 * Displays {@link Author}'s
 * 
 * @author eav
 */
public class AuthorTree
    extends CollectionTree
{
    @Override
    public void initSingleton()
    {
        super.initSingleton();

        setColumnCount( 2 );
        final List<String> list = new ArrayList<String>();
        list.add( tr( "Name" ) );
        list.add( tr( "Category" ) );
        setHeaderLabels( list );
        setColumnWidth( 0, 400 );
        setColumnWidth( 1, 40 );
    }

    @Override
    protected void addChildren(
        final UniqueNode parent )
    {
        final Author author = (Author) parent.getUnique();

        final QTreeWidgetItem books = new QTreeWidgetItem();
        books.setText( 0, tr( "Books" ) );
        final QTreeWidgetItem categories = new QTreeWidgetItem();
        categories.setText( 0, tr( "Categories" ) );

        for ( final Book book : author.getBooks() )
        {
            books.addChild( new UniqueNode( book ) );
        }
        for ( final Category category : author.getCategories() )
        {
            categories.addChild( new UniqueNode( category ) );
        }

        addToParent( parent, books );
        addToParent( parent, categories );
    }

    @Override
    protected UniqueNode createNode(
        final Unique unique )
    {
        final UniqueNode node = super.createNode( unique );
        final Author author = (Author) unique;
        final List<Category> categories = author.getCategories();
        if ( categories.size() > 0 )
        {
            node.setText( 1, categories.get( 0 ).getName() );
        }
        return node;
    }

    @Override
    protected EReference getReference()
    {
        return ModelPackage.eINSTANCE.getBookShelf_Authors();
    }

}
