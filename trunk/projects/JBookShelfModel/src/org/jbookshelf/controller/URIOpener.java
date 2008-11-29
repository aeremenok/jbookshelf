package org.jbookshelf.controller;

import java.io.File;

import org.eclipse.core.runtime.Assert;

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
        Assert.isTrue( file.isDirectory() );
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
