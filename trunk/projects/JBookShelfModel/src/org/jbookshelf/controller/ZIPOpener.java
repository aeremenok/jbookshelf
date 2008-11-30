/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZIPOpener
{
    public static File openZip(
        File zipFile )
    {
        try
        {
            ZipInputStream in = new ZipInputStream( new FileInputStream( zipFile ) );

            ZipEntry entry = in.getNextEntry();

            String outFilename = System.getProperty( "java.io.tmpdir" ) + "/" + entry.getName();
            File unzipped = new File( outFilename );
            if ( unzipped.exists() && entry.getSize() == unzipped.length() )
            {
                return unzipped;
            }

            FileOutputStream out = new FileOutputStream( unzipped );

            byte[] buf = new byte[1024];
            int len;
            while ( (len = in.read( buf )) > 0 )
            {
                out.write( buf, 0, len );
            }

            out.close();
            in.close();

            return unzipped;
        }
        catch ( IOException e )
        {
            throw new Error( e );
        }
    }
}
