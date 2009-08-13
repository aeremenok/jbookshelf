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

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * opens urls in default browser using awt
 * 
 * @author eav
 */
public class URIUtil
{
    private static final Logger log = Logger.getLogger( URIUtil.class );

    /**
     * @param url <b>must start with protocol under KDE</b>
     *            {@link "http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6486393"}
     * @deprecated use {@link URIUtil#browseFile(File)} or {@link URIUtil#browseHTTP(String)}
     */
    @Deprecated
    public static void browse(
        final String url )
    {
        final java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        try
        {
            final java.net.URI uri = new java.net.URI( url );
            desktop.browse( uri );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    public static void browseFile(
        final File file )
    {
        browseFile( file.getAbsolutePath() );
    }

    /**
     * @param path must not start with "file:///" or "http://"
     */
    public static void browseFile(
        final String path )
    {
        String url = "file:///" + path;
        url = url.replaceAll( " ", "%20" );
        url = url.replaceAll( "\\\\", "/" );
        browse( url );
    }

    /**
     * @param url must not start with "http://"
     */
    public static void browseHTTP(
        final String url )
    {
        browse( "http://" + url );
    }

    /**
     * search text in google
     * 
     * @param query text to search
     */
    public static void google(
        final String query )
    {
        browse( "http://www.google.com/search?q=" + toSearchEngineQuery( query ) );
    }

    @SuppressWarnings( "deprecation" )
    public static void mail(
        final String address,
        final String subject,
        final String body )
    {
        try
        {
            final StringBuilder uri = new StringBuilder( "mailto:" ).append( address ).append( "?" );
            uri.append( "subject=" ).append( subject );
            uri.append( "&body=" ).append( URLEncoder.encode( body ).replaceAll( "\\+", "%20" ) );

            Desktop.getDesktop().mail( URI.create( uri.toString() ) );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    /**
     * @param directory a directory to open with system file manager
     */
    public static void openDir(
        final File directory )
    {
        final java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        try
        {
            desktop.open( directory );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    /**
     * splits the query into tokens and concatenates them using "+", removes unicode paragraph characters
     * 
     * @param string query
     * @return single line query
     */
    private static String toSearchEngineQuery(
        final String string )
    {
        final StringBuilder builder = new StringBuilder();
        final StringTokenizer tokenizer = new StringTokenizer( string );
        while ( tokenizer.hasMoreTokens() )
        {
            // unicode paragraph character may be contained
            builder.append( tokenizer.nextToken().replaceAll( "\u2029", "" ) );
            builder.append( "+" );
        }
        return builder.toString();
    }
}
