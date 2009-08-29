/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 */
public class BookmarkPanel
    extends JPanel
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookmarkPanel.class );

    public BookmarkPanel()
    {
        super();
        // todo 
        add( new JButton( "bookmarks" ) );
    }
}
