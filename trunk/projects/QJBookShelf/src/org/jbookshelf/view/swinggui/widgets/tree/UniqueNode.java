package org.jbookshelf.view.swinggui.widgets.tree;

import org.jbookshelf.model.db.Unique;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

public class UniqueNode
    extends DefaultMutableTreeTableNode
{
    private final Unique  unique;
    private final boolean isLeaf;

    public UniqueNode(
        final Unique unique )
    {
        this( unique, true );
    }

    public UniqueNode(
        final Unique unique,
        final boolean isLeaf )
    {
        this.unique = unique;
        this.isLeaf = isLeaf;
    }

    @Override
    public int getColumnCount()
    {
        // todo
        return 1;
    }

    public Unique getUnique()
    {
        return unique;
    }

    @Override
    public Object getValueAt(
        final int column )
    {
        // todo
        return unique.getName();
    }

    @Override
    public boolean isEditable(
        final int column )
    {
        return false;
    }

    @Override
    public boolean isLeaf()
    {
        return isLeaf;
    }

    @Override
    public void setValueAt(
        final Object value,
        final int column )
    {}
}
