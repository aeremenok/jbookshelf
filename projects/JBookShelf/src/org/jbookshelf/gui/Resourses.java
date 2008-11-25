package org.jbookshelf.gui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.text.JTextComponent;

public class Resourses
{
    private static ResourceBundle                bundle     = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" );
    private static final Map<String, JComponent> components = new HashMap<String, JComponent>();
    private static Locale                        currentLocale;

    public static void register(
        Class clazz,
        JComponent component )
    {
        components.put( clazz.getSimpleName() + "." + component.getName(), component );
    }

    public static void unregister(
        Class clazz,
        JComponent component )
    {
        components.remove( clazz.getSimpleName() + "." + component.getName() );
    }

    public static ImageIcon createIcon(
        String fileName )
    {
        return new ImageIcon( Resourses.class.getResource( "/org/jbookshelf/gui/images/" + fileName ) );
    }

    public static ImageIcon createSpecificIcon(
        String url )
    {
        return new ImageIcon( Resourses.class.getResource( url ) );
    }

    public static String getString(
        Class clazz,
        String name )
    {
        return getString( clazz.getSimpleName(), name );
    }

    public static String getString(
        Class clazz,
        JComponent component )
    {
        return getString( clazz.getSimpleName(), component.getName() );
    }

    public static String getString(
        String className,
        String name )
    {
        return getString( className + "." + name );
    }

    public static String getString(
        String fullName )
    {
        return bundle.getString( fullName + ".text" );
    }

    /**
     * stores {@link LookAndFeel} classNames
     */
    private static Map<String, String> lafClassNames = new HashMap<String, String>();
    /**
     * stores {@link LookAndFeel} classNames
     */
    public static final String[]       LAF_NAMES     = getLAFs();

    /**
     * @return {@link LookAndFeel} names for combobox
     */
    private static String[] getLAFs()
    {
        LookAndFeelInfo[] installed = UIManager.getInstalledLookAndFeels();
        lafClassNames.clear();
        String[] lafs = new String[installed.length];
        for ( int i = 0; i < installed.length; i++ )
        {
            LookAndFeelInfo lookAndFeelInfo = installed[i];
            lafs[i] = lookAndFeelInfo.getName();
            lafClassNames.put( lafs[i], lookAndFeelInfo.getClassName() );
        }
        return lafs;
    }

    public static void switchLanguage(
        String language )
    {
        if ( language.equals( "Russian" ) )
        {
            currentLocale = new Locale( "ru", "RU" );
        }
        else
        {
            currentLocale = new Locale( "en", "US" );
        }
        bundle = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle", currentLocale );

        for ( String fullName : components.keySet() )
        {
            JComponent component = components.get( fullName );
            String text = getString( fullName );

            if ( component instanceof JLabel )
            {
                ((JLabel) component).setText( text );
            }
            else if ( component instanceof JTextComponent )
            {
                ((JTextComponent) component).setText( text );
            }
            else if ( component instanceof JButton )
            {
                ((JButton) component).setText( text );
            }
        }
    }

    public static Locale getCurrentLocale()
    {
        return currentLocale;
    }

    public static String getLAFClassName(
        String lafName )
    {
        return lafClassNames.get( lafName );
    }
}
