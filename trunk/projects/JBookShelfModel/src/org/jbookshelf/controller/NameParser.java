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

import java.util.HashMap;
import java.util.Map;

import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ReadingUnit;

/**
 * pasres strings, described as wildcards, where<br>
 * <br>
 * %a - name of {@link Author} <br>
 * %b - name of {@link ReadingUnit} <br>
 * %c - name of {@link Category} <br>
 * todo treat filenames more softly, now any mismatch to wildcard causes an error<br>
 * todo optimize parsing
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

    /**
     * extract values from name and put them into map
     * 
     * @param name matches pattern, specified in constructor
     */
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
