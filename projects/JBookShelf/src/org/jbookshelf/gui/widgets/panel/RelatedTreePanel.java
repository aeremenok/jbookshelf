package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTree;

import org.jbookshelf.Unique;

public class RelatedTreePanel
    extends SearchableTreePanel
{
    private javax.swing.JTree       relatedTree = new JTree();
    private javax.swing.JScrollPane scrollPane  = new JScrollPane( relatedTree );

    public RelatedTreePanel()
    {
        super();
        setLayout( new BorderLayout() );
        initComponents();
    }

    protected void initComponents()
    {
        add( scrollPane );
    }

    public void nothingSelected()
    {
        // TODO Auto-generated method stub

    }

    public void selectedUnique(
        Unique unique )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAdd()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onKeyTyped(
        KeyEvent evt )
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRemove()
    {
        // TODO Auto-generated method stub

    }
}
