/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollBar;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.TaskQueue;
import org.jbookshelf.view.swinggui.reader.textpanel.SelectableTextPanel;
import org.lobobrowser.html.HtmlRendererContext;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.domimpl.NodeImpl;
import org.lobobrowser.html.gui.HtmlBlockPanel;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.gui.SelectionChangeEvent;
import org.lobobrowser.html.gui.SelectionChangeListener;
import org.lobobrowser.html.parser.DocumentBuilderImpl;
import org.lobobrowser.html.renderer.FrameContext;
import org.lobobrowser.html.renderer.RBlock;
import org.lobobrowser.html.renderer.RenderableContainer;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;
import org.lobobrowser.html.test.SimpleUserAgentContext;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * todo rename
 * 
 * @author eav 2009
 */
public class HTMLReaderPanel
    extends SelectableTextPanel<HTMLContentContainer>
{
    // todo avoid dirty hacks!
    protected class HTMLBlockPanel
        extends HtmlBlockPanel
    {
        public HTMLBlockPanel(
            final Color background,
            final boolean opaque,
            final UserAgentContext pcontext,
            final HtmlRendererContext rcontext,
            final FrameContext frameContext )
        {
            super( background, opaque, pcontext, rcontext, frameContext );
        }

        public JScrollBar getVScrollBar()
        {
            final RBLOCK block = (RBLOCK) this.rblock;
            return block.getVerticalScrollBar();
        }

        /**
         * Sets the root node to render. This method should be invoked in the GUI dispatch thread.
         */
        @Override
        public void setRootNode(
            final NodeImpl node )
        {
            if ( node != null )
            {
                final RBlock block = new RBLOCK( node, 0, this.ucontext, this.rcontext, this.frameContext, this );
                block.setDefaultMarginInsets( this.defaultMarginInsets );
                //block.setDefaultPaddingInsets(this.defaultPaddingInsets);
                block.setDefaultOverflowX( this.defaultOverflowX );
                block.setDefaultOverflowY( this.defaultOverflowY );
                node.setUINode( block );
                this.rblock = block;
            }
            else
            {
                this.rblock = null;
            }
            this.invalidate();
            this.validateAll();
            this.repaint();
        }
    }

    protected class HTMLPanel
        extends HtmlPanel
    {
        public HTMLBlockPanel getBlockPanel()
        {
            return (HTMLBlockPanel) this.htmlBlockPanel;
        }

        @Override
        protected HtmlBlockPanel createHtmlBlockPanel(
            final UserAgentContext ucontext,
            final HtmlRendererContext rcontext )
        {
            return new HTMLBlockPanel( java.awt.Color.WHITE, true, ucontext, rcontext, this );
        }
    }

    protected class RBLOCK
        extends RBlock
    {
        public RBLOCK(
            final NodeImpl modelNode,
            final int listNesting,
            final UserAgentContext pcontext,
            final HtmlRendererContext rcontext,
            final FrameContext frameContext,
            final RenderableContainer parentContainer )
        {
            super( modelNode, listNesting, pcontext, rcontext, frameContext, parentContainer );
        }

        @Override
        public Component addComponent(
            final Component component )
        {
            if ( component == this.vScrollBar )
            {
                this.vScrollBar.getModel().addChangeListener( taskQueue );
            }
            return super.addComponent( component );
        }

        public JScrollBar getVerticalScrollBar()
        {
            return this.vScrollBar;
        }
    }

    private static final Logger             log       = Logger.getLogger( HTMLReaderPanel.class );

    static
    {
        java.util.logging.Logger.getLogger( "org.lobobrowser" ).setLevel( Level.OFF );
    }

    private final TaskQueue                 taskQueue = new TaskQueue();
    private final HTMLPanel                 htmlPanel = new HTMLPanel();
    private final SimpleHtmlRendererContext rcontext;
    private final DocumentBuilder           documentBuilder;

    private Document                        doc;

    public HTMLReaderPanel()
    {
        super();
        add( htmlPanel, BorderLayout.CENTER );

        final SimpleUserAgentContext ucontext = new SimpleUserAgentContext();
        ucontext.setScriptingEnabled( false );
        rcontext = new SimpleHtmlRendererContext( htmlPanel, ucontext );
        documentBuilder = new DocumentBuilderImpl( ucontext, rcontext );

        htmlPanel.addSelectionChangeListener( new SelectionChangeListener()
        {
            public void selectionChanged(
                final SelectionChangeEvent event )
            {
                EventQueue.invokeLater( new Runnable()
                {
                    public void run()
                    {
                        if ( event.isSelectionAvailable() )
                        {
                            final Point location = MouseInfo.getPointerInfo().getLocation();
                            popupListener.showPopup( htmlPanel, location.x, location.y );
                        }
                    }
                } );
            }
        } );
    }

    @Override
    public synchronized void goTo(
        final Bookmark bookmark )
    {
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                final HTMLBlockPanel blockPanel = htmlPanel.getBlockPanel();
                if ( blockPanel != null )
                {
                    final JScrollBar vScrollBar = blockPanel.getVScrollBar();
                    final BoundedRangeModel model = vScrollBar.getModel();
                    final float max = model.getMaximum() - model.getExtent();
                    final float value = bookmark.getPosition() * max;
                    model.setValue( (int) value );
                }
            }
        };
        EventQueue.invokeLater( runnable );
        taskQueue.enqueue( runnable );
    }

    @Override
    public void highlightText(
        final String text )
    { // todo 
    }

    @Override
    public synchronized void setContent(
        final HTMLContentContainer content )
    {
        Single.instance( ProgressBar.class ).invoke( new Runnable()
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
    protected float getPosition(
        final Bookmark note )
    {
        final BoundedRangeModel model = htmlPanel.getBlockPanel().getVScrollBar().getModel();
        final float value = model.getValue();
        final float max = model.getMaximum() - model.getExtent();
        return value / max;
    }

    @Override
    protected String getSelectedText()
    {
        return htmlPanel.getSelectionText();
    }
}
