/**
 * 
 */
package org.jbookshelf.view.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * universal parameter container to pass between objects
 * 
 * @author eav 2009
 */
public class Parameters
{
    /**
     * parametes keys
     * 
     * @author eav 2009
     */
    public enum Keys
    {
        SEARCH_TEXT, SEARCH_IS_READ, SEARCH_CONTENT,

        BOOK_NAME, BOOK_IS_READ, BOOK_FILE, BOOK_AUTHORS, BOOK_CATEGORIES, BOOK_VIEWER,

        SEARCH_DIRECTION,

        IMPORT_ROOTS, IMPORT_STRATEGY
    }

    private final Map<String, Object> map = new HashMap<String, Object>();

    /**
     * @param <T> value type
     * @param key the key
     * @return the value
     * @see java.util.Map#get(java.lang.Object)
     */
    @SuppressWarnings( "unchecked" )
    public <T> T get(
        final Keys key )
    {
        return (T) this.map.get( key.name() );
    }

    /**
     * @param <T> value type
     * @param key the key
     * @param value the value
     * @return the value
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings( "unchecked" )
    public <T> T put(
        final Keys key,
        final T value )
    {
        return (T) this.map.put( key.name(), value );
    }

    @Override
    public String toString()
    {
        return map.toString();
    }
}
