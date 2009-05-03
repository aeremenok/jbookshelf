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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.mozilla.universalchardet.UniversalDetector;

/**
 * performs basic file and text operations
 * 
 * @author eav
 */
public class FileUtil
{
    public static byte[] getBytesFromFile(
        final File file )
        throws IOException
    {
        final InputStream is = new FileInputStream( file );

        // Get the size of the file
        final long length = file.length();

        if ( length > Integer.MAX_VALUE )
        {
            // todo break into parts?
            throw new IOException( "File is too large: " + file.getAbsolutePath() );
        }

        // Create the byte array to hold the data
        final byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while ( offset < bytes.length && (numRead = is.read( bytes, offset, bytes.length - offset )) >= 0 )
        {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if ( offset < bytes.length )
        {
            throw new IOException( "Could not completely read file " + file.getName() );
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public static String guessFileEncoding(
        final File file )
    {
        final UniversalDetector detector = new UniversalDetector( null );
        try
        {
            final byte[] buf = new byte[4096];
            final FileInputStream fis = new FileInputStream( file );

            int nread;
            while ( (nread = fis.read( buf )) > 0 && !detector.isDone() )
            {
                detector.handleData( buf, 0, nread );
            }
            detector.dataEnd();

            return detector.getDetectedCharset();
        }
        catch ( final Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            detector.reset();
        }
        return Charset.defaultCharset().name();
    }
}
