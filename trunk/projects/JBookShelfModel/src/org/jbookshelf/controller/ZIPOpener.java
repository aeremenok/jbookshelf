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
import java.util.Enumeration;
import java.util.zip.ZipFile;

import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipInputStreamEncoded;

import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.mozilla.universalchardet.UniversalDetector;

public class ZIPOpener
{
    public static void extractZipFiles(
        String filename,
        String destDir )
    {
        System.out.println( "Extracting zip-file" );
        try
        {
            File file = new File( filename );
            String encoding = guessZipEncoding( file );

            ZipInputStreamEncoded zise = new ZipInputStreamEncoded( new FileInputStream( filename ), encoding );
            ZipEntry entry;
            do
            {
                entry = zise.getNextEntry();
                if ( entry != null )
                {
                    File f = new File( destDir + File.separator + entry.getName() );
                    if ( entry.isDirectory() )
                    { // if its a directory, create it
                        f.mkdir();
                    }
                    else
                    {
                        FileOutputStream fos = new FileOutputStream( f );
                        while ( zise.available() > 0 )
                        { // write contents of 'is' to 'fos'
                            fos.write( zise.read() );
                        }
                        fos.close();
                    }
                }
            }
            while ( entry != null );

            zise.close();

            System.out.println( "Zip-file extracted" );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static String guessZipEncoding(
        File file )
    {
        // String encoding = Charset.defaultCharset().displayName();
        // System.out.println( encoding );
        // return "IBM866";
        // todo autodetect

        try
        {
            ZipFile zipFile = new ZipFile( file );
            Enumeration<? extends java.util.zip.ZipEntry> entries = zipFile.entries();
            StringBuilder builder = new StringBuilder();
            while ( entries.hasMoreElements() )
            {
                java.util.zip.ZipEntry zipEntry = entries.nextElement();
                builder.append( zipEntry.getName() );
            }
            String encoding = guessStringEncoding( builder.toString() );
            if ( encoding != null )
            {
                System.out.println( "encoding=" + encoding );
                return encoding;
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return Charset.defaultCharset().name();
    }

    public static File openZip(
        File zipFile )
    {
        extractZipFiles( zipFile.getAbsolutePath(), Settings.getInstance().getProperty( JBookShelfSettings.TEMP_FOLDER ) );
        File extracted = zipFile;
        // todo search for a file to open
        return extracted;
    }

    private static String guessStringEncoding(
        String string )
    {
        UniversalDetector detector = new UniversalDetector( null );
        try
        {
            System.out.println( new String( string.getBytes(), "IBM866" ) );
            byte[] bytes = string.getBytes();
            // ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( bytes );
            // byte[] buf = new byte[4096];
            // int nread;
            // while ( (nread = byteArrayInputStream.read( buf )) > 0 && !detector.isDone() )
            // {
            // detector.handleData( buf, 0, nread );
            // }
            detector.handleData( bytes, 0, bytes.length - 1 );
            detector.dataEnd();

            return detector.getDetectedCharset();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return Charset.defaultCharset().name();
        }
        finally
        {
            detector.reset();
        }
    }
}
