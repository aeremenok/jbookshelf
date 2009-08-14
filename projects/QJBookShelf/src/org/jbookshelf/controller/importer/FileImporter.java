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
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;

/**
 * imports filesystem into database
 * 
 * @author eav
 */
public class FileImporter
{
    /**
     * rejects files with extensions from the given list
     * 
     * @author eav 2009
     */
    private static class UnsupportedExtFilter
        implements
        FileFilter
    {
        private final List<String> exts = new ArrayList<String>();

        public UnsupportedExtFilter()
        {
            super();
            exts.add( "gif" );
            exts.add( "png" );
            exts.add( "bmp" );
            exts.add( "jpg" );
            exts.add( "jpeg" );
            exts.add( "js" );
            exts.add( "css" );
            exts.add( "ini" );
            exts.add( "db" );
            // todo ...
        }

        public boolean accept(
            final File pathname )
        {
            if ( pathname.isDirectory() )
            {
                return true;
            }
            final String ext = FilenameUtils.getExtension( pathname.getAbsolutePath() );
            return !exts.contains( ext );
        }
    }

    public static final FileFilter                  UNSUPPORTED_EXT_FILTER = new UnsupportedExtFilter();
    private static final List<PhysicalBookImporter> importers              = new ArrayList<PhysicalBookImporter>();
    @SuppressWarnings( "unused" )
    private static final Logger                     log                    = Logger.getLogger( FileImporter.class );

    static
    {
        importers.add( new IndexFileImporter() );
        importers.add( new SingleFileDirImporter() );
        importers.add( new HtmlFileImporter() );
        importers.add( new ZipFileImporter() );
        importers.add( new SingleFileImporter() );
    }

    /**
     * guess the type of the file and create a {@link Book} implementation
     * 
     * @param file a file to import
     * @return a book implementation
     */
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

    public void importFiles(
        final Parameters parameters )
    {
        final List<File> roots = parameters.get( Keys.IMPORT_ROOTS );
        final FileImportStrategy strategy = parameters.get( Keys.IMPORT_STRATEGY );
        importFilesImpl( roots, strategy );
    }

    private void importFilesImpl(
        final File[] roots,
        final FileImportStrategy strategy )
    {
        importFilesImpl( Arrays.asList( roots ), strategy );
    }

    private void importFilesImpl(
        final List<File> roots,
        final FileImportStrategy strategy )
    {
        for ( final File file : roots )
        {
            final PhysicalBook physicalUnit = createPhysicalBook( file );
            if ( physicalUnit != null )
            {
                final Book book = strategy.importBook( physicalUnit );
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
            { // here goes the recursion
                importFilesImpl( file.listFiles( UNSUPPORTED_EXT_FILTER ), strategy );
            }
            else
            { // todo specify more info
                onImportFailure( file, new Exception( "unparseable directory" ) );
            }
        }
    }

    /**
     * called when file import failed
     * 
     * @param file a file
     * @param cause a failure cause
     */
    protected void onImportFailure(
        @SuppressWarnings( "unused" ) @Nonnull final File file,
        @SuppressWarnings( "unused" ) @Nonnull final Exception cause )
    {
    // override if needed
    }

    /**
     * called when file import succeeded
     * 
     * @param book an imported book
     */
    protected void onImportSuccess(
        @SuppressWarnings( "unused" ) @Nonnull final Book book )
    {
    // override if needed
    }
}
