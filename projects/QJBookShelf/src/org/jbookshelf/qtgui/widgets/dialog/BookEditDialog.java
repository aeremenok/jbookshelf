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

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.Book;
import org.jbookshelf.qtgui.MainWindow;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.jbookshelf.qtgui.widgets.panel.BookPanel;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.BookPanel.Parameters;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;

/**
 * Shows book data of existing {@link Book} in the {@link BookPanel}. Then updates a {@link Book} from the
 * {@link BookPanel}
 * 
 * @author eav
 */
public class BookEditDialog
    extends QDialogExt
{
    private final QPushButton applyButton  = new QPushButton( this );
    private final QPushButton cancelButton = new QPushButton( this );
    private final QLabel      headerLabel  = new QLabel( this );

    private final BookPanel   bookPanel    = new BookPanel( this );

    private final Book        book;

    public BookEditDialog(
        final QWidget parent,
        final Book book )
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

    private void initComponents()
    {
        setModal( true );

        final QRect geometry = geometry();
        geometry.setWidth( 770 );
        geometry.setHeight( 300 );
        geometry.moveCenter( Single.instance( MainWindow.class ).geometry().center() );
        setGeometry( geometry );

        final QGridLayout layout = new QGridLayout();
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
        final Parameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            BookPanel.changeBook( book, parameters );
            Single.instance( CollectionPanel.class ).updateTree();
            close();
        }
    }
}
