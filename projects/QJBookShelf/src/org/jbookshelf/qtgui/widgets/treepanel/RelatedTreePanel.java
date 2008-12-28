/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */
package org.jbookshelf.qtgui.widgets.treepanel;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.JTextComponent;

import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.gui.QTreeWidgetItem;

public class RelatedTreePanel
    extends SearchableTreePanel
{
    private QTreeWidgetItem root = new QTreeWidgetItem();

    // private JTree relatedTree = new JTree( root );
    // private JScrollPane scrollPane = new JScrollPane( relatedTree );
    private Unique          selectedUnique;

    public RelatedTreePanel(
        RelatedPanel relatedPanel )
    {
        super( relatedPanel );
        initComponents();
    }

    public void nothingSelected()
    {
        // root.removeAllChildren();
        relatedPanel.focusLost();
    }

    @Override
    public void onAdd()
    {
        // JOptionPane.showMessageDialog( this, "Double click on related item in the collection tree" );
        CollectionPanel.getInstance().selectRelatedUnique( this, selectedUnique );
    }

    @Override
    public void search(
        String text )
    {
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

        // root.add( new UniqueNode( relatedUnique ) );
        // relatedTree.setSelectionRow( root.getChildCount() - 1 );
        //
        // JOptionPane.showMessageDialog( this, relatedUnique.getName() + " now relates to " + unique.getName() );
        selectedUnique( selectedUnique );
    }

    @Override
    public void onRemove()
    {
        // UniqueNode uniqueNode = (UniqueNode) relatedTree.getLastSelectedPathComponent();
        // selectedUnique.getRelated().remove( uniqueNode.getUnique() );
        // uniqueNode.getUnique().getRelated().remove( selectedUnique );
        //
        // int row = root.getIndex( uniqueNode );
        // root.remove( uniqueNode );
        // relatedTree.updateUI();
        //
        // if ( row > 1 )
        // {
        // relatedTree.setSelectionRow( row - 1 );
        // }
        // else
        // {
        // relatedPanel.focusLost();
        // }
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
        // root.removeAllChildren();
        //
        // for ( Unique related : relatedUniques )
        // {
        // root.add( new UniqueNode( related ) );
        // }
        //
        // relatedTree.expandRow( 0 );
        // relatedTree.setRootVisible( false );
        //
        // relatedTree.updateUI();
    }

    private void initComponents()
    {
        // add( scrollPane );
        // relatedTree.addTreeSelectionListener( new TreeSelectionListener()
        // {
        // public void valueChanged(
        // TreeSelectionEvent e )
        // {
        // relatedPanel.focusGained();
        // }
        // } );
    }
}
