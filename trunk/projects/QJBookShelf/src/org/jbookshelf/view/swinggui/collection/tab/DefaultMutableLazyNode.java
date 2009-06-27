/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author eav 2009
 */
public class DefaultMutableLazyNode
    extends DefaultMutableTreeNode
    implements
    LazyNode
{
    private boolean initalized = false;

    public DefaultMutableLazyNode()
    {
        super();
    }

    public DefaultMutableLazyNode(
        final Object userObject )
    {
        super( userObject );
    }

    public DefaultMutableLazyNode(
        final Object userObject,
        final boolean allowsChildren )
    {
        super( userObject, allowsChildren );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.collection.tab.LazyNode#isInitialized()
     */
    @Override
    public boolean isInitialized()
    {
        return initalized;
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.collection.tab.LazyNode#setInitialized(boolean)
     */
    @Override
    public void setInitialized(
        final boolean initalized )
    {
        this.initalized = initalized;
    }
}
