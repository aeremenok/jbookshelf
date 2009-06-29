/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import javax.swing.JToolBar;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 */
public class ReaderToolBar
    extends JToolBar
{
    @SuppressWarnings( "unused" )
    private static final Logger log       = Logger.getLogger( ReaderToolBar.class );
    private final ReaderWindow  readerWindow;

    private final Scalator      scalator  = new Scalator( 50, 200, 50 );
    private final Paginator     paginator = new Paginator()
                                          {
                                              @Override
                                              protected void pageChanged(
                                                  final int page )
                                              {
                                              // todo
                                              }
                                          };

    public ReaderToolBar(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;
        add( scalator );
        addSeparator();
        add( paginator );
    }

    /**
     * @return the readerWindow
     */
    public ReaderWindow getReaderWindow()
    {
        return this.readerWindow;
    }
}
