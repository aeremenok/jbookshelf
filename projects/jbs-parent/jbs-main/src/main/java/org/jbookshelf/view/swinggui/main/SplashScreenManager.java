/**
 * 
 */
package org.jbookshelf.view.swinggui.main;

import icons.IMG;

import javax.swing.UIManager;

import org.xnap.commons.gui.SplashWindow;

/**
 * displays a splash screen
 * 
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
        try
        {
            UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
        }
        catch ( final Exception e )
        {
            try
            {
                UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
            }
            catch ( final Exception e1 )
            {}
        }

        final String title = Application.INSTANCE.appName() + " " + Application.INSTANCE.appVersion();
        instance = SplashWindow.createInstance( title, IMG.icon( IMG.SPLASH_PNG, 256 ) );
        instance.setVisible( true );
    }

    public static void stop()
    {
        instance.dispose();
        instance = null;
    }
}
