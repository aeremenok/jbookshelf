/**
 * 
 */
package org.jbookshelf.view.swinggui.main;

import icons.IMG;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.xnap.commons.gui.SplashWindow;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

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
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        }
        catch ( final UnsupportedLookAndFeelException e )
        {}
        final String title = MainWindow.APP_NAME + " " + MainWindow.VERSION;
        instance = SplashWindow.createInstance( title, IMG.icon( IMG.SPLASH_PNG, 256 ) );
        instance.setVisible( true );
    }

    public static void stop()
    {
        instance.dispose();
        instance = null;
    }
}
