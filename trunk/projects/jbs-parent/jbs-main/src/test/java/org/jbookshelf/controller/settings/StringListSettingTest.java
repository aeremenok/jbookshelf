/**
 * 
 */
package org.jbookshelf.controller.settings;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xnap.commons.settings.PropertyResource;
import org.xnap.commons.settings.SettingResource;

/**
 * @author eav 2010
 */
public class StringListSettingTest
{
    private StringListSetting setting;

    @BeforeClass
    public void setUp()
    {
        final SettingResource backstore = new PropertyResource();
        setting = new StringListSetting( backstore, "testkey", new String[0] );
    }

    @Test
    public void defaultValue()
    {
        final List<String> defaultValue = setting.getDefaultValue();
        assert defaultValue.isEmpty();
    }

    @Test
    public void testFromString()
    {
        final List<String> strings = setting.fromString( "x:y:z" );
        assertEquals( strings, Arrays.asList( "x", "y", "z" ) );
    }

    @Test
    public void testToString()
    {
        final String string = setting.toString( Arrays.asList( "x", "y", "z" ) );
        assertEquals( string, "x:y:z" );
    }
}
