package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTree;

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
}
