/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.bushe.swing.event.ObjectEvent;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.dialog.book.BookAdditionDialog;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.BrowserNavigator;
import org.jbookshelf.view.swinggui.reader.toolbar.BrowserNavigator.BrowserCommand;
import org.lobobrowser.html.FormInput;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;

/**
 * @author eav
 */
public class EventRendererContext
    extends SimpleHtmlRendererContext
{
    private static final Logger log = Logger.getLogger( HTMLReaderPanel.class );

    public EventRendererContext(
        final HtmlPanel contextComponent,
        final UserAgentContext ucontext )
    {
        super( contextComponent, ucontext );
        AnnotationProcessor.process( this );
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
                navigate( "http://www.google.com" );
                break;

            case HOME:
                reload();
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
                EventRendererContext.super.submitForm( method, action, target, enctype, formInputs );
            }
        } );
    }

    private void savePage()
    {
        final ReaderWindow window = Single.instance( ReaderWindow.class );
        final String name = JOptionPane.showInputDialog( window, I18N.tr( "Enter new file name" ) );
        if ( name != null && !"".equals( name ) )
        {
            try
            {
                final String parent = window.getBook().getPhysicalBook().getFile().getParent();
                final File file = new File( parent + "/" + name + ".html" );
                file.createNewFile();

                FileUtils.writeStringToFile( file, this.getSourceCode(), "utf8" );

                new BookAdditionDialog( window, file )
                {
                    @Override
                    protected void cancelled()
                    {
                        file.deleteOnExit();
                    }
                }.setVisible( true );
            }
            catch ( final IOException e )
            {
                log.error( e, e );
                throw new Error( e );
            }
        }
    }

    @Override
    protected boolean isNavigationAsynchronous()
    {
        return false;
    }
}
