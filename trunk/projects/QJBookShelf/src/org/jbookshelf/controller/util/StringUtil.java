package org.jbookshelf.controller.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.mozilla.universalchardet.UniversalDetector;

public class StringUtil
{
    /**
     * tries to guess the encoding of the {@link String}
     * 
     * @param string a string to guess
     * @return charset name
     */
    public static String guessStringEncoding(
        final String string )
    {
        final UniversalDetector detector = new UniversalDetector( null );
        try
        { // todo optimize
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

    public static String printThrowable(
        final Throwable e )
    {
        final StringWriter stringWriter = new StringWriter();
        e.printStackTrace( new PrintWriter( stringWriter ) );
        return stringWriter.toString();
    }
}
