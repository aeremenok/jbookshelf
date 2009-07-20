/**
 * 
 */
package org.jbookshelf.controller.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author eav 2009
 */
public class DOMTextFinder
{
    private static final Logger log = Logger.getLogger( DOMTextFinder.class );
    private final String        text;

    private final Document      document;

    public DOMTextFinder(
        @Nonnull final Document document,
        @Nonnull final String text )
    {
        super();
        this.document = document;
        this.text = text.toLowerCase();
        log.debug( "finding text " + text );
    }

    @Nullable
    public Node find()
    {
        return findText( document.getChildNodes() );
    }

    private Node findText(
        final NodeList childNodes )
    {
        final int length = childNodes.getLength();
        for ( int i = 0; i < length; i++ )
        {
            Node node = childNodes.item( i );
            final String nodeValue = node.getNodeValue();
            if ( nodeValue != null && !"".equals( nodeValue ) && nodeValue.length() >= text.length() )
            {
                log.debug( "checking " + nodeValue );
                if ( nodeValue.toLowerCase().contains( text ) )
                {
                    return node;
                }
            }

            node = findText( node.getChildNodes() );
            if ( node != null )
            {
                return node;
            }
        }
        return null;
    }
}
