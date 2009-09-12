/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.html;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.Proxy.Type;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.lobobrowser.html.test.SimpleUserAgentContext;

/**
 * @author eav 2009
 */
public class ProxyUserAgentContext
    extends SimpleUserAgentContext
{
    private class AskUserAuthenticator
        extends Authenticator
    {
        private final String user = System.getProperty( "user.name" );
        private final String pass = null;

        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            if ( pass == null )
            {
                // todo init
            }
            return new PasswordAuthentication( user, pass.toCharArray() );
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( ProxyUserAgentContext.class );

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
        }

        Authenticator.setDefault( new AskUserAuthenticator() );
    }

}
