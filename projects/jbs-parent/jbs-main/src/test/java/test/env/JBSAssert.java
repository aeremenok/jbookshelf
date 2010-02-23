/**
 * 
 */
package test.env;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;

import java.io.File;

/**
 * @author eav 2010
 */
public class JBSAssert
{
    public static void assertFileEquals(
        final String actualFileName,
        final String expectedFileName )
    {
        assertEquals( new File( actualFileName ), new File( expectedFileName ) );
    }

    public static void assertFileNotSame(
        final String actualFileName,
        final String expectedFileName )
    {
        assertNotSame( new File( actualFileName ), new File( expectedFileName ) );
    }
}
