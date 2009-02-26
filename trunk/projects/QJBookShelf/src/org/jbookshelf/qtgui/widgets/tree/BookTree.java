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
 * Displays {@link ReadingUnit}
 * 
 * @author eav
 */
public class BookTree
    extends CollectionTree
{
    public BookTree()
    {
        super();
        setColumnCount( 3 );

        List<String> list = new ArrayList<String>();
        list.add( tr( "Name" ) );
        list.add( tr( "Author" ) );
        list.add( tr( "Category" ) );
        setHeaderLabels( list );
        setColumnWidth( 0, 300 );
        setColumnWidth( 1, 100 );
        setColumnWidth( 2, 40 );
    }

    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        QTreeWidgetItem authors = new QTreeWidgetItem();
        authors.setText( 0, tr( "Authors" ) );
        QTreeWidgetItem categories = new QTreeWidgetItem();
        categories.setText( 0, tr( "Categories" ) );

        Book readingUnit = (Book) parent.getUnique();

        for ( Category category : readingUnit.getCategories() )
        {
            categories.addChild( new UniqueNode( category ) );
        }
        for ( Author author : readingUnit.getAuthors() )
        {
            authors.addChild( new UniqueNode( author ) );
        }

        if ( authors.childCount() > 0 )
        {
            parent.addChild( authors );
        }
        if ( categories.childCount() > 0 )
        {
            parent.addChild( categories );
        }
    }

    @Override
    protected UniqueNode createNode(
        Unique unique )
    {
        UniqueNode node = super.createNode( unique );
        Book book = (Book) unique;
        List<Category> categories = book.getCategories();
        if ( categories.size() > 0 )
        {
            node.setText( 2, categories.get( 0 ).getName() );
        }
        List<Author> authors = book.getAuthors();
        if ( authors.size() > 0 )
        {
            node.setText( 1, authors.get( 0 ).getName() );
        }
        return node;
    }

    @Override
    protected EReference getReference()
    {
        return ModelPackage.eINSTANCE.getBookShelf_ReadingUnits();
    }

}
