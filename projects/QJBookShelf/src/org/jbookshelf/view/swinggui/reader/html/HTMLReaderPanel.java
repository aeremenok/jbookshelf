/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.html;

import java.awt.BorderLayout;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;

import javax.swing.JScrollPane;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.parser.DocumentBuilderImpl;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;
import org.lobobrowser.html.test.SimpleUserAgentContext;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author eav 2009
 */
public class HTMLReaderPanel
    extends ReaderContentPanel<HTMLContentContainer>
{
    private static final Logger             log       = Logger.getLogger( HTMLReaderPanel.class );
    static
    {
        java.util.logging.Logger.getLogger( "org.lobobrowser" ).setLevel( Level.OFF );
    }

    private final HtmlPanel                 htmlPanel = new HtmlPanel();
    private final SimpleHtmlRendererContext rcontext;
    private final DocumentBuilder           documentBuilder;

    private Document                        doc;

    public HTMLReaderPanel(
        final ReaderWindow<HTMLContentContainer> readerWindow )
    {
        super( readerWindow );
        add( new JScrollPane( htmlPanel ), BorderLayout.CENTER );

        final SimpleUserAgentContext ucontext = new SimpleUserAgentContext();
        ucontext.setScriptingEnabled( false );
        rcontext = new SimpleHtmlRendererContext( htmlPanel, ucontext );
        documentBuilder = new DocumentBuilderImpl( ucontext, rcontext );
    }

    @Override
    public void highlightText(
        final String text )
    {
    // todo node can be too large
    //        final Node node = new DOMTextFinder( doc, text ).find();
    //        if ( node != null )
    //        {
    //            log.debug( "text found " + text );
    //            htmlPanel.scrollTo( node );
    //        }
    }

    @Override
    public void setContent(
        final HTMLContentContainer content )
    {
        readerWindow.getReaderToolBar().getProgressBar().invoke( new Runnable()
        {
            @Override
            public void run()
            {
                Reader reader = null;
                try
                {
                    reader = new StringReader( content.getContent() );
                    final InputSource source = new InputSource( reader );

                    doc = documentBuilder.parse( source );
                    htmlPanel.setDocument( doc, rcontext );
                }
                catch ( final Exception e )
                {
                    log.error( e, e );
                }
                finally
                {
                    IOUtils.closeQuietly( reader );
                }
            }
        } );
    }

    @Override
    public void setScale(
        final int scale )
    {}
}