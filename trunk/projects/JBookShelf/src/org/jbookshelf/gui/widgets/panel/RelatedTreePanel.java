package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jbookshelf.Unique;
import org.jbookshelf.gui.CollectionPanel;
import org.jbookshelf.gui.RelatedPanel;
import org.jbookshelf.gui.widgets.tree.UniqueNode;

public class RelatedTreePanel
    extends SearchableTreePanel
{
    private DefaultMutableTreeNode root        = new DefaultMutableTreeNode();

    private JTree                  relatedTree = new JTree( root );
    private JScrollPane            scrollPane  = new JScrollPane( relatedTree );
    private Unique                 selectedUnique;

    public RelatedTreePanel(
        RelatedPanel relatedPanel )
    {
        super( relatedPanel );
        setLayout( new BorderLayout() );
        initComponents();
    }

    public void nothingSelected()
    {
        root.removeAllChildren();
        relatedPanel.focusLost();
    }

    @Override
    public void onAdd()
    {
        JOptionPane.showMessageDialog( this, "Double click on related item in the collection tree" );
        CollectionPanel.getInstance().selectRelatedUnique( this, selectedUnique );
    }

    @Override
    public void onKeyReleased(
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

    public void onRelatedSelection(
        Unique relatedUnique,
        Unique unique )
    {
        unique.getRelated().add( relatedUnique );
        relatedUnique.getRelated().add( unique );

        root.add( new UniqueNode( relatedUnique ) );
        relatedTree.setSelectionRow( root.getChildCount() - 1 );

        JOptionPane.showMessageDialog( this, relatedUnique.getName() + " now relates to " + unique.getName() );
    }

    @Override
    public void onRemove()
    {
        UniqueNode uniqueNode = (UniqueNode) relatedTree.getLastSelectedPathComponent();
        selectedUnique.getRelated().remove( uniqueNode.getUnique() );
        uniqueNode.getUnique().getRelated().remove( selectedUnique );

        int row = root.getIndex( uniqueNode );
        root.remove( uniqueNode );
        relatedTree.updateUI();

        if ( row > 1 )
        {
            relatedTree.setSelectionRow( row - 1 );
        }
        else
        {
            relatedPanel.focusLost();
        }
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
        root.removeAllChildren();

        for ( Unique related : relatedUniques )
        {
            root.add( new UniqueNode( related ) );
        }

        relatedTree.expandRow( 0 );
        relatedTree.setRootVisible( false );

        relatedTree.updateUI();
    }

    private void initComponents()
    {
        add( scrollPane );
        relatedTree.addTreeSelectionListener( new TreeSelectionListener()
        {
            public void valueChanged(
                TreeSelectionEvent e )
            {
                relatedPanel.focusGained();
            }
        } );
    }
}
