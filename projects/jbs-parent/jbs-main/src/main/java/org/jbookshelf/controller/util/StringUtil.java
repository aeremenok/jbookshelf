package org.jbookshelf.controller.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * performs basic string operations
 * 
 * @author eav 2009
 */
public class StringUtil
{
    /**
     * @param e a {@link Throwable}
     * @return its stacktrace
     */
    public static String printThrowable(
        final Throwable e )
    {
        final StringWriter stringWriter = new StringWriter();
        e.printStackTrace( new PrintWriter( stringWriter ) );
        return stringWriter.toString();
    }
}
