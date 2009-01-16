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

import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;

/**
 * imports filesystem into {@link BookShelf}
 * 
 * @author eav
 */
public class FileImporter
{
    static class HtmlFolderFileFilter
        implements
            FileFilter
    {
        private String mainFolderName;

        public boolean accept(
            File pathname )
        {
            boolean b = pathname.isDirectory() && pathname.getName().endsWith( "_files" );
            if ( b )
            {
                mainFolderName = pathname.getName();
            }
            return b;
        }

        public String getMainFolderName()
        {
            return mainFolderName;
        }
    }

    public static PhysicalUnit createPhysicalUnit(
        File file )
    {
        if ( file.isDirectory() )
        {
            File[] files = file.listFiles( new FileFilter()
            {
                public boolean accept(
                    File pathname )
                {
                    String name = pathname.getName();
                    return name.equalsIgnoreCase( "index.html" ) || name.equalsIgnoreCase( "index.htm" );
                }
            } );

            if ( files.length > 1 )
            { // at least one index file
                IndexFileFolder indexFileFolder = ModelFactory.eINSTANCE.createIndexFileFolder();
                indexFileFolder.setIndexFile( files[0] );
                indexFileFolder.setIndexFolder( file );
                return indexFileFolder;
            }

            File[] listFiles = file.listFiles();
            if ( listFiles.length == 1 )
            { // simply single file
                SingleFileFolder singleFileFolder = ModelFactory.eINSTANCE.createSingleFileFolder();
                singleFileFolder.setFolder( file );
                singleFileFolder.setSingleFile( listFiles[0] );
                return singleFileFolder;
            }

            File mainFile = getHtmlFile( file );
            if ( mainFile != null )
            { // file.html with file_files/
                SingleFileFolder singleFileFolder = ModelFactory.eINSTANCE.createSingleFileFolder();
                singleFileFolder.setFolder( file );
                singleFileFolder.setSingleFile( mainFile );
                return singleFileFolder;
            }
            return null;
        }

        // zip file
        if ( file.getName().endsWith( ".zip" ) )
        {
            ArchiveFile archiveFile = ModelFactory.eINSTANCE.createArchiveFile();
            archiveFile.setArchiveFile( file );
            return archiveFile;
        }

        // single file
        SingleFile singleFile = ModelFactory.eINSTANCE.createSingleFile();
        singleFile.setFile( file );
        return singleFile;
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
        File root = new File( "/tmp/test" );
        new FileImporter()
        {
            @Override
            protected void onImportFailure(
                File file,
                Exception e )
            {
                System.out.println( "-cannot import file " + file.getAbsolutePath() + " cause:" + e.getMessage() );
            }

            @Override
            protected void onImportSuccess(
                ReadingUnit book )
            {
                System.out.println( "+imported " + book.getAuthors().get( 0 ).getName() + ". " + book.getName() );
            }
        }.importFiles( "%a. %b", bookShelf, root.listFiles() );
    }

    /**
     * try to find if folder holds "file" with "file_files/"
     * 
     * @param folder folder
     * @return main file ("file") or null in case failure
     */
    private static File getHtmlFile(
        File folder )
    {
        HtmlFolderFileFilter filter = new HtmlFolderFileFilter();
        File[] listFiles = folder.listFiles( filter );
        if ( listFiles.length == 1 )
        {
            String mainFolderName = filter.getMainFolderName();
            File mainFile = new File( mainFolderName.substring( 0, mainFolderName.indexOf( "_files" ) ) );
            if ( mainFile.exists() )
            {
                return mainFile;
            }
        }
        return null;
    }

    public void importFiles(
        String pattern,
        BookShelf bookShelf,
        File... files )
    {
        NameParser nameParser = new NameParser( pattern );
        for ( File file : files )
        {
            PhysicalUnit physicalUnit = createPhysicalUnit( file );
            if ( physicalUnit != null )
            {
                try
                {
                    nameParser.parse( cutExtension( file ) );

                    Author author = bookShelf.addAuthor( nameParser.getAuthorName() );
                    Category category = bookShelf.addCategory( nameParser.getCategoryName() );
                    ReadingUnit book =
                        bookShelf.addReadingUnit( nameParser.getBookName(), author, category, physicalUnit );

                    onImportSuccess( book );
                }
                catch ( Exception e )
                {
                    onImportFailure( file, e );
                }
            }
            else if ( file.isDirectory() )
            {
                importFiles( pattern, bookShelf, file.listFiles() );
            }
            else
            {
                onImportFailure( file, new Exception( "unparseable directory" ) // todo specify more info
                );
            }
        }
    }

    protected void onImportFailure(
        @SuppressWarnings( "unused" ) File file,
        @SuppressWarnings( "unused" ) Exception e )
    {
        // override if needed
    }

    protected void onImportSuccess(
        @SuppressWarnings( "unused" ) ReadingUnit book )
    {
        // override if needed
    }
}
