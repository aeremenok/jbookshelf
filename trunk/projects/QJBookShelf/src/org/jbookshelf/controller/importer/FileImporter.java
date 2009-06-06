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
package org.jbookshelf.controller.importer;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.PhysicalBook;

/**
 * imports filesystem into database
 * 
 * @author eav
 */
public class FileImporter
{
    private static class UnsupportedExtFilter
        implements
        FileFilter
    {
        String[] extensions =
                            { "gif", "png", "bmp", "jpg", "jpeg", "js", "css", "ini", "db"
                            // todo ...
                            };

        public boolean accept(
            final File pathname )
        {
            final String name = pathname.getAbsolutePath().toLowerCase();
            for ( final String ext : extensions )
            {
                if ( name.endsWith( "." + ext ) )
                {
                    return false;
                }
            }
            return true;
        }
    }

    private static PhysicalBookImporter[] importers              =
                                                                 { new IndexFileImporter(),
                    new SingleFileDirImporter(), new HtmlFileImporter(), new ZipFileImporter(),
                    new SingleFileImporter()                    };

    public static final FileFilter        UNSUPPORTED_EXT_FILTER = new UnsupportedExtFilter();

    @Nullable
    public static PhysicalBook createPhysicalBook(
        @Nonnull final File file )
    {
        for ( final PhysicalBookImporter importer : importers )
        {
            if ( importer.accept( file ) )
            {
                return importer.create( file );
            }
        }
        return null;
    }

    public static String cutExtension(
        @Nonnull final String fileName )
    {
        if ( new File( fileName ).isDirectory() )
        {
            return fileName;
        }

        final int lastIndexOf = fileName.lastIndexOf( '.' );
        if ( fileName.length() - lastIndexOf > 5 )
        { // it is probably a part of the name todo which files could have a longer extension?
            return fileName;
        }

        return fileName.substring( 0, lastIndexOf );
    }

    public static void main(
        final String[] args )
    {
        final File root = new File( args[0] );
        new FileImporter()
        {
            @Override
            protected void onImportFailure(
                final File file,
                final Exception e )
            {
                System.out.println( "-cannot import file " + file.getAbsolutePath() + " cause: " + e.getMessage() );
            }

            @Override
            protected void onImportSuccess(
                final Book book )
            {
                System.out.println( "+imported " + book.getPhysicalBook().getFileName() );
            }
        }.importFiles( new String[]
        { "%a. %b" }, root );
    }

    @Nullable
    private static Book bookFromName(
        @Nonnull final NameParser parser,
        @Nonnull final PhysicalBook physicalUnit )
    {
        try
        {
            parser.parse( cutExtension( new File( physicalUnit.getFileName() ).getName() ) );

            final String authorName = parser.getAuthorName();
            final String categoryName = parser.getCategoryName();
            final String bookName = parser.getBookName();

            if ( bookName != null && "".equals( bookName ) )
            {
                return BookShelf.addBook( bookName, authorName, categoryName, physicalUnit );
            }
            return null;
        }
        catch ( final Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    public void importFiles(
        @Nonnull final String[] patterns,
        @Nonnull final File... files )
    {
        final List<NameParser> parsers = new ArrayList<NameParser>();
        for ( final String string : patterns )
        {
            parsers.add( new NameParser( string ) );
        }

        // recursively process files
        for ( final File file : files )
        {
            final PhysicalBook physicalUnit = createPhysicalBook( file );
            if ( physicalUnit != null )
            {
                Book book = null;
                final Iterator<NameParser> iterator = parsers.iterator();
                while ( iterator.hasNext() && book == null )
                {
                    final NameParser parser = iterator.next();
                    book = bookFromName( parser, physicalUnit );
                }

                if ( book != null )
                {
                    onImportSuccess( book );
                }
                else
                {// todo message
                    onImportFailure( file, new Exception() );
                }
            }
            else if ( file.isDirectory() )
            {
                importFiles( patterns, file.listFiles( UNSUPPORTED_EXT_FILTER ) );
            }
            else
            { // todo specify more info
                onImportFailure( file, new Exception( "unparseable directory" ) );
            }
        }
    }

    protected void onImportFailure(
        @SuppressWarnings( "unused" ) @Nonnull final File file,
        @SuppressWarnings( "unused" ) @Nonnull final Exception e )
    {
    // override if needed
    }

    protected void onImportSuccess(
        @SuppressWarnings( "unused" ) @Nonnull final Book book )
    {
    // override if needed
    }
}
