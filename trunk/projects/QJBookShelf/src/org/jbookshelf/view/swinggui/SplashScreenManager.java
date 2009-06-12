/**
 * 
 */
package org.jbookshelf.view.swinggui;

import images.IMG;

import org.xnap.commons.gui.SplashWindow;

/**
 * @author eav 2009
 */
public class SplashScreenManager
{
    private static SplashWindow instance;

    public static void setProgress(
        final int frame )
    {
        SplashWindow.setProgress( frame );
    }

    public static void start()
    {
        instance = SplashWindow.createInstance( MainWindow.APP_NAME + " " + MainWindow.VERSION, IMG
            .icon( "logo-256.png" ) );
        instance.setVisible( true );
    }

    public static void stop()
    {
        instance.dispose();
        instance = null;
    }
}
