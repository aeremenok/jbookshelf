/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * default implementation of {@link LazyNode}
 * 
 * @author eav 2009
 */
public class DefaultLazyNode
    extends DefaultMutableTreeNode
    implements
    LazyNode
{
    private boolean initalized = false;

    public DefaultLazyNode()
    {
        super();
    }

    public DefaultLazyNode(
        final Object userObject )
    {
        super( userObject );
    }

    public DefaultLazyNode(
        final Object userObject,
        final boolean allowsChildren )
    {
        super( userObject, allowsChildren );
    }

    @Override
    public boolean isInitialized()
    {
        return initalized;
    }

    @Override
    public void setInitialized(
        final boolean initalized )
    {
        this.initalized = initalized;
    }
}
