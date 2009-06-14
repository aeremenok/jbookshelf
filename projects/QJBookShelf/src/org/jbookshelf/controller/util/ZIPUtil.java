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
package org.jbookshelf.controller.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.importer.FileImporter;

/**
 * handles zip-files
 * 
 * @author eav
 */
public class ZIPUtil
{
    private static final Logger log = Logger.getLogger( ZIPUtil.class );

    /**
     * extracts the specified zip-file to the specified directory
     * 
     * @param zipFilename source file name
     * @param destDirName destination directory name
     * @return destination directory
     */
    public static File extractZipFileTo(
        final String zipFilename,
        final String destDirName )
    {
        InputStream zis = null;
        FileOutputStream fos = null;
        try
        {
            // create destination directory
            final File destDir = new File( destDirName );
            destDir.mkdir();

            // unzip
            final ZipFile zipFile = new ZipFile( zipFilename );
            final Enumeration entries = zipFile.getEntries();
            while ( entries.hasMoreElements() )
            {
                final ZipArchiveEntry entry = (ZipArchiveEntry) entries.nextElement();
                zis = zipFile.getInputStream( entry );

                final File f = new File( destDirName + File.separator + entry.getName() );
                if ( !f.exists() )
                { // a file doesn't exist, create it
                    if ( entry.isDirectory() )
                    {
                        f.mkdirs();
                    }
                    else
                    {
                        FileUtils.touch( f );
                    }

                    log.debug( "created " + f.getAbsolutePath() );
                }

                if ( !f.isDirectory() && f.length() < entry.getSize() )
                { // file is empty
                    fos = new FileOutputStream( f );
                    while ( zis.available() > 0 )
                    { // write contents
                        fos.write( zis.read() );
                    }
                    fos.close();

                    log.debug( "written " + f.getAbsolutePath() );
                }
            }

            return destDir;
        }
        catch ( final IOException e1 )
        {
            log.error( e1, e1 );
            throw new Error( e1 );
        }
        finally
        {
            IOUtils.closeQuietly( zis );
            IOUtils.closeQuietly( fos );
        }
    }

    /**
     * finds the biggest file in the directory
     * 
     * @param dir directory to search
     * @return the biggest file
     */
    public static File getBiggestFile(
        final File dir )
    {
        File biggest = dir;
        final File[] listFiles = dir.listFiles( FileImporter.UNSUPPORTED_EXT_FILTER );
        if ( listFiles != null )
        {
            for ( final File file : listFiles )
            {
                if ( file.isDirectory() )
                {
                    final File biggestFile = getBiggestFile( file );
                    if ( biggestFile.length() > biggest.length() )
                    {
                        biggest = biggestFile;
                    }
                }
                else if ( file.length() > biggest.length() )
                {
                    biggest = file;
                }
            }
        }
        return biggest;
    }

    /**
     * unpacks the archive to system temp directory and returns the file to open with viewer
     * 
     * @param zipFile zip archive
     * @return file in {java.io.tmpdir}/zipFile.getName()/ to be opened
     */
    public static File getZippedFileToOpen(
        final File zipFile )
    {
        final String destDirName = System.getProperty( "java.io.tmpdir" ) + File.separator + zipFile.getName();

        final File destDir = extractZipFileTo( zipFile.getAbsolutePath(), destDirName );
        return getBiggestFile( destDir );
    }

    /**
     * @param file a file to check
     * @return is file a zip archive
     */
    public static boolean isZip(
        final File file )
    {
        try
        {
            final String contentType = file.toURI().toURL().openConnection().getContentType();
            return "application/zip".equals( contentType );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }
}
