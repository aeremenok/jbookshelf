/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.ObjectEvent;

/**
 * @author eav 2009
 */
public class History
    implements
    ListIterator<String>
{
    @SuppressWarnings( "unused" )
    private static final Logger log        = Logger.getLogger( History.class );

    public static final String  NAVIGATION = "NAVIGATION";

    private final List<String>  urls       = new ArrayList<String>();
    private int                 cursor     = -1;

    @Override
    public void add(
        final String e )
    {
        final int indexOf = urls.indexOf( e );
        if ( indexOf == -1 )
        {
            urls.add( e );
            setCursor( urls.size() - 1 );
        }
    }

    public boolean contains(
        final String url )
    {
        return urls.contains( url );
    }

    public String current()
    {
        return urls.get( cursor );
    }

    @Override
    public boolean hasNext()
    {
        return cursor < urls.size() - 1;
    }

    @Override
    public boolean hasPrevious()
    {
        return cursor > 0;
    }

    @Override
    public String next()
    {
        setCursor( nextIndex() );
        return current();
    }

    @Override
    public int nextIndex()
    {
        return cursor + 1;
    }

    @Override
    public String previous()
    {
        setCursor( previousIndex() );
        return current();
    }

    @Override
    public int previousIndex()
    {
        return cursor - 1;
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(
        final String e )
    {
        throw new UnsupportedOperationException();
    }

    public int size()
    {
        return urls.size();
    }

    public Collection<String> getUrls()
    {
        return Collections.unmodifiableCollection( urls );
    }

    private void setCursor(
        final int cursor )
    {
        this.cursor = cursor;
        EventBus.publish( NAVIGATION, new ObjectEvent( this, null ) );
    }
}
