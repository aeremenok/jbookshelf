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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipInputStream;
import net.sf.jazzlib.ZipInputStreamEncoded;

import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Singletons;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * handles zip-files
 * 
 * @author eav
 */
public class ZIPHandler
{
    /**
     * extracts the specified zip-file to the specified directory
     * 
     * @param srcFilename source file name
     * @param destDir destination directory name
     */
    public static void extractZipFiles(
        final String srcFilename,
        final String destDir )
    {
        try
        {
            final File file = new File( srcFilename );
            final String encoding = guessZipEncoding( file );
            final ZipInputStreamEncoded zise = new ZipInputStreamEncoded( new FileInputStream( file ), encoding );
            ZipEntry entry = zise.getNextEntry();
            while ( entry != null )
            {
                final File f = new File( destDir + File.separator + entry.getName() );
                if ( entry.isDirectory() && !f.exists() )
                { // a directory doesn't exist, create it
                    f.mkdir();
                }
                else if ( !f.exists() || f.length() < entry.getSize() )
                { // a file doesn't exist or is empty, write it
                    final FileOutputStream fos = new FileOutputStream( f );
                    while ( zise.available() > 0 )
                    { // write contents
                        fos.write( zise.read() );
                    }
                    fos.close();
                }
                entry = zise.getNextEntry();
            }

            zise.close();
        }
        catch ( final Exception e )
        {
            throw new Error( e );
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
        final File[] listFiles = dir.listFiles( new FileImporter.ExtensionDenyingFilter() );
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

    public static File getZippedFileToOpen(
        final File zipFile )
    {
        final Settings settings = Singletons.instance( Settings.class );
        final String destDir =
            settings.getProperty( JBookShelfSettings.TEMP_FOLDER ) + File.separator + zipFile.getName();
        new File( destDir ).mkdir();
        extractZipFiles( zipFile.getAbsolutePath(), destDir );
        return getBiggestFile( new File( destDir ) );
    }

    /**
     * tries to guess the encoding used by an archive
     * 
     * @param file zip-file
     * @return approximate encoding
     */
    public static String guessZipEncoding(
        final File file )
    {
        try
        {
            final ZipInputStream stream = new ZipInputStream( new FileInputStream( file ) );
            final StringBuilder builder = new StringBuilder();
            ZipEntry entry = stream.getNextEntry();
            while ( entry != null )
            {
                builder.append( entry.getName() );
                entry = stream.getNextEntry();
            }
            final String encoding = guessStringEncoding( builder.toString() );
            if ( encoding != null )
            {
                return encoding;
            }
        }
        catch ( final Exception e )
        { // do not interrupt hoping that the defaultCharset will fit
            e.printStackTrace();
        }
        return Charset.defaultCharset().name();
    }

    /**
     * tries to guess the encoding of the {@link String}
     * 
     * @param string a string to guess
     * @return charset name
     */
    private static String guessStringEncoding(
        final String string )
    {
        final UniversalDetector detector = new UniversalDetector( null );
        try
        {
            final byte[] bytes = string.getBytes();
            detector.handleData( bytes, 0, bytes.length - 1 );
            detector.dataEnd();

            return detector.getDetectedCharset();
        }
        catch ( final Exception e )
        { // do not interrupt hoping that the defaultCharset will fit
            e.printStackTrace();
        }
        finally
        {
            detector.reset();
        }
        return Charset.defaultCharset().name();
    }
}
