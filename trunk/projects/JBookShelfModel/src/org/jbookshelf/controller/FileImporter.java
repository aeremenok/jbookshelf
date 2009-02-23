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
package org.jbookshelf.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.PhysicalUnit;

/**
 * imports filesystem into {@link BookShelf}
 * 
 * @author eav
 */
public class FileImporter
{
    public static class ExtensionDenyingFilter
        implements
            FileFilter
    {
        String[] extensions = { "gif", "png", "bmp", "jpg", "jpeg", "js", "css", "ini", "db" };

        public boolean accept(
            File pathname )
        {
            String name = pathname.getAbsolutePath().toLowerCase();
            for ( String ext : extensions )
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
        File mainFolder;
        File mainFile;

        public boolean accept(
            File pathname )
        {
            String name = pathname.getName();
            boolean b = pathname.isDirectory() && name.endsWith( "_files" );
            if ( b )
            {
                mainFolder = pathname;
            }
            else if ( pathname.isFile() &&
                (name.endsWith( ".htm" ) || name.endsWith( ".html" ) || name.endsWith( ".shtml" )) )
            {
                mainFile = pathname;
            }
            return b;
        }
    }

    public static PhysicalUnit createPhysicalUnit(
        File file )
    {
        PhysicalUnit physicalUnit = ModelFactory.eINSTANCE.createPhysicalUnit();
        if ( file.isDirectory() )
        {
            physicalUnit.setDirectory( file );
            File[] files = file.listFiles( new FileFilter()
            {
                public boolean accept(
                    File pathname )
                {
                    String name = pathname.getName().toLowerCase();
                    return name.equals( "index.html" ) || name.equals( "index.htm" ) || name.equals( "index.shtml" );
                }
            } );

            if ( files.length > 1 )
            { // at least one index file
                physicalUnit.setFile( files[0] );
                return physicalUnit;
            }

            File[] listFiles = file.listFiles();
            if ( listFiles.length == 1 && listFiles[0].isFile() &&
            // zip file will be imported later
                !listFiles[0].getName().toLowerCase().endsWith( ".zip" ) )
            { // simply single file
                physicalUnit.setFile( listFiles[0] );
                return physicalUnit;
            }

            File mainFile = getHtmlFile( file );
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
            physicalUnit = ModelFactory.eINSTANCE.createArchiveFile();
            physicalUnit.setDirectory( file.getParentFile() );
            physicalUnit.setFile( file );
            // will be unpacked later
            ((ArchiveFile) physicalUnit).setArchiveFile( null );
            return physicalUnit;
        }

        // single file
        physicalUnit.setDirectory( file.getParentFile() );
        physicalUnit.setFile( file );
        return physicalUnit;
    }

    public static String cutExtension(
        File file )
    {
        if ( file.isDirectory() )
        {
            return file.getName();
        }

        String name = file.getName();
        int lastIndexOf = name.lastIndexOf( '.' );
        if ( name.length() - lastIndexOf > 5 )
        { // it is probably a part of the name todo which files could have a longer extension?
            return name;
        }

        return name.substring( 0, lastIndexOf );
    }

    public static void main(
        String[] args )
    {
        BookShelf bookShelf = ModelFactory.eINSTANCE.createBookShelf();
        File root = new File( "/home/eav/doc" );
        new FileImporter()
        {
            @Override
            protected void onImportFailure(
                File file,
                Exception e )
            {
                System.out.println( "-cannot import file " + file.getAbsolutePath() + " cause: " + e.getMessage() );
            }

            @Override
            protected void onImportSuccess(
                Book book )
            {
                System.out.println( "+imported " + book.getAuthors().get( 0 ).getName() + ". " + book.getName() );
            }
        }.importFiles( new String[] { "%a. %b" }, bookShelf, root );
    }

    /**
     * try to find if folder holds "file" with "file_files/" todo too complex - simplify!
     * 
     * @param folder folder
     * @return main file ("file") or null in case failure
     */
    private static File getHtmlFile(
        File folder )
    {
        HtmlFolderFileFilter filter = new HtmlFolderFileFilter();
        folder.listFiles( filter );
        if ( filter.mainFolder != null && filter.mainFile != null )
        {
            return filter.mainFile;
        }
        return null;
    }

    public void importFiles(
        String[] patterns,
        BookShelf bookShelf,
        File... files )
    {
        List<NameParser> parsers = new ArrayList<NameParser>();
        for ( String string : patterns )
        {
            NameParser nameParser = new NameParser( string );
            parsers.add( nameParser );
        }

        for ( File file : files )
        {
            PhysicalUnit physicalUnit = createPhysicalUnit( file );
            if ( physicalUnit != null )
            {
                Book book = null;
                Iterator<NameParser> iterator = parsers.iterator();
                while ( iterator.hasNext() && book == null )
                {
                    NameParser parser = iterator.next();
                    book = bookFromName( parser, physicalUnit, bookShelf );
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
                importFiles( patterns, bookShelf, file.listFiles( new ExtensionDenyingFilter() ) );
            }
            else
            {
                onImportFailure( file, new Exception( "unparseable directory" ) // todo specify more info
                );
            }
        }
    }

    private Book bookFromName(
        NameParser parser,
        PhysicalUnit physicalUnit,
        BookShelf bookShelf )
    {
        try
        {
            parser.parse( cutExtension( physicalUnit.getFile() ) );

            Author author = bookShelf.addAuthor( parser.getAuthorName() );
            Category category = bookShelf.addCategory( parser.getCategoryName() );
            return bookShelf.addReadingUnit( parser.getBookName(), author, category, physicalUnit );
        }
        catch ( Exception e )
        {
            return null;
        }
    }

    protected void onImportFailure(
        @SuppressWarnings( "unused" ) File file,
        @SuppressWarnings( "unused" ) Exception e )
    {
        // override if needed
    }

    protected void onImportSuccess(
        @SuppressWarnings( "unused" ) Book book )
    {
        // override if needed
    }
}
