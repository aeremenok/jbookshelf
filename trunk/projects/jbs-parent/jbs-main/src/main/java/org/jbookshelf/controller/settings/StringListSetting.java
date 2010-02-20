/**
 * 
 */
package org.jbookshelf.controller.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xnap.commons.settings.AbstractSetting;
import org.xnap.commons.settings.SettingResource;
import org.xnap.commons.settings.Validator;

/**
 * stores list of strings (i.e. import masks)
 * 
 * @author eav 2009
 */
public class StringListSetting
    extends AbstractSetting<List<String>>
{
    /**
     * masks denote filenames, so no mask should contain this char
     */
    private static final String SEPARATOR = ":";

    public StringListSetting(
        final SettingResource backstore,
        final String key,
        final String[] defaultValue )
    {
        super( backstore, key, Arrays.asList( defaultValue ), null );
    }

    public StringListSetting(
        final SettingResource backstore,
        final String key,
        final String[] defaultValue,
        final Validator validator )
    {
        super( backstore, key, Arrays.asList( defaultValue ), validator );
    }

    @Override
    protected List<String> fromString(
        final String string )
    {
        if ( string.length() == 0 )
        {
            return new ArrayList<String>();
        }
        return Arrays.asList( string.split( SEPARATOR ) );
    }

    @Override
    protected String toString(
        final List<String> strings )
    {
        final StringBuilder builder = new StringBuilder();
        for ( final String string : strings )
        {
            builder.append( string ).append( SEPARATOR );
        }
        if ( strings.size() > 0 )
        {
            builder.deleteCharAt( builder.length() - 1 );
        }
        return builder.toString();
    }
}
