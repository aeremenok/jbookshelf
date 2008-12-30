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
package org.jbookshelf.qtgui.widgets.panel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;
import org.jbookshelf.qtgui.FileDialog;

import com.trolltech.qt.gui.QCheckBox;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class BookPanel
    extends QWidget
{
    public class Parameters
    {
        private final String  bookName;
        private final String  authorName;
        private final String  categoryName;
        private final File    file;
        private final boolean isRead;

        public Parameters(
            String bookName,
            String authorName,
            String categoryName,
            File file,
            boolean isRead )
        {
            this.bookName = bookName;
            this.authorName = authorName;
            this.categoryName = categoryName;
            this.file = file;
            this.isRead = isRead;
        }

        public String getAuthorName()
        {
            return authorName;
        }

        public String getBookName()
        {
            return bookName;
        }

        public String getCategoryName()
        {
            return categoryName;
        }

        public File getFile()
        {
            return file;
        }

        public boolean isRead()
        {
            return isRead;
        }
    }

    private QLabel        authorLabel       = new QLabel();
    private QLabel        categoryLabel     = new QLabel();
    private QLabel        fileLabel         = new QLabel();
    private QLabel        bookLabel         = new QLabel();

    private QCheckBox     isReadCheckBox    = new QCheckBox();
    private QPushButton   chooseButton      = new QPushButton();

    private QLineEdit     authorTextField   = new QLineEdit();
    private QLineEdit     bookTextField     = new QLineEdit();
    private QLineEdit     categoryTextField = new QLineEdit();
    private QLineEdit     fileTextField     = new QLineEdit();

    private List<QWidget> components        = new ArrayList<QWidget>();

    public BookPanel()
    {
        super();
        initComponents();
        initListeners();

        registerComponents();
    }

    public void clear()
    {
        for ( QWidget component : components )
        {
            if ( component instanceof QLineEdit )
            {
                ((QLineEdit) component).setText( "" );
            }
        }
        isReadCheckBox.setChecked( false );
    }

    public Parameters extractParameters()
    {
        String bookName = bookTextField.text();
        String title = tr( "Error" );
        if ( bookName.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Book name not specified" ) );
            return null;
        }

        String authorName = authorTextField.text();
        if ( authorName.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Author name not specified" ) );
            return null;
        }

        String categoryName = categoryTextField.text();
        if ( categoryName.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Category name not specified" ) );
            return null;
        }

        File file = new File( fileTextField.text() );
        if ( !file.exists() )
        {
            QMessageBox.critical( this, title, tr( "File does not exist: " ) + file.getName() );
            return null;
        }

        boolean isRead = isReadCheckBox.isChecked();

        return new Parameters( bookName, authorName, categoryName, file, isRead );
    }

    public void setBook(
        ReadingUnit book )
    {
        bookTextField.setText( book.getName() );
        Author author = book.getAuthors().get( 0 ); // todo multiple authors
        authorTextField.setText( author.getName() );
        Category category = book.getCategories().get( 0 ); // todo multiple categories
        categoryTextField.setText( category.getName() );

        String fileName;
        PhysicalUnit physical = book.getPhysical();
        if ( physical instanceof SingleFile )
        {
            SingleFile singleFile = (SingleFile) physical;
            fileName = singleFile.getFile().getAbsolutePath();
        }
        else if ( physical instanceof ArchiveFile )
        {
            ArchiveFile archiveFile = (ArchiveFile) physical;
            fileName = archiveFile.getArchiveFile().getAbsolutePath();
        }
        else if ( physical instanceof SingleFileFolder )
        {
            SingleFileFolder singleFileFolder = (SingleFileFolder) physical;
            fileName = singleFileFolder.getFolder().getAbsolutePath();
        }
        else if ( physical instanceof IndexFileFolder )
        {
            IndexFileFolder indexFileFolder = (IndexFileFolder) physical;
            fileName = indexFileFolder.getIndexFolder().getAbsolutePath();
        }
        else
        {
            throw new Error( physical.toString() );
        }
        fileTextField.setText( fileName );

        isReadCheckBox.setChecked( book.isRead() );
    }

    @SuppressWarnings( "unused" )
    private void chooseFile()
    {
        FileDialog fileDialog = new FileDialog( this )
        {
            @Override
            protected void filesSelected()
            {
                fileTextField.setText( selectedFiles().get( 0 ) );
            }
        };
        fileDialog.selectFile( fileTextField.text() );
        fileDialog.show();
    }

    private void initComponents()
    {
        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( bookLabel, 0, 0 );
        layout.addWidget( bookTextField, 0, 1 );

        layout.addWidget( authorLabel, 1, 0 );
        layout.addWidget( authorTextField, 1, 1 );

        layout.addWidget( categoryLabel, 2, 0 );
        layout.addWidget( categoryTextField, 2, 1 );

        layout.addWidget( fileLabel, 3, 0 );
        layout.addWidget( fileTextField, 3, 1 );
        layout.addWidget( chooseButton, 3, 2 );

        layout.addWidget( isReadCheckBox, 4, 0 );

        isReadCheckBox.setText( tr( "Is read" ) );
        bookLabel.setText( tr( "Book" ) );
        authorLabel.setText( tr( "Author" ) );
        categoryLabel.setText( tr( "Category" ) );
        fileLabel.setText( tr( "File" ) );

        chooseButton.setText( "..." );
    }

    private void initListeners()
    {
        chooseButton.released.connect( this, "chooseFile()" );
    }

    private void registerComponents()
    {
        components.add( bookTextField );
        components.add( authorTextField );
        components.add( categoryTextField );
        components.add( fileTextField );
    }
}
