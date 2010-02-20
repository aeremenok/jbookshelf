/**
 * 
 */
package org.jbookshelf.view;

import java.awt.Component;

import org.apache.log4j.Logger;
import org.fest.swing.core.GenericTypeMatcher;

/**
 * @author eav 2010
 * @param <T>
 */
public class SingletonMatcher<T extends Component>
    extends GenericTypeMatcher<T>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( SingletonMatcher.class );

    public SingletonMatcher(
        final Class<T> supportedType )
    {
        super( supportedType );
    }

    @Override
    protected boolean isMatching(
        final T component )
    {
        return true;
    }
}
