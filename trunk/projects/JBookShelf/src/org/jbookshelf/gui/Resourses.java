package org.jbookshelf.gui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class Resourses
{
    private static ResourceBundle                bundle     = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
    private static final Map<String, JComponent> components = new HashMap<String, JComponent>();

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

    public static void switchLanguaage(
        String language )
    {
        if ( language.equals( "Russian" ) )
        {
            bundle = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle", new Locale( "ru", "RU" ) );
        }
        else
        {
            bundle = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle", new Locale( "en", "US" ) );
        }

        for ( String fullName : components.keySet() )
        {
            JComponent component = components.get( fullName );

            if ( component instanceof JLabel )
            {
                ((JLabel) component).setText( getString( fullName ) );
            }
            else if ( component instanceof JTextComponent )
            {
                ((JTextComponent) component).setText( getString( fullName ) );
            }
        }
    }
}
