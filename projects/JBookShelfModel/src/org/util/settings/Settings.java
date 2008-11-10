package org.util.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * stores settings
 * 
 * @author eav
 */
public class Settings
    implements
        JBookShelfSettings,
        ThirdPartySettings
{
    private static final String DEFAULTS   = "defaults.properties";

    private static Settings     instance;

    private final Properties    properties = new Properties();

    public static Settings getInstance()
    {
        if ( instance == null )
        {
            instance = new Settings();
        }
        return instance;
    }

    public static void main(
        String[] args )
    {
        getInstance().loadDefaults();
        System.out.println( getInstance().getProperty( IMPORT_MASK ) );
        getInstance().setProperty( IMPORT_MASK, "%a====" );
        System.out.println( getInstance().getProperty( IMPORT_MASK ) );
        getInstance().save( getInstance().getProperty( JBS_FOLDER ) + File.separator + "settings.properties", true );
    }

    private Settings()
    {
        super();
    }

    public String getProperty(
        String key )
    {
        return properties.getProperty( key );
    }

    public void load(
        String file )
    {
        try
        {
            properties.load( new FileInputStream( file ) );
        }
        catch ( Exception e )
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
        catch ( Error e )
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

    public void save(
        String fileName,
        boolean createNewFile )
    {
        try
        {
            File file = new File( fileName );
            if ( !file.exists() && createNewFile )
            {
                file.createNewFile();
            }
            properties.store( new FileOutputStream( file ), "" );
        }
        catch ( Exception e )
        {
            throw new Error( e );
        }
    }

    public void saveAsDefaults()
    {
        save( System.getProperty( "user.dir" ) + File.separator + DEFAULTS, true );
    }

    public Object setProperty(
        String key,
        String value )
    {
        return properties.setProperty( key, value );
    }

    private void createDefaults()
    {
        properties.clear();

        properties.setProperty( JBookShelfSettings.LANGUAGE, "en" );
        properties.setProperty( JBookShelfSettings.LAF, "metal" );
        properties.setProperty( JBookShelfSettings.JBS_FOLDER, System.getProperty( "user.home" ) + File.separator +
            ".jbookshelf" + File.separator );

        File jbookshelf = new File( getProperty( JBS_FOLDER ) );
        if ( !jbookshelf.exists() && !jbookshelf.mkdir() )
        {
            throw new Error( "cannot create jbookshelf folder" );
        }

        properties.setProperty( JBookShelfSettings.IMPORT_MASK, "%a. %b" );
        // todo trick windows
        properties.setProperty( JBookShelfSettings.TEMP_FOLDER, System.getProperty( "java.io.tmpdir" ) );
    }
}
