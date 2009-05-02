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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import org.jbookshelf.controller.singleton.Singleton;

/**
 * stores settings
 * 
 * @author eav
 */
public class Settings
    implements
        JBookShelfSettings,
        Singleton
{
    private static final String DEFAULTS   = "defaults.properties";

    private final Properties    properties = new Properties();

    public File getCollectionFile()
    {
        return new File( getProperty( JBS_FOLDER ) + File.separator + "collection.xml" );
    }

    public String getProperty(
        final String key )
    {
        return properties.getProperty( key );
    }

    public File getSettingsFile()
    {
        return new File( getProperty( JBS_FOLDER ) + File.separator + "settings.properties" );
    }

    public void initSingleton()
    {
        // preparing values
        loadDefaults();

        final File folder = new File( getProperty( JBookShelfSettings.JBS_FOLDER ) );
        final String fileName = getSettingsFile().getAbsolutePath();
        final File file = new File( fileName );
        if ( file.exists() )
        {
            load( fileName );
        }
        else
        {
            if ( !folder.exists() )
            {
                folder.mkdir();
            }
            save( fileName, true );
        }
    }

    public void load(
        final String file )
    {
        try
        {
            properties.load( new FileInputStream( file ) );
        }
        catch ( final Exception e )
        {
            throw new Error( e );
        }
    }

    public void loadDefaults()
    {
        try
        {
            load( System.getProperty( "user.dir" ) + File.separator + DEFAULTS );
        }
        catch ( final Error e )
        {
            if ( e.getCause() instanceof FileNotFoundException )
            {
                createDefaults();
                saveAsDefaults();
            }
            else
            {
                throw e;
            }
        }
    }

    public void save()
    {
        save( getSettingsFile().getAbsolutePath(), true );
    }

    public void save(
        final String fileName,
        final boolean createNewFile )
    {
        try
        {
            final File file = new File( fileName );
            if ( !file.exists() && createNewFile )
            {
                file.createNewFile();
            }
            properties.store( new FileOutputStream( file ), "" );
        }
        catch ( final Exception e )
        {
            System.err.println( "fileName=" + fileName );
            throw new Error( e );
        }
    }

    public void saveAsDefaults()
    {
        save( System.getProperty( "user.dir" ) + File.separator + DEFAULTS, true );
    }

    public Object setProperty(
        final String key,
        final String value )
    {
        return properties.setProperty( key, value );
    }

    private void createDefaults()
    {
        properties.clear();

        properties.setProperty( JBookShelfSettings.LANGUAGE, "English" );
        properties.setProperty( JBookShelfSettings.JBS_FOLDER, System.getProperty( "user.home" ) + File.separator +
            ".jbookshelf" + File.separator );

        final File jbookshelf = new File( getProperty( JBS_FOLDER ) );
        if ( !jbookshelf.exists() && !jbookshelf.mkdir() )
        {
            throw new Error( "cannot create jbookshelf folder" );
        }

        properties.setProperty( JBookShelfSettings.IMPORT_MASK, "%b" );
        // todo trick windows
        properties.setProperty( JBookShelfSettings.TEMP_FOLDER, System.getProperty( "java.io.tmpdir" ) );
    }
}
