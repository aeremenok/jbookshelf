/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 */
public class NotesPanel
    extends JToolBar
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( NotesPanel.class );

    public NotesPanel()
    {
        super( SwingConstants.VERTICAL );

        // todo 
        add( new JButton( "note1" ) );
        add( new JButton( "note2" ) );
    }

}
