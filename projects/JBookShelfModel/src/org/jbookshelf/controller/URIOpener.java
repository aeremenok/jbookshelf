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

/**
 * opens urls in default browser using awt
 * 
 * @author eav
 */
public class URIOpener
{
    static
    {
        if ( !java.awt.Desktop.isDesktopSupported() )
        {
            throw new UnsupportedOperationException( "Desktop is not supported (fatal)" );
        }

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if ( !desktop.isSupported( java.awt.Desktop.Action.BROWSE ) )
        {
            throw new UnsupportedOperationException( "Desktop doesn't support the browse action (fatal)" );
        }
        if ( !desktop.isSupported( java.awt.Desktop.Action.OPEN ) )
        {
            throw new UnsupportedOperationException( "Desktop doesn't support the open action (fatal)" );
        }
    }

    /**
     * @param url <b>must start with protocol under KDE</b> {@link http
     *            ://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6486393}
     * @deprecated use {@link URIOpener#browseFile(File)} or {@link URIOpener#browseHTTP(String)}
     */
    @Deprecated
    public static void browse(
        String url )
    {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        try
        {
            java.net.URI uri = new java.net.URI( url );
            desktop.browse( uri );
        }
        catch ( Exception e )
        {
            throw new Error( e );
        }
    }

    public static void browseFile(
        File file )
    {
        browseFile( file.getAbsolutePath() );
    }

    /**
     * @param path must not start with "file:///" or "http://"
     */
    public static void browseFile(
        String path )
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
        String url )
    {
        browse( "http://" + url );
    }

    public static void openFolder(
        File file )
    {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        try
        {
            desktop.open( file );
        }
        catch ( Exception e )
        {
            throw new Error( e );
        }
    }
}
