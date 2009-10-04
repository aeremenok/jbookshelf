/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab;

import javax.swing.tree.MutableTreeNode;

/**
 * tree node, that knows whether or not it is initialized
 * 
 * @author eav 2009
 */
public interface LazyNode
    extends
    MutableTreeNode
{
    boolean isInitialized();

    void setInitialized(
        boolean initalized );
}
