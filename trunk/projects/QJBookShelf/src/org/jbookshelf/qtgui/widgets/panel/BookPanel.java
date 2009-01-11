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
package org.jbookshelf.qtgui.widgets.panel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;
import org.jbookshelf.model.impl.BookShelfImpl;
import org.jbookshelf.qtgui.widgets.completion.UniqueCompleter;
import org.jbookshelf.qtgui.widgets.ext.QFileDialogExt;
import org.jbookshelf.qtgui.widgets.ext.QWidgetExt;

import com.trolltech.qt.gui.QCheckBox;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;

/**
 * Shows book data of {@link ReadingUnit}
 * 
 * @author eav
 */
public class BookPanel
    extends QWidgetExt
{
    /**
     * Parameter set to excange between classes
     * 
     * @author eav
     */
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
    private QLineEdit     authorTextField   = new QLineEdit( this );
    private QLineEdit     bookTextField     = new QLineEdit( this );
    private QLineEdit     categoryTextField = new QLineEdit( this );

    private QLineEdit     fileTextField     = new QLineEdit( this );

    private List<QWidget> components        = new ArrayList<QWidget>();

    public BookPanel(
        QWidget parent )
    {
        super( parent );
        initComponents();
        initListeners();

        registerComponents();

        retranslate();
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

    /**
     * read data, entered by user
     * 
     * @return all field values, incapsulated in {@link Parameters}
     */
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

    public void retranslate()
    {
        isReadCheckBox.setText( tr( "Is read" ) );
        bookLabel.setText( tr( "Book" ) );
        authorLabel.setText( tr( "Author" ) );
        categoryLabel.setText( tr( "Category" ) );
        fileLabel.setText( tr( "File" ) );
    }

    /**
     * show book properties on panel
     * 
     * @param book a book to edit
     */
    public void setBook(
        ReadingUnit book )
    {
        // show book name
        bookTextField.setText( book.getName() );

        // show author name
        Author author = book.getAuthors().get( 0 ); // todo multiple authors
        if ( author == null )
        { // author has been removed
            BookShelfImpl bookShelf = (BookShelfImpl) Storage.getBookShelf();
            author = bookShelf.getUnknown();
            book.getAuthors().add( author );
        }
        authorTextField.setText( author.getName() );

        // show category name
        Category category = book.getCategories().get( 0 ); // todo multiple categories
        if ( category == null )
        { // category has been removed
            BookShelfImpl bookShelf = (BookShelfImpl) Storage.getBookShelf();
            category = bookShelf.getCommon();
            book.getCategories().add( category );
        }
        categoryTextField.setText( category.getName() );

        // show file of the physical unit
        String fileName;
        PhysicalUnit physical = book.getPhysical();
        // todo generalize
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

        // show whether is read
        isReadCheckBox.setChecked( book.isRead() );
    }

    @SuppressWarnings( "unused" )
    private void chooseFile()
    {
        QFileDialogExt fileDialog = new QFileDialogExt( this )
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

        chooseButton.setText( "..." );

        // hanging in autocompletion
        BookShelf bookShelf = Storage.getBookShelf();
        UniqueCompleter.decorate( authorTextField, bookShelf.getAuthors() );
        UniqueCompleter.decorate( categoryTextField, bookShelf.getCategories() );
    }

    private void initListeners()
    {
        chooseButton.released.connect( this, "chooseFile()" );
    }

    /**
     * register fields to clear quickly
     */
    private void registerComponents()
    {
        components.add( bookTextField );
        components.add( authorTextField );
        components.add( categoryTextField );
        components.add( fileTextField );
    }
}
