package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jbookshelf.Unique;
import org.jbookshelf.gui.RelatedPanel;
import org.jbookshelf.gui.widgets.tree.UniqueNode;

public class RelatedTreePanel
    extends SearchableTreePanel
{
    private JTree       relatedTree = new JTree();
    private JScrollPane scrollPane  = new JScrollPane( relatedTree );
    private Unique      selectedUnique;

    public RelatedTreePanel(
        RelatedPanel relatedPanel )
    {
        super( relatedPanel );
        setLayout( new BorderLayout() );
        initComponents();
    }

    public void nothingSelected()
    {
        getRoot().removeAllChildren();
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
        String text = ((JTextComponent) evt.getSource()).getText();
        if ( text.equals( "" ) )
        {
            drawUniques( selectedUnique.getRelated() );
        }
        else
        {
            List<Unique> result = new ArrayList<Unique>();
            String lowerCase = text.toLowerCase();
            for ( Unique unique : selectedUnique.getRelated() )
            {
                if ( unique.getName().toLowerCase().contains( lowerCase ) )
                {
                    result.add( unique );
                }
            }
            drawUniques( result );
        }
    }

    @Override
    public void onRemove()
    {
        UniqueNode uniqueNode = (UniqueNode) relatedTree.getLastSelectedPathComponent();
        selectedUnique.getRelated().remove( uniqueNode.getUnique() );

        getRoot().remove( uniqueNode );
        relatedTree.updateUI();
    }

    public void selectedUnique(
        Unique unique )
    {
        this.selectedUnique = unique;
        drawUniques( selectedUnique.getRelated() );
    }

    private void drawUniques(
        List<Unique> relatedUniques )
    {
        DefaultMutableTreeNode root = getRoot();
        root.removeAllChildren();

        for ( Unique related : relatedUniques )
        {
            UniqueNode parent = new UniqueNode( related );
            root.add( parent );
        }

        relatedTree.expandRow( 0 );
        relatedTree.setRootVisible( false );

        updateUI();
    }

    private DefaultMutableTreeNode getRoot()
    {
        return (DefaultMutableTreeNode) relatedTree.getModel().getRoot();
    }

    private void initComponents()
    {
        add( scrollPane );
        relatedTree.addFocusListener( relatedPanel );
    }
}
