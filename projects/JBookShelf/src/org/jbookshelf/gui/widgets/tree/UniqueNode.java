package org.jbookshelf.gui.widgets.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jbookshelf.Unique;

public class UniqueNode
    extends DefaultMutableTreeNode
{
    private Unique unique;

    public UniqueNode(
        Unique unique )
    {
        super( unique.getName() );
        this.unique = unique;
    }

    public Unique getUnique()
    {
        return unique;
    }
}
