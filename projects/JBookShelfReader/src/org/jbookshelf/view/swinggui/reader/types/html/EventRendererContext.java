/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.ObjectEvent;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.dialog.book.BookAdditionDialog;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutablePanel;
import org.jbookshelf.view.swinggui.reader.toolbar.BrowserNavigator;
import org.jbookshelf.view.swinggui.reader.toolbar.BrowserNavigator.BrowserCommand;
import org.lobobrowser.html.FormInput;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;
import org.w3c.dom.html2.HTMLLinkElement;

/**
 * @author eav
 */
public class EventRendererContext
    extends SimpleHtmlRendererContext
{
    private static final String GOOGLE_URL        = "http://www.google.com";

    private static final Logger log               = Logger.getLogger( HTMLReaderPanel.class );

    public static final String  BACK_AVAILABLE    = "BACK_AVAILABLE";
    public static final String  FORWARD_AVAILABLE = "FORWARD_AVAILABLE";

    private final List<String>  history           = new ArrayList<String>();
    private int                 currentUrlIndex   = 0;

    public EventRendererContext(
        final HtmlPanel contextComponent,
        final UserAgentContext ucontext )
    {
        super( contextComponent, ucontext );
        AnnotationProcessor.process( this );
    }

    @Override
    public void back()
    {
        navigate( history.get( --currentUrlIndex ) );
    }

    @Override
    public void forward()
    {
        navigate( history.get( ++currentUrlIndex ) );
    }

    @Override
    public String getCurrentURL()
    {
        return history.get( currentUrlIndex );
    }

    @Override
    public int getHistoryLength()
    {
        return history.size();
    }

    @Override
    public String getNextURL()
    {
        return history.get( currentUrlIndex + 1 );
    }

    @Override
    public String getPreviousURL()
    {
        return history.get( currentUrlIndex - 1 );
    }

    @Override
    public void goToHistoryURL(
        final String url )
    {
        if ( history.contains( url ) )
        {
            navigate( url );
        }
    }

    @Override
    public boolean isVisitedLink(
        final HTMLLinkElement link )
    {
        final String href = link.getHref();
        URL url;
        try
        {
            url = new URL( href );
        }
        catch ( final MalformedURLException e )
        {
            try
            {
                final URL context = new URL( history.get( currentUrlIndex ) );
                url = new URL( context, href );
            }
            catch ( final MalformedURLException e1 )
            {
                log.error( e1, e1 );
                return false;
            }
        }
        return history.contains( url.toString() );
    }

    @Override
    public void moveInHistory(
        final int offset )
    {
        currentUrlIndex += offset;
        navigate( history.get( currentUrlIndex ) );
    }

    @Override
    public void navigate(
        final String fullURL )
    {
        try
        {
            super.navigate( fullURL );
        }
        catch ( final MalformedURLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @EventTopicSubscriber( topic = ReaderFactory.BROWSER )
    public void onNavigation(
        @SuppressWarnings( "unused" ) final String topic,
        final ObjectEvent event )
    {
        final BrowserCommand command = (BrowserCommand) event.getEventObject();
        switch ( command )
        {
            case BACK:
                back();
                break;

            case FORWARD:
                forward();
                break;

            case GOOGLE:
                navigate( GOOGLE_URL );
                break;

            case HOME:
                final ReaderWindow window = Single.instance( ReaderWindow.class );
                window.updateCurrentPage();
                Single.instance( LayoutablePanel.class ).getCurrentPanels().goTo( window.getBook().getLastRead() );
                break;

            case SAVE:
                savePage();
                break;

            case ADDRESS:
                final BrowserNavigator navigator = (BrowserNavigator) event.getSource();
                String address = navigator.getAddress().getText();
                if ( !address.startsWith( "http://" ) )
                {
                    address = "http://" + address;
                }
                navigate( address );
                break;

            default:
                break;
        }
    }

    @Override
    public void submitForm(
        final String method,
        final URL action,
        final String target,
        final String enctype,
        final FormInput[] formInputs )
    {
        Single.instance( ProgressBar.class ).invoke( new Runnable()
        {
            @Override
            public void run()
            {
                if ( history.size() == 0 )
                {
                    Single.instance( ReaderWindow.class ).updateLastRead();
                }

                EventRendererContext.super.submitForm( method, action, target, enctype, formInputs );

                final String url = action.toString();
                final int indexOf = history.indexOf( url );
                if ( indexOf == -1 )
                {
                    history.add( url );
                    currentUrlIndex = history.size() - 1;
                }
                else
                {
                    currentUrlIndex = indexOf;
                }

                EventBus.publish( BACK_AVAILABLE, new ObjectEvent( this, currentUrlIndex > 0 ) );
                EventBus.publish( FORWARD_AVAILABLE, new ObjectEvent( this, currentUrlIndex < history.size() - 1 ) );
            }
        } );
    }

    private void savePage()
    {
        final ReaderWindow window = Single.instance( ReaderWindow.class );
        final String name = JOptionPane.showInputDialog( window, I18N.tr( "Enter new file name" ) );
        if ( name != null && !"".equals( name ) )
        {
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<JDialog, Object>()
            {
                @Override
                protected JDialog doInBackground()
                    throws Exception
                {
                    final String parent = window.getBook().getPhysicalBook().getFile().getParent();
                    final File file = new File( parent + "/" + name + ".html" );
                    file.createNewFile();

                    // fixme wrong encoding!
                    FileUtils.writeStringToFile( file, getSourceCode(), "utf8" );

                    return new BookAdditionDialog( window, file )
                    {
                        @Override
                        protected void cancelled()
                        {
                            file.deleteOnExit();
                        }
                    };
                }

                @Override
                protected void doneSafe()
                {
                    getQuiet().setVisible( true );
                }
            } );
        }
    }

    @Override
    protected boolean isNavigationAsynchronous()
    {
        return false;
    }
}
