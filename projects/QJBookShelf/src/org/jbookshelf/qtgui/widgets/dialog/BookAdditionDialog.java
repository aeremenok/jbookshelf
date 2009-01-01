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
package org.jbookshelf.qtgui.widgets.dialog;

import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.widgets.panel.BookPanel;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.BookPanel.Parameters;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class BookAdditionDialog
    extends QDialog
    implements
        Translatable
{
    private QPushButton addNCloseButton    = new QPushButton();
    private QPushButton addNContinueButton = new QPushButton();
    private QPushButton cancelButton       = new QPushButton();

    private QLabel      headerLabel        = new QLabel();

    private BookPanel   bookPanel          = new BookPanel();

    public BookAdditionDialog(
        QWidget parent )
    {
        super( parent );
        initComponents();
        initListeners();

        retranslate();
    }

    public void addBook(
        Parameters parameters )
    {
        Author author = Storage.getBookShelf().addAuthor( parameters.getAuthorName() );
        Category category = Storage.getBookShelf().addCategory( parameters.getCategoryName() );
        PhysicalUnit physicalUnit = FileImporter.createPhysicalUnit( parameters.getFile() );
        ReadingUnit unit =
            Storage.getBookShelf().addReadingUnit( parameters.getBookName(), author, category, physicalUnit );
        unit.setRead( parameters.isRead() );

        CollectionPanel.getInstance().updateTree();

        QMessageBox.information( this, tr( "Added" ), parameters.getBookName() + " " + tr( "added" ) );
    }

    public void retranslate()
    {
        setWindowTitle( tr( "Add Book" ) );

        headerLabel.setText( tr( "Add Book" ) );

        addNCloseButton.setText( tr( "And and close" ) );
        addNContinueButton.setText( tr( "And and continue" ) );
        cancelButton.setText( tr( "Cancel" ) );
    }

    @SuppressWarnings( "unused" )
    private void addNClose()
    {
        Parameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            addBook( parameters );
            close();
        }
    }

    @SuppressWarnings( "unused" )
    private void addNContinue()
    {
        Parameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            addBook( parameters );
            bookPanel.clear();
        }
    }

    private void initComponents()
    {
        setModal( true );

        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( headerLabel, 0, 0, 1, 3 );
        layout.addWidget( bookPanel, 1, 0, 1, 3 );

        layout.addWidget( addNCloseButton, 2, 0 );
        layout.addWidget( addNContinueButton, 2, 1 );
        layout.addWidget( cancelButton, 2, 2 );

        headerLabel.setFont( new QFont( "Tahoma", 14 ) );
    }

    private void initListeners()
    {
        cancelButton.released.connect( this, "close()" );
        addNCloseButton.released.connect( this, "addNClose()" );
        addNContinueButton.released.connect( this, "addNContinue()" );
    }
}
