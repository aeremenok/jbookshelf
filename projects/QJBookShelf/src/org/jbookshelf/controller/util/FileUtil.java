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
import java.io.ByteArrayInputStream;
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
     * @param content a byte array to check
     * @return its possible encoding
     */
    public static String guessByteArrayEncoding(
        final byte[] content )
    {
        final UniversalDetector detector = new UniversalDetector( null );
        InputStream is = null;
        try
        {
            is = new BufferedInputStream( new ByteArrayInputStream( content ) );
            final byte[] buf = new byte[4096];

            int nread;
            while ( (nread = is.read( buf )) > 0 && !detector.isDone() )
            {
                detector.handleData( buf, 0, nread );
            }
            detector.dataEnd();

            return detector.getDetectedCharset();
        }
        catch ( final Exception e )
        {
            log.error( e, e );
        }
        finally
        {
            detector.reset();
            IOUtils.closeQuietly( is );
        }

        return Charset.defaultCharset().name();
    }
}
