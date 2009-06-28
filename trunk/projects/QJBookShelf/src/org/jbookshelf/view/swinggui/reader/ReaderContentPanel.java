/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 */
public class ReaderContentPanel
    extends JPanel
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( ReaderContentPanel.class );
    @SuppressWarnings( "unused" )
    private final ReaderWindow  readerWindow;

    public ReaderContentPanel(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;
    }
}
