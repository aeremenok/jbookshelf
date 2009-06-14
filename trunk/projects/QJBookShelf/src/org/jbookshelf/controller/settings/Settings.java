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
package org.jbookshelf.controller.settings;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.MainWindow;
import org.xnap.commons.settings.PropertyResource;
import org.xnap.commons.settings.StringSetting;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * stores settings
 * 
 * @author eav
 */
public class Settings
    extends PropertyResource
{
    public StringSetting     LANGUAGE;
    public StringSetting     LAF;
    public StringSetting     JBS_DIR;
    public StringSetting     WORKSPACE_DIR;
    public StringListSetting IMPORT_MASKS;
    public StringListSetting ZIP_ENCODINGS;

    public File getCollectionFile()
    {
        return new File( JBS_DIR.getValue() + File.separator + "collection.xml" );
    }

    @PostConstruct
    public void initSingleton()
    {
        initDefaults();
        initFile();

        addPropertyChangeListener( LANGUAGE.getKey(), I18N.LANGUAGE_LISTENER );
        addPropertyChangeListener( LAF.getKey(), Single.instance( MainWindow.class ) );

        firePropertyChange( LANGUAGE.getKey(), "", LANGUAGE.getValue() );
        firePropertyChange( LAF.getKey(), "", LAF.getValue() );
        firePropertyChange( JBS_DIR.getKey(), "", JBS_DIR.getValue() );
        firePropertyChange( IMPORT_MASKS.getKey(), "", IMPORT_MASKS.getValue() );
    }

    public void load()
    {
        try
        {
            load( getSettingsFile() );
        }
        catch ( final IOException e )
        {
            throw new Error( e );
        }
    }

    public void save()
    {
        try
        {
            store( getSettingsFile() );
        }
        catch ( final IOException e )
        {
            throw new Error( e );
        }
    }

    private File getSettingsFile()
    {
        return new File( JBS_DIR.getValue() + File.separator + "settings.properties" );
    }

    private void initDefaults()
        throws Error
    {
        LANGUAGE = new StringSetting( this, "language", I18N.defaultLanguage() );
        IMPORT_MASKS = new StringListSetting( this, "import_masks", new String[]
        { "%a. %b", "%b" } );
        ZIP_ENCODINGS = new StringListSetting( this, "zip_encodings", new String[]
        { "cp1251", "cp1252", "IBM866", "KOI8-R", "KOI8-U" } );

        final String jbsDir = System.getProperty( "user.home" ) + File.separator + ".jbookshelf" + File.separator;
        JBS_DIR = new StringSetting( this, "jbs_dir", jbsDir );
        WORKSPACE_DIR = new StringSetting( this, "workspace_dir", System.getProperty( "user.home" ) );

        String lafName;
        try
        {
            lafName = new NimbusLookAndFeel().getName();
        }
        catch ( final Throwable e )
        { // NimbusLookAndFeel does not exist, jre < 1.6.10
            try
            {
                final Object laf = Class.forName( UIManager.getSystemLookAndFeelClassName() ).newInstance();
                lafName = ((LookAndFeel) laf).getName();
            }
            catch ( final Exception e1 )
            {
                throw new Error( e1 );
            }
        }
        LAF = new StringSetting( this, "laf", lafName );
    }

    private void initFile()
        throws Error
    {
        // preparing values
        final File dir = new File( JBS_DIR.getValue() );
        final File settingsFile = getSettingsFile();
        try
        {
            if ( settingsFile.exists() )
            { // settings exist - load them
                load( settingsFile );
            }
            else
            {
                if ( !dir.exists() )
                { // create directory
                    dir.mkdir();
                }
                // create new file
                store( settingsFile );
            }
        }
        catch ( final IOException e )
        {
            throw new Error( e );
        }
    }
}
