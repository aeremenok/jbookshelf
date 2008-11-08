package org.util;

import java.util.HashMap;
import java.util.Map;

import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.ReadingUnit;

/**
 * pasres strings, described as wildcards, where<br>
 * <br>
 * %a - name of {@link Author} <br>
 * %b - name of {@link ReadingUnit} <br>
 * %c - name of {@link Category}
 * 
 * @author eav
 */
public class NameParser
{
    private final String[]               delims;

    private final Map<Character, String> values = new HashMap<Character, String>();

    public NameParser(
        String pattern )
    {
        // prepare delimiters
        delims = pattern.split( "%" );
    }

    public String getAuthorName()
    {
        return values.get( 'a' );
    }

    public String getBookName()
    {
        return values.get( 'b' );
    }

    public String getCategoryName()
    {
        return values.get( 'c' );
    }

    public void parse(
        String name )
    {
        // cut delimiters at edges
        String right = name.substring( delims[0].length(), name.length() - delims[delims.length - 1].length() + 1 );

        for ( int i = 1; i < delims.length - 1; i++ )
        {
            String delim = delims[i].substring( 1 );
            String[] split = split( right, delim );
            // left is a wildcard value
            values.put( delims[i].charAt( 0 ), split[0] );
            right = split[1];
        }
        // remainder is a wildcard value too
        values.put( delims[delims.length - 1].charAt( 0 ), right );
    }

    /**
     * split only at first delimiter
     * 
     * @param string string to split
     * @param delim delimiter
     * @return array from string's left and right
     */
    private String[] split(
        String string,
        String delim )
    {
        String left = string.substring( 0, string.indexOf( delim ) );
        String right = string.substring( string.indexOf( delim ) + delim.length() );
        return new String[] { left, right };
    }
}
