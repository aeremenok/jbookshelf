/**
 * 
 */
package org.jbookshelf.controller.settings;

import org.xnap.commons.settings.AbstractSetting;
import org.xnap.commons.settings.SettingResource;
import org.xnap.commons.settings.Validator;

/**
 * @author eav 2009
 */
public class MasksSetting
    extends AbstractSetting<String[]>
{
    /**
     * masks denote filenames, so no mask should contain this char
     */
    private static final String SEPARATOR = ":";

    /**
     * @param backstore
     * @param key
     * @param defaultValue
     */
    public MasksSetting(
        final SettingResource backstore,
        final String key,
        final String[] defaultValue )
    {
        super( backstore, key, defaultValue, null );
    }

    /**
     * @param backstore
     * @param key
     * @param defaultValue
     * @param validator
     */
    public MasksSetting(
        final SettingResource backstore,
        final String key,
        final String[] defaultValue,
        final Validator validator )
    {
        super( backstore, key, defaultValue, validator );
    }

    /* (non-Javadoc)
     * @see org.xnap.commons.settings.AbstractSetting#fromString(java.lang.String)
     */
    @Override
    protected String[] fromString(
        final String string )
    {
        if ( string.length() == 0 )
        {
            return new String[0];
        }
        return string.split( SEPARATOR );
    }

    /* (non-Javadoc)
     * @see org.xnap.commons.settings.AbstractSetting#toString(java.lang.Object)
     */
    @Override
    protected String toString(
        final String[] strings )
    {
        final StringBuilder builder = new StringBuilder();
        for ( final String string : strings )
        {
            builder.append( string ).append( SEPARATOR );
        }
        if ( strings.length > 0 )
        {
            builder.deleteCharAt( builder.length() - 1 );
        }
        return builder.toString();
    }
}
