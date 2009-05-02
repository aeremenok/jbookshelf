package org.jbookshelf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.mozilla.universalchardet.UniversalDetector;

/**
 * performs basic file and text operations
 * 
 * @author eav
 */
public class FileHandler
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

    public static String guessEncoding(
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

    public static String printThrowable(
        final Throwable e )
    {
        final StringWriter stringWriter = new StringWriter();
        e.printStackTrace( new PrintWriter( stringWriter ) );
        return stringWriter.toString();
    }
}
