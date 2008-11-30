/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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
package org.jbookshelf.gui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.text.JTextComponent;

public class Resourses
{
    private static ResourceBundle                bundle        = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" );
    private static final Map<String, JComponent> components    = new HashMap<String, JComponent>();
    private static Locale                        currentLocale;

    /**
     * stores {@link LookAndFeel} classNames
     */
    private static Map<String, String>           lafClassNames = new HashMap<String, String>();

    /**
     * stores {@link LookAndFeel} classNames
     */
    public static final String[]                 LAF_NAMES     = getLAFs();

    public static ImageIcon createIcon(
        String fileName )
    {
        return createSpecificIcon( "/org/jbookshelf/gui/images/" + fileName );
    }

    public static ImageIcon createSpecificIcon(
        String url )
    {
        return new ImageIcon( Resourses.class.getResource( url ) );
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

    public static String getSpecificString(
        String fullName )
    {
        return bundle.getString( fullName );
    }

    public static String getString(
        Class clazz,
        JComponent component )
    {
        return getString( clazz.getSimpleName(), component.getName() );
    }

    public static String getString(
        Class clazz,
        String name )
    {
        return getString( clazz.getSimpleName(), name );
    }

    public static String getString(
        String fullName )
    {
        return bundle.getString( fullName + ".text" );
    }

    public static String getString(
        String className,
        String name )
    {
        return getString( className + "." + name );
    }

    public static void register(
        Class clazz,
        JComponent component )
    {
        components.put( clazz.getSimpleName() + "." + component.getName(), component );
        setText( component, getString( clazz, component ) );
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
            setText( component, getString( fullName ) );
        }
    }

    public static void unregister(
        Class clazz,
        JComponent component )
    {
        components.remove( clazz.getSimpleName() + "." + component.getName() );
    }

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

    private static void setText(
        JComponent component,
        String text )
    {
        if ( component instanceof JLabel )
        {
            ((JLabel) component).setText( text );
        }
        else if ( component instanceof JTextComponent )
        {
            ((JTextComponent) component).setText( text );
        }
        else if ( component instanceof AbstractButton )
        {
            ((AbstractButton) component).setText( text );
        }
    }
}
