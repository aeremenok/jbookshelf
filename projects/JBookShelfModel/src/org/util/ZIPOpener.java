package org.util;

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
