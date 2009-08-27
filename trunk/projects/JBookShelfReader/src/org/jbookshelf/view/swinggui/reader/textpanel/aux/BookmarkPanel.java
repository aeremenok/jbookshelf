/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.aux;

import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;

/**
 * @author eav 2009
 */
public class BookmarkPanel
    extends JPanel
    implements
    Features
{
    @SuppressWarnings( "unused" )
    private static final Logger         log                   = Logger.getLogger( BookmarkPanel.class );

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport( this );

    public BookmarkPanel()
    {
        super();
        // todo 
        add( new JButton( "bookmarks" ) );
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport()
    {
        return propertyChangeSupport;
    }

}
