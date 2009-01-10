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
package org.jbookshelf.qtgui.widgets.dialog;

import org.eclipse.emf.common.util.EList;
import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.jbookshelf.qtgui.widgets.panel.BookPanel;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.BookPanel.Parameters;

import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;

/**
 * Shows book data of existing {@link ReadingUnit} in the {@link BookPanel}. Then updates a {@link ReadingUnit} from the
 * {@link BookPanel}
 * 
 * @author eav
 */
public class BookEditDialog
    extends QDialogExt
{
    private QPushButton       applyButton  = new QPushButton();
    private QPushButton       cancelButton = new QPushButton();
    private QLabel            headerLabel  = new QLabel();

    private BookPanel         bookPanel    = new BookPanel();

    private final ReadingUnit book;

    public BookEditDialog(
        QWidget parent,
        ReadingUnit book )
    {
        super( parent );
        this.book = book;

        initComponents();
        initListeners();

        bookPanel.setBook( book );

        retranslate();
    }

    public void retranslate()
    {
        setWindowTitle( tr( "Edit Book" ) );

        headerLabel.setText( tr( "Edit Book Properties" ) );
        applyButton.setText( tr( "Apply" ) );
        cancelButton.setText( tr( "Cancel" ) );
    }

    public void saveBook(
        Parameters parameters )
    {
        EList<Author> authors = Storage.getBookShelf().queryAuthors( parameters.getAuthorName() );
        if ( authors.size() > 0 )
        { // todo what if we've found more than 1 author with equal names?
            book.getAuthors().set( 0, authors.get( 0 ) ); // todo edit multiple authors
        }
        else
        {
            Author author = book.getAuthors().get( 0 ); // todo edit multiple authors
            author.setName( parameters.getAuthorName() );
        }

        EList<Category> categories = Storage.getBookShelf().queryCategories( parameters.getCategoryName() );
        if ( categories.size() > 0 )
        {
            book.getCategories().set( 0, categories.get( 0 ) ); // todo edit multiple categories
        }
        else
        {
            Category category = book.getCategories().get( 0 );
            category.setName( parameters.getCategoryName() ); // todo edit multiple categories
        }
        book.setName( parameters.getBookName() );

        book.setPhysical( FileImporter.createPhysicalUnit( parameters.getFile() ) );
        book.setRead( parameters.isRead() );
    }

    private void initComponents()
    {
        setModal( true );

        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        headerLabel.setFont( new QFont( "Tahoma", 14 ) );

        layout.addWidget( headerLabel, 0, 0, 1, 2 );
        layout.addWidget( bookPanel, 1, 0, 1, 2 );
        layout.addWidget( applyButton, 2, 0 );
        layout.addWidget( cancelButton, 2, 1 );
    }

    private void initListeners()
    {
        cancelButton.released.connect( this, "close()" );
        applyButton.released.connect( this, "save()" );
    }

    @SuppressWarnings( "unused" )
    private void save()
    {
        Parameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            saveBook( parameters );
            CollectionPanel.getInstance().updateTree();
            close();
        }
    }
}
