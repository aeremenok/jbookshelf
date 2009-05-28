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
    private static class ExtensionDenyingFilter
        implements
        FileFilter
    {
        String[] extensions =
                            { "gif", "png", "bmp", "jpg", "jpeg", "js", "css", "ini", "db" };

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

    private static class HtmlFolderFileFilter
        implements
        FileFilter
    {
        @Nonnull
        File mainFolder;
        @Nonnull
        File mainFile;

        public boolean accept(
            final File pathname )
        {
            final String name = pathname.getName();
            final boolean b = pathname.isDirectory() && name.endsWith( "_files" );
            if ( b )
            {
                mainFolder = pathname;
            }
            else if ( pathname.isFile()
                && (name.endsWith( ".htm" ) || name.endsWith( ".html" ) || name.endsWith( ".shtml" )) )
            {
                mainFile = pathname;
            }
            return b;
        }
    }

    public static final FileFilter UNSUPPORTED_EXT_FILTER = new ExtensionDenyingFilter();

    public static PhysicalBook createPhysicalUnit(
        @Nonnull final File file )
    {
        final PhysicalBook physicalUnit = new PhysicalBook();
        if ( file.isDirectory() )
        {
            final File[] files = file.listFiles( new FileFilter()
            {
                public boolean accept(
                    final File pathname )
                {
                    final String name = pathname.getName().toLowerCase();
                    return name.equals( "index.html" ) || name.equals( "index.htm" ) || name.equals( "index.shtml" );
                }
            } );

            if ( files.length > 1 )
            { // at least one index file
                physicalUnit.setFile( files[0] );
                return physicalUnit;
            }

            final File[] listFiles = file.listFiles();
            if ( listFiles.length == 1 && listFiles[0].isFile() &&
            // zip file will be imported later
                !listFiles[0].getName().toLowerCase().endsWith( ".zip" ) )
            { // simply single file
                physicalUnit.setFile( listFiles[0] );
                return physicalUnit;
            }

            final File mainFile = getHtmlFile( file );
            if ( mainFile != null )
            { // file.html with file_files/
                physicalUnit.setFile( mainFile );
                return physicalUnit;
            }

            return null;
        }

        // zip file
        if ( file.getName().toLowerCase().endsWith( ".zip" ) )
        {
            physicalUnit.setFile( file );
            // will be unpacked later
            return physicalUnit;
        }

        // single file
        physicalUnit.setFile( file );
        return physicalUnit;
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
        final File root = new File( "/home/eav/data/books/оип" );
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

    /**
     * try to find if folder holds "file" with "file_files/" todo too complex - simplify!
     * 
     * @param folder folder
     * @return main file ("file") or null in case failure
     */
    @Nullable
    private static File getHtmlFile(
        @Nonnull final File folder )
    {
        final HtmlFolderFileFilter filter = new HtmlFolderFileFilter();
        folder.listFiles( filter );
        if ( filter.mainFolder != null && filter.mainFile != null )
        {
            return filter.mainFile;
        }
        return null;
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

        for ( final File file : files )
        {
            final PhysicalBook physicalUnit = createPhysicalUnit( file );
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
                {
                    // todo message
                    onImportFailure( file, new Exception() );
                }
            }
            else if ( file.isDirectory() )
            {
                importFiles( patterns, file.listFiles( UNSUPPORTED_EXT_FILTER ) );
            }
            else
            {
                onImportFailure( file, new Exception( "unparseable directory" ) // todo specify more info
                );
            }
        }
    }

    @Nullable
    private Book bookFromName(
        @Nonnull final NameParser parser,
        @Nonnull final PhysicalBook physicalUnit )
    {
        try
        {
            parser.parse( cutExtension( physicalUnit.getFileName() ) );

            final String authorName = parser.getAuthorName();
            final String categoryName = parser.getCategoryName();
            final String bookName = parser.getBookName();

            return BookShelf.getBook( bookName, authorName, categoryName, physicalUnit );
        }
        catch ( final Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    protected void onImportFailure(
        @SuppressWarnings( "unused" ) final File file,
        @SuppressWarnings( "unused" ) final Exception e )
    {
    // override if needed
    }

    protected void onImportSuccess(
        @SuppressWarnings( "unused" ) final Book book )
    {
    // override if needed
    }
}
