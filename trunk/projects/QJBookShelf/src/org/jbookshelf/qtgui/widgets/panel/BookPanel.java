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
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.completion.CommaSeparatedCompleter;
import org.jbookshelf.qtgui.widgets.ext.QWidgetExt;

import com.trolltech.qt.gui.QCheckBox;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
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
        private final String   bookName;
        private final String[] authorNames;
        private final String[] categoryNames;
        private final File     file;
        private final boolean  isRead;

        public Parameters(
            String bookName,
            String[] authorNames,
            String[] categoryNames,
            File file,
            boolean isRead )
        {
            this.bookName = bookName;
            this.authorNames = authorNames;
            this.categoryNames = categoryNames;
            this.file = file;
            this.isRead = isRead;
        }

        public String[] getAuthorNames()
        {
            return authorNames;
        }

        public String getBookName()
        {
            return bookName;
        }

        public String[] getCategoryNames()
        {
            return categoryNames;
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

    private QLineEdit     bookTextField     = new QLineEdit( this );
    private QLineEdit     authorTextField   = new QLineEdit( this );
    private QLineEdit     categoryTextField = new QLineEdit( this );

    private FilePathEdit  filePathEdit      = new FilePathEdit( this );

    private QCheckBox     isReadCheckBox    = new QCheckBox( this );

    private List<QWidget> components        = new ArrayList<QWidget>();

    public BookPanel(
        QWidget parent )
    {
        super( parent );
        initComponents();
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
        filePathEdit.setText( "" );
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

        String authorNames = authorTextField.text();
        if ( authorNames.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Author name not specified" ) );
            return null;
        }

        String categoryNames = categoryTextField.text();
        if ( categoryNames.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Category name not specified" ) );
            return null;
        }

        File file = new File( filePathEdit.text() );
        if ( !file.exists() )
        {
            QMessageBox.critical( this, title, tr( "File does not exist: " ) + file.getName() );
            return null;
        }

        boolean isRead = isReadCheckBox.isChecked();

        return new Parameters( bookName, authorNames.split( "," ), categoryNames.split( "," ), file, isRead );
    }

    public void retranslate()
    {
        isReadCheckBox.setText( tr( "Is read" ) );
        bookLabel.setText( tr( "Book" ) );
        authorLabel.setText( tr( "Author" ) );
        categoryLabel.setText( tr( "Category" ) );
        fileLabel.setText( tr( "File" ) );
        filePathEdit.setCaption( tr( "Select a file" ) );
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
        authorTextField.setText( concat( book.getAuthors() ) );
        categoryTextField.setText( concat( book.getCategories() ) );

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
        filePathEdit.setText( fileName );

        // show whether is read
        isReadCheckBox.setChecked( book.isRead() );
    }

    public void setBookFile(
        File bookFile )
    {
        filePathEdit.setText( bookFile.getAbsolutePath() );
    }

    private String concat(
        List<? extends Unique> list )
    {
        StringBuilder builder = new StringBuilder();
        for ( Unique unique : list )
        {
            if ( unique != null )
            {
                builder.append( unique.getName() ).append( ", " );
            }
        }
        builder.deleteCharAt( builder.length() - 1 );
        builder.deleteCharAt( builder.length() - 1 );
        return builder.toString();
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
        layout.addWidget( filePathEdit, 3, 1 );

        layout.addWidget( isReadCheckBox, 4, 0 );

        // hanging in autocompletion
        BookShelf bookShelf = Storage.getBookShelf();
        CommaSeparatedCompleter.decorate( authorTextField, bookShelf.getAuthors() );
        CommaSeparatedCompleter.decorate( categoryTextField, bookShelf.getCategories() );
    }

    /**
     * register fields to clear quickly
     */
    private void registerComponents()
    {
        components.add( bookTextField );
        components.add( authorTextField );
        components.add( categoryTextField );
    }
}
