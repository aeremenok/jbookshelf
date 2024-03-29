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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * performs basic file and text operations
 * 
 * @author eav
 */
public class FileUtil
{
    private static final Logger log = Logger.getLogger( FileUtil.class );

    /**
     * @param file a file to check
     * @return its possible encoding
     */
    public static String guessFileEncoding(
        final File file )
    {
        try
        {
            return guessStreamEncoding( new FileInputStream( file ) );
        }
        catch ( final FileNotFoundException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    /**
     * @param in a stream to check
     * @return its possible encoding
     */
    public static String guessStreamEncoding(
        final InputStream in )
    {
        final UniversalDetector detector = new UniversalDetector( null );
        InputStream is = null;
        try
        {
            is = new BufferedInputStream( in );
            final byte[] buf = new byte[4096];

            int nread;
            while ( (nread = is.read( buf )) > 0 && !detector.isDone() )
            {
                detector.handleData( buf, 0, nread );
            }
            detector.dataEnd();

            final String detectedCharset = detector.getDetectedCharset();
            log.debug( "detected charset = " + detectedCharset );
            return detectedCharset;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            final String defaultCharset = Charset.defaultCharset().name();
            log.debug( "returning defaultCharset = " + defaultCharset );
            return defaultCharset;
        }
        finally
        {
            detector.reset();
            IOUtils.closeQuietly( is );
        }
    }
}
