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
package org.jbookshelf.qtgui.widgets.tree;

import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.ToolBar;
import org.jbookshelf.qtgui.logic.SourcesUniqueSelection;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.menu.CollectionTreeMenu;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QFocusEvent;
import com.trolltech.qt.gui.QTreeWidget;
import com.trolltech.qt.gui.QTreeWidgetItem;
import com.trolltech.qt.gui.QTreeWidgetItem.ChildIndicatorPolicy;

/**
 * Displays any collection of {@link Unique}'s from {@link BookShelf}
 * 
 * @author eav
 */
public abstract class CollectionTree
    // todo implement Translatable
    extends QTreeWidget
    implements
        SourcesUniqueSelection
{
    private List<UniqueSelectionListener> listeners      = new ArrayList<UniqueSelectionListener>();
    private List<FocusListener>           focusListeners = new ArrayList<FocusListener>();
    private List<MouseListener>           mouseListeners = new ArrayList<MouseListener>();

    protected QTreeWidgetItem             root;

    public static void removeChildren(
        QTreeWidgetItem parent )
    {
        int childCount = parent.childCount();
        for ( int i = 0; i < childCount; i++ )
        {
            parent.removeChild( parent.child( 0 ) );
        }
        parent.setExpanded( false );
    }

    public CollectionTree()
    {
        super();

        initComponents();
        initListeners();

        ToolBar.getInstance().selectedUniques( new ArrayList<Unique>() );
    }

    public void addFocusListener(
        FocusListener focusListener )
    {
        focusListeners.add( focusListener );
    }

    public void addMouseListener(
        MouseListener mouseListener )
    {
        mouseListeners.add( mouseListener );
    }

    public void addSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.add( listener );
    }

    public void removeFocusListener(
        FocusListener focusListener )
    {
        focusListeners.remove( focusListener );
    }

    public void removeMouseListener(
        MouseListener mouseListener )
    {
        mouseListeners.remove( mouseListener );
    }

    public void removeSelectedItems()
    {
        for ( QTreeWidgetItem item : selectedItems() )
        {
            if ( item instanceof UniqueNode )
            {
                // search recursively for its nodes
                List<UniqueNode> nodes = new ArrayList<UniqueNode>();
                findUniqueNodes( nodes, root, ((UniqueNode) item).getUnique() );
                // remove all of them
                for ( UniqueNode node : nodes )
                {
                    removeFromParent( node );
                    Storage.getBookShelf().removeUnique( node.getUnique() );
                }
            }
        }
    }

    public void removeSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.remove( listener );
    }

    public void selectUnique(
        Unique unique )
    {
        clearSelection();
        for ( int i = 0; i < root.childCount(); i++ )
        {
            UniqueNode child = (UniqueNode) root.child( i );
            if ( child.getUnique().equals( unique ) )
            {
                child.setSelected( true );
                return;
            }
        }
    }

    public void showResult(
        List<? extends Unique> uniques )
    {
        clearSelection();

        removeChildren( root );

        for ( Unique unique : uniques )
        {
            UniqueNode parent = new UniqueNode( unique );
            parent.setChildIndicatorPolicy( ChildIndicatorPolicy.ShowIndicator );
            root.addChild( parent );
        }

        fireSelectedUniques( new ArrayList<Unique>() );
    }

    @SuppressWarnings( "unchecked" )
    public void update(
        BookShelf bookShelf )
    {
        showResult( (List<Unique>) bookShelf.eGet( getReference() ) );
    }

    /**
     * recursively search for nodes containing the specified unique
     * 
     * @param list search result
     * @param parent search starts here
     * @param unique what to search
     */
    private void findUniqueNodes(
        List<UniqueNode> list,
        QTreeWidgetItem parent,
        Unique unique )
    {
        if ( parent instanceof UniqueNode )
        {
            UniqueNode uniqueNode = (UniqueNode) parent;
            if ( uniqueNode.getUnique().equals( unique ) )
            {
                list.add( uniqueNode );
            }
        }
        for ( int i = 0; i < parent.childCount(); i++ )
        {
            findUniqueNodes( list, parent.child( i ), unique );
        }
    }

    @SuppressWarnings( "unused" )
    private void fireDoubleClicked(
        QTreeWidgetItem item,
        Integer index )
    {
        for ( MouseListener listener : mouseListeners )
        {
            listener.mouseClicked( null );
        }
    }

    private void fireSelectedUniques(
        List<Unique> uniques )
    {
        for ( UniqueSelectionListener listener : listeners )
        {
            listener.selectedUniques( uniques );
        }
    }

    private void initComponents()
    {
        setSelectionMode( SelectionMode.ExtendedSelection );
        root = invisibleRootItem();
        header().hide();
    }

    private void initListeners()
    {
        addSelectionListener( ToolBar.getInstance() );
        addSelectionListener( RelatedPanel.getInstance() );

        itemDoubleClicked.connect( this, "fireDoubleClicked(QTreeWidgetItem, Integer)" );
        itemSelectionChanged.connect( this, "itemSelectionChanged()" );
        itemExpanded.connect( this, "onExpand(QTreeWidgetItem)" );
        doubleClicked.connect( this, "navigate(QModelIndex)" );
    }

    @SuppressWarnings( "unused" )
    private void itemSelectionChanged()
    {
        List<Unique> uniques = new ArrayList<Unique>();
        for ( QTreeWidgetItem node : selectedItems() )
        {
            if ( node instanceof UniqueNode )
            {
                uniques.add( ((UniqueNode) node).getUnique() );
            }
        }

        fireSelectedUniques( uniques );
    }

    @SuppressWarnings( "unused" )
    private void navigate(
        QModelIndex index )
    {
        if ( selectedItems().size() > 0 )
        {
            QTreeWidgetItem item = selectedItems().get( 0 );
            if ( item instanceof UniqueNode && !item.parent().equals( root ) )
            { // leaf doubleclicked
                CollectionPanel.getInstance().selectUnique( ((UniqueNode) item).getUnique() );
            }
        }
    }

    @SuppressWarnings( "unused" )
    private void onExpand(
        QTreeWidgetItem parent )
    {
        if ( parent instanceof UniqueNode )
        {
            UniqueNode uniqueNode = (UniqueNode) parent;
            if ( uniqueNode.childCount() == 0 )
            {
                addChildren( uniqueNode );
            }
        }
    }

    /**
     * remove a node from its parent
     * 
     * @param node a node to remove
     */
    private void removeFromParent(
        QTreeWidgetItem node )
    {
        QTreeWidgetItem parent = node.parent();
        if ( parent == null )
        { // todo a stub. why a parent of a toplevel item is null?
            parent = root;
        }
        parent.removeChild( node );
    }

    protected abstract void addChildren(
        UniqueNode parent );

    @Override
    protected void contextMenuEvent(
        QContextMenuEvent arg__1 )
    {
        List<Unique> uniques = new ArrayList<Unique>();
        for ( QTreeWidgetItem item : selectedItems() )
        {
            if ( item instanceof UniqueNode )
            {
                uniques.add( ((UniqueNode) item).getUnique() );
            }
        }
        if ( uniques.size() > 0 )
        {
            CollectionTreeMenu menu = new CollectionTreeMenu( uniques );
            menu.exec( arg__1.globalPos() );
        }
    }

    @Override
    protected void focusInEvent(
        QFocusEvent event )
    {
        super.focusInEvent( event );
        for ( FocusListener listener : focusListeners )
        {
            listener.focusGained( null );
        }
    }

    @Override
    protected void focusOutEvent(
        QFocusEvent event )
    {
        super.focusOutEvent( event );
        for ( FocusListener listener : focusListeners )
        {
            listener.focusLost( null );
        }
    }

    protected abstract EReference getReference();
}
