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
package org.jbookshelf.controller;

import java.io.File;
import java.io.IOException;

import org.jbookshelf.controller.singleton.Singleton;
import org.xnap.commons.settings.PropertyResource;
import org.xnap.commons.settings.StringSetting;

/**
 * stores settings
 * 
 * @author eav
 */
public class Settings
    extends PropertyResource
    implements
        Singleton
{
    public final StringSetting LANGUAGE;
    public final StringSetting LAF;
    public final StringSetting TEMP_DIR;
    public final StringSetting JBS_DIR;
    public final StringSetting IMPORT_MASK;

    {
        LANGUAGE = new StringSetting( this, "language", "English" );
        LAF = new StringSetting( this, "laf", "Plastique" );
        IMPORT_MASK = new StringSetting( this, "import_mask", "%b" );
        TEMP_DIR = new StringSetting( this, "temp_folder", System.getProperty( "java.io.tmpdir" ) );
        final String jbsDir = System.getProperty( "user.home" ) + File.separator + ".jbookshelf" + File.separator;
        JBS_DIR = new StringSetting( this, "jbs_folder", jbsDir );
    }

    public File getCollectionFile()
    {
        return new File( JBS_DIR.getValue() + File.separator + "collection.xml" );
    }

    public File getSettingsFile()
    {
        return new File( JBS_DIR.getValue() + File.separator + "settings.properties" );
    }

    public void initSingleton()
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
}
