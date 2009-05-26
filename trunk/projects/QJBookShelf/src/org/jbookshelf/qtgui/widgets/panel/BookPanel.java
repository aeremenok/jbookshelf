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

import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.completion.CommaSeparatedCompleter;
import org.jbookshelf.qtgui.widgets.ext.QWidgetExt;
import org.jbookshelf.settings.Settings;

import com.trolltech.qt.gui.QCheckBox;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QWidget;

/**
 * Shows book data of {@link Book}
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
        private final String   viewer;

        public Parameters(
            final String bookName,
            final String[] authorNames,
            final String[] categoryNames,
            final File file,
            final boolean isRead,
            final String viewer )
        {
            super();
            this.bookName = bookName;
            this.authorNames = authorNames;
            this.categoryNames = categoryNames;
            this.file = file;
            this.isRead = isRead;
            this.viewer = viewer;
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

        public String getViewer()
        {
            return viewer;
        }

        public boolean isRead()
        {
            return isRead;
        }
    }

    public static Book changeBook(
        final Book book,
        final Parameters parameters )
    {
        // todo reflective
        book.getAuthors().clear();
        for ( final String name : parameters.getAuthorNames() )
        {
            Author author;
            final String trim = name.trim();
            final List<Author> authors = Storage.getBookShelf().queryAuthors( trim );
            if ( authors.size() > 0 )
            { // todo what if we've found more than 1 author with equal names?
                author = authors.get( 0 );
            }
            else
            {
                author = Storage.getBookShelf().addAuthor( trim );
            }
            book.getAuthors().add( author );
        }

        book.getCategories().clear();
        for ( final String name : parameters.getCategoryNames() )
        {
            Category category;
            final String trim = name.trim();
            final List<Category> authors = Storage.getBookShelf().queryCategories( trim );
            if ( authors.size() > 0 )
            { // todo what if we've found more than 1 author with equal names?
                category = authors.get( 0 );
            }
            else
            {
                category = Storage.getBookShelf().addCategory( trim );
            }
            book.getCategories().add( category );
        }

        book.setName( parameters.getBookName() );

        PhysicalUnit physical = book.getPhysical();
        if ( physical == null || !physical.getFile().equals( parameters.getFile() ) )
        {
            physical = FileImporter.createPhysicalUnit( parameters.getFile() );
        }
        physical.setViewer( parameters.getViewer() );

        book.setPhysical( physical );
        book.setRead( parameters.isRead()
            ? 1 : 0 );

        return book;
    }

    private final QLabel        authorLabel       = new QLabel( this );
    private final QLabel        categoryLabel     = new QLabel( this );
    private final QLabel        fileLabel         = new QLabel( this );
    private final QLabel        bookLabel         = new QLabel( this );

    private final QLabel        viewerLabel       = new QLabel( this );
    private final QLineEdit     bookTextField     = new QLineEdit( this );
    private final QLineEdit     authorTextField   = new QLineEdit( this );
    private final QLineEdit     categoryTextField = new QLineEdit( this );

    private final QComboBox     viewerComboBox    = new QComboBox( this );

    private final FileImporter  fileImporter      = new FileImporter()
                                                  {
                                                      @Override
                                                      protected void onImportFailure(
                                                          final File file,
                                                          final Exception e )
                                                      {
                                                      // let user enters the data
                                                      }

                                                      @Override
                                                      protected void onImportSuccess(
                                                          final Book book )
                                                      {
                                                          setBook( book );
                                                      }
                                                  };

    private final FilePathEdit  filePathEdit      = new FilePathEdit( this )
                                                  {
                                                      @Override
                                                      protected void fileSelected(
                                                          final String fileName )
                                                      {
                                                          super.fileSelected( fileName );
                                                          // try to parse book data
                                                          BookPanel.this.fileSelected( fileName );
                                                      }
                                                  };

    private final QCheckBox     isReadCheckBox    = new QCheckBox( this );

    private final List<QWidget> components        = new ArrayList<QWidget>();

    public BookPanel(
        final QWidget parent )
    {
        super( parent );
        initComponents();
        registerComponents();

        retranslate();
    }

    /**
     * @param hard indicates whether to clear file path
     */
    public void clear(
        final boolean hard )
    {
        for ( final QWidget component : components )
        {
            if ( component instanceof QLineEdit )
            {
                ((QLineEdit) component).setText( "" );
            }
        }
        if ( hard )
        {
            filePathEdit.setText( "" );
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
        final String bookName = bookTextField.text();
        final String title = tr( "Error" );
        if ( bookName.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Book name not specified" ) );
            return null;
        }

        final String authorNames = authorTextField.text();
        if ( authorNames.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Author name not specified" ) );
            return null;
        }

        final String categoryNames = categoryTextField.text();
        if ( categoryNames.equals( "" ) )
        {
            QMessageBox.critical( this, title, tr( "Category name not specified" ) );
            return null;
        }

        final File file = new File( filePathEdit.text() );
        if ( !file.exists() )
        {
            QMessageBox.critical( this, title, tr( "File does not exist: " ) + file.getName() );
            return null;
        }

        final boolean isRead = isReadCheckBox.isChecked();

        final int viewerIndex = viewerComboBox.currentIndex();
        final String viewer = viewerIndex == 0
            ? null : viewerIndex == 1
                ? PhysicalUnit.INTERNAL_VIEWER : PhysicalUnit.SYSTEM_VIEWER;
        return new Parameters( bookName, authorNames.split( "," ), categoryNames.split( "," ), file, isRead, viewer );
    }

    public void retranslate()
    {
        isReadCheckBox.setText( tr( "Is read" ) );
        bookLabel.setText( tr( "Book" ) );
        authorLabel.setText( tr( "Author" ) );
        categoryLabel.setText( tr( "Category" ) );
        fileLabel.setText( tr( "File" ) );
        filePathEdit.setCaption( tr( "Select a file" ) );
        viewerLabel.setText( tr( "Viewer" ) );
    }

    /**
     * show book properties on panel
     * 
     * @param book a book to edit
     */
    public void setBook(
        final Book book )
    {
        // display book name
        bookTextField.setText( book.getName() );
        authorTextField.setText( concat( book.getAuthors() ) );
        categoryTextField.setText( concat( book.getCategories() ) );

        // display file of the physical unit
        final String fileName = book.getPhysical().getFile().getAbsolutePath();
        filePathEdit.setText( fileName );

        // display whether is read
        isReadCheckBox.setChecked( book.getRead() == 1 );

        // display viewer
        final String viewer = book.getPhysical().getViewer();
        final int index = viewer == null
            ? 0 : PhysicalUnit.INTERNAL_VIEWER.equals( viewer )
                ? 1 : 2;
        viewerComboBox.setCurrentIndex( index );
    }

    public void setBookFile(
        final File bookFile )
    {
        filePathEdit.setText( bookFile.getAbsolutePath() );
    }

    private String concat(
        final List<? extends Unique> list )
    {
        final StringBuilder builder = new StringBuilder();
        for ( final Unique unique : list )
        {
            if ( unique != null )
            {
                builder.append( unique.getName() ).append( ", " );
            }
        }
        if ( builder.length() > 0 )
        {
            builder.deleteCharAt( builder.length() - 1 );
            builder.deleteCharAt( builder.length() - 1 );
        }
        return builder.toString();
    }

    private void fileSelected(
        final String fileName )
    {
        final String mask = Single.instance( Settings.class ).IMPORT_MASK.getValue();
        final String[] masks = mask.split( "/" );
        fileImporter.importFiles( masks, Storage.getBookShelf(), new File( fileName ) );
    }

    private void initComponents()
    {
        final QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( bookLabel, 0, 0 );
        layout.addWidget( bookTextField, 0, 1 );

        layout.addWidget( authorLabel, 1, 0 );
        layout.addWidget( authorTextField, 1, 1 );

        layout.addWidget( categoryLabel, 2, 0 );
        layout.addWidget( categoryTextField, 2, 1 );

        layout.addWidget( fileLabel, 3, 0 );
        layout.addWidget( filePathEdit, 3, 1 );

        layout.addWidget( viewerLabel, 4, 0 );
        layout.addWidget( viewerComboBox, 4, 1 );

        layout.addWidget( isReadCheckBox, 5, 0 );

        viewerComboBox.addItem( tr( "Auto" ) );
        viewerComboBox.addItem( tr( "Internal" ) );
        viewerComboBox.addItem( tr( "System" ) );

        // hanging in autocompletion
        final BookShelf bookShelf = Storage.getBookShelf();
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
