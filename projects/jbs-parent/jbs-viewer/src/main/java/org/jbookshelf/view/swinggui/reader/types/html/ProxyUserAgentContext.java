/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import static org.jbookshelf.controller.singleton.Single.instance;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.Proxy.Type;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.JBSSystem;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jdesktop.swingx.JXLoginDialog;
import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.auth.LoginService;
import org.lobobrowser.html.test.SimpleUserAgentContext;

/**
 * reads settings of a {@link Proxy} and enables it todo refactor
 * 
 * @author eav 2009
 */
public class ProxyUserAgentContext
    extends SimpleUserAgentContext
{
    /**
     * asks user's credentials first time using {@link JXLoginDialog}
     * 
     * @author eav 2009
     */
    private class AskUserAuthenticator
        extends Authenticator
    {
        private String user = instance( JBSSystem.class ).userHome();
        private char[] pass = null;

        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            if ( pass == null )
            {
                final CheckCredentialsService service = new CheckCredentialsService();
                final JXLoginDialog dialog = new JXLoginDialog( Single.instance( ReaderWindow.class ), true );

                final JXLoginPane panel = dialog.getPanel();
                panel.setLoginService( service );
                panel.setUserName( user );
                panel.setBannerText( getRequestingPrompt() );
                panel.setMessage( I18N.tr( "Proxy credentials are not stored by JBookShelf" ) );

                dialog.setVisible( true );

                if ( service.getPassword() == null )
                {
                    return null;
                }
                user = service.getName();
                pass = service.getPassword();
            }
            return new PasswordAuthentication( user, pass );
        }
    }

    /**
     * checks connection with given credentials and saves them if connection was successful
     * 
     * @author eav 2009
     */
    private class CheckCredentialsService
        extends LoginService
    {
        private String name;
        private char[] password;

        @Override
        public boolean authenticate(
            final String name,
            final char[] password,
            final String server )
        {
            try
            {
                Authenticator.setDefault( new Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    { // fixme will this work while another auth is not complete?
                        return new PasswordAuthentication( name, password );
                    }
                } );

                final URL url = new URL( EventDrivenRendererContext.GOOGLE_URL );
                final URLConnection connection = url.openConnection( getProxy() );
                connection.connect();

                this.name = name;
                this.password = password;

                Authenticator.setDefault( askUserAuthenticator );

                return true;
            }
            catch ( final Exception e )
            {
                log.error( e, e );
                return false;
            }
        }

        public String getName()
        {
            return this.name;
        }

        public char[] getPassword()
        {
            return this.password;
        }
    }

    private static final Logger        log                  = Logger.getLogger( ProxyUserAgentContext.class );
    private final AskUserAuthenticator askUserAuthenticator = new AskUserAuthenticator();

    public ProxyUserAgentContext()
    {
        super();

        this.setScriptingEnabled( false );
        final Settings settings = Single.instance( Settings.class );
        final Type type = settings.PROXY_TYPE.getValue();
        if ( type != Type.DIRECT )
        {
            final String host = settings.PROXY_HOST.getValue();
            final Integer port = settings.PROXY_PORT.getValue();
            final SocketAddress sa = new InetSocketAddress( host, port );
            this.setProxy( new Proxy( type, sa ) );

            Authenticator.setDefault( askUserAuthenticator );
        }
    }

}
