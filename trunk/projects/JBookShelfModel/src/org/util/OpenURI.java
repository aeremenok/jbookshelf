package org.util;

import java.io.File;

public class OpenURI
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
    }

    public static void browse(
        File file )
    {
        browse( "file:///" + // lack of protocol causes crash under KDE
            file.getAbsolutePath() );
    }

    public static void browse(
        String url )
    {
        if ( url.startsWith( "www" ) )
        {
            url = "http://" + url;
        }

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
}
