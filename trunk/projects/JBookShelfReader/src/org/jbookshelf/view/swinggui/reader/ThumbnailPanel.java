/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.toolbar.Features;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class ThumbnailPanel<PageType>
    extends JPanel
    implements
    Features
{
    @SuppressWarnings( "unused" )
    private static final Logger         log                   = Logger.getLogger( ThumbnailPanel.class );
    protected ReaderWindow<PageType>    readerWindow;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport( this );

    public ThumbnailPanel(
        final ReaderWindow<PageType> readerWindow )
    {
        super();
        this.readerWindow = readerWindow;
        // todo
        add( new JButton( "thumbnails" ) );
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport()
    {
        return propertyChangeSupport;
    }

}
