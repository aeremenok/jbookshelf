/**
 * 
 */
package org.jbookshelf.view.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eav 2009
 */
public class Parameters
{
    public enum Keys
    {
        SEARCH_TEXT,
        SEARCH_IS_READ,
        SEARCH_CONTENT;
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

}
