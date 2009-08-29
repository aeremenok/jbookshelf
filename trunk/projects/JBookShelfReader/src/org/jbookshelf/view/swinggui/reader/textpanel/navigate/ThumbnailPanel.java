/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;

/**
 * @author eav 2009
 * @param <PageType>
 */
public class ThumbnailPanel<PageType>
    extends JPanel
{
    @SuppressWarnings( "unused" )
    private static final Logger      log = Logger.getLogger( ThumbnailPanel.class );
    protected ReaderWindow<PageType> readerWindow;

    public ThumbnailPanel(
        final ReaderWindow<PageType> readerWindow )
    {
        super();
        this.readerWindow = readerWindow;
        // todo
        add( new JButton( "thumbnails" ) );
    }
}
