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

import static org.jbookshelf.controller.singleton.Single.instance;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.util.JBSSystem;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.widget.model.LookAndFeelComboBoxModel;
import org.xnap.commons.settings.EnumSetting;
import org.xnap.commons.settings.IntSetting;
import org.xnap.commons.settings.PropertyResource;
import org.xnap.commons.settings.StringSetting;

/**
 * stores settings
 * 
 * @author eav
 */
public class Settings
    extends PropertyResource
{
    private static final Logger    log = Logger.getLogger( Settings.class );

    public StringSetting           LANGUAGE;
    public StringSetting           LAF;
    public StringSetting           JBS_DIR;
    public StringSetting           WORKSPACE_DIR;
    public StringListSetting       IMPORT_MASKS;

    public EnumSetting<Proxy.Type> PROXY_TYPE;
    public StringSetting           PROXY_HOST;
    public IntSetting              PROXY_PORT;

    @PostConstruct
    public void initSingleton()
    {
        initDefaults();
        initFile();
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

    /**
     * @return a file that stores settings
     */
    private File getSettingsFile()
    {
        return new File( JBS_DIR.getValue() + File.separator + "settings.properties" );
    }

    /**
     * creates default values
     */
    @SuppressWarnings( "unchecked" )
    private void initDefaults()
    {
        LANGUAGE = new StringSetting( this, "language", I18N.defaultLanguage() );
        IMPORT_MASKS = new StringListSetting( this, "import_masks", "%a. %b", "%b" );

        final JBSSystem system = instance( JBSSystem.class );

        final String home = system.userHome().replaceAll( "\\\\", "/" );
        final String jbsDir = home + "/.jbookshelf/";
        JBS_DIR = new StringSetting( this, "jbs_dir", jbsDir );
        WORKSPACE_DIR = new StringSetting( this, "workspace_dir", system.myDocs() );

        PROXY_TYPE = new EnumSetting( this, "PROXY_TYPE", Proxy.Type.DIRECT );
        PROXY_HOST = new StringSetting( this, "PROXY_HOST", "proxy" );
        PROXY_PORT = new IntSetting( this, "PROXY_PORT", 3128 );

        LAF = new StringSetting( this, "laf", LookAndFeelComboBoxModel.pickFirstLAFName() );
    }

    /**
     * read the settings file if it exists. if not - create it
     */
    private void initFile()
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

                    log.debug( "created dir " + dir );
                    assert dir.exists() : dir.toString();
                }
                // create new file
                settingsFile.createNewFile();
                store( settingsFile );

                log.debug( "settings saved to " + settingsFile );
                assert settingsFile.exists() : settingsFile.toString();
            }
        }
        catch ( final IOException e )
        {
            throw new Error( e );
        }
    }
}
