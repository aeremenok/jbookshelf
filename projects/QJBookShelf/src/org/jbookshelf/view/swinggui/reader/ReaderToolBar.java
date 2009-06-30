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
    private static final Logger log        = Logger.getLogger( ReaderToolBar.class );
    private final ReaderWindow  readerWindow;

    private final Scalator      scalator   = new Scalator( 50, 200, 50 );
    private final Paginator     paginator  = new Paginator();
    private final TextFinder    textFinder = new TextFinder();

    public ReaderToolBar(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;
        add( scalator );
        addSeparator();
        add( paginator );
        addSeparator();
        add( textFinder );
    }

    public Paginator getPaginator()
    {
        return paginator;
    }

    /**
     * @return the readerWindow
     */
    public ReaderWindow getReaderWindow()
    {
        return this.readerWindow;
    }

    public Scalator getScalator()
    {
        return scalator;
    }

    public TextFinder getTextFinder()
    {
        return textFinder;
    }
}
