/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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

import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;
import org.jbookshelf.qtgui.widgets.tree.UniqueNode;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QTreeWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;

/**
 * Displays {@link Unique}'s related to one selected in the {@link CollectionPanel} ( {@link Unique#getRelated()} )
 * 
 * @author eav
 */
public class RelatedTreePanel
    extends SearchableTreePanel
{
    private Unique selectedUnique;

    public RelatedTreePanel(
        RelatedPanel relatedPanel )
    {
        super( relatedPanel );
        initComponents();
        initListeners();
    }

    @Override
    public void onAdd()
    {
        QMessageBox.information( this, tr( "Info" ), tr( "Double click on related item in the collection tree" ) );
        CollectionPanel.getInstance().selectRelatedUnique( this, selectedUnique );
    }

    @Override
    public void onRemove()
    {
        UniqueNode uniqueNode = (UniqueNode) searchableTree.selectedItems().get( 0 );
        selectedUnique.getRelated().remove( uniqueNode.getUnique() );
        uniqueNode.getUnique().getRelated().remove( selectedUnique );

        int row = root.indexOfChild( uniqueNode );
        root.removeChild( uniqueNode );

        if ( row > 1 )
        {
            searchableTree.setCurrentItem( root.child( row - 1 ) );
        }
        else
        {
            relatedPanel.focusLost();
        }
    }

    public void relatedSelectionFinished(
        Unique relatedUnique,
        Unique unique )
    {
        unique.getRelated().add( relatedUnique );
        relatedUnique.getRelated().add( unique );

        root.addChild( new UniqueNode( relatedUnique ) );
        searchableTree.setCurrentItem( root.child( root.childCount() - 1 ) );

        QMessageBox.information( this, tr( "Linked" ), relatedUnique.getName() + " " + tr( "now relates to" ) + " " +
            unique.getName() );
        selectedUnique( selectedUnique );
    }

    @Override
    public void search(
        String text )
    {
        if ( selectedUnique != null )
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
    }

    public void selectedUniques(
        List<Unique> uniques )
    {
        if ( uniques.size() == 1 )
        {
            selectedUnique( uniques.get( 0 ) );
        }
        else
        {
            cleanRoot();
            relatedPanel.focusLost();
        }
    }

    private void cleanRoot()
    {
        for ( int i = 0; i < root.childCount(); i++ )
        {
            root.removeChild( root.child( i ) );
        }
    }

    private void drawUniques(
        List<Unique> relatedUniques )
    {
        cleanRoot();
        for ( Unique related : relatedUniques )
        {
            root.addChild( new UniqueNode( related ) );
        }
        root.setExpanded( true );
    }

    private void initListeners()
    {
        searchableTree.activated.connect( relatedPanel, "focusGained()" );
        searchableTree.itemSelectionChanged.connect( relatedPanel, "focusGained()" );
        searchableTree.doubleClicked.connect( this, "navigate(QModelIndex)" );
    }

    @SuppressWarnings( "unused" )
    private void navigate(
        QModelIndex index )
    {
        List<QTreeWidgetItem> selectedItems = searchableTree.selectedItems();
        if ( selectedItems.size() > 0 && selectedItems.get( 0 ) instanceof UniqueNode )
        {
            UniqueNode node = (UniqueNode) selectedItems.get( 0 );
            CollectionPanel.getInstance().selectUnique( node.getUnique() );
        }
    }

    private void selectedUnique(
        Unique unique )
    {
        this.selectedUnique = unique;
        drawUniques( selectedUnique.getRelated() );
    }

    protected void initComponents()
    {
        setLayout( new QVBoxLayout() );
        layout().addWidget( searchableTree );
    }
}
