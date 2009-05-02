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
import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.ToolBar;
import org.jbookshelf.qtgui.logic.SourcesUniqueSelection;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.menu.CollectionTreeMenu;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QDragEnterEvent;
import com.trolltech.qt.gui.QDropEvent;
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
    extends QTreeWidget
    implements
        SourcesUniqueSelection,
        Singleton,
        Translatable
{
    private final List<UniqueSelectionListener> listeners      = new ArrayList<UniqueSelectionListener>();
    private final List<FocusListener>           focusListeners = new ArrayList<FocusListener>();
    private final List<MouseListener>           mouseListeners = new ArrayList<MouseListener>();

    protected QTreeWidgetItem                   root;

    /**
     * temporary points to a category, which node is being dragged at the moment
     */
    private Category                            draggedCategory;

    public static void removeChildren(
        final QTreeWidgetItem parent )
    {
        final int childCount = parent.childCount();
        for ( int i = 0; i < childCount; i++ )
        {
            parent.removeChild( parent.child( 0 ) );
        }
        parent.setExpanded( false );
    }

    public void addFocusListener(
        final FocusListener focusListener )
    {
        focusListeners.add( focusListener );
    }

    public void addMouseListener(
        final MouseListener mouseListener )
    {
        mouseListeners.add( mouseListener );
    }

    public void addSelectionListener(
        final UniqueSelectionListener listener )
    {
        listeners.add( listener );
    }

    public void initSingleton()
    {
        initComponents();
        initListeners();

        Translator.addTranslatable( this );
    }

    public void removeFocusListener(
        final FocusListener focusListener )
    {
        focusListeners.remove( focusListener );
    }

    public void removeMouseListener(
        final MouseListener mouseListener )
    {
        mouseListeners.remove( mouseListener );
    }

    public void removeSelectedItems()
    {
        for ( final QTreeWidgetItem item : selectedItems() )
        {
            if ( item instanceof UniqueNode )
            {
                // search recursively for its nodes
                final List<UniqueNode> nodes = new ArrayList<UniqueNode>();
                findUniqueNodes( nodes, root, ((UniqueNode) item).getUnique() );
                // remove all of them
                for ( final UniqueNode node : nodes )
                {
                    removeFromParent( node );
                    Storage.getBookShelf().removeUnique( node.getUnique() );
                }
            }
        }
    }

    public void removeSelectionListener(
        final UniqueSelectionListener listener )
    {
        listeners.remove( listener );
    }

    public void retranslate()
    {
    }

    public void selectUnique(
        final Unique unique )
    {
        clearSelection();
        for ( int i = 0; i < root.childCount(); i++ )
        {
            final UniqueNode child = (UniqueNode) root.child( i );
            if ( child.getUnique().equals( unique ) )
            {
                child.setSelected( true );
                return;
            }
        }
    }

    public void showResult(
        final List<? extends Unique> uniques )
    {
        clearSelection();

        removeChildren( root );

        for ( final Unique unique : uniques )
        {
            final UniqueNode parent = createNode( unique );
            parent.setChildIndicatorPolicy( ChildIndicatorPolicy.ShowIndicator );
            root.addChild( parent );
        }

        fireSelectedUniques( new ArrayList<Unique>() );
    }

    @SuppressWarnings( "unchecked" )
    public void update(
        final BookShelf bookShelf )
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
        final List<UniqueNode> list,
        final QTreeWidgetItem parent,
        final Unique unique )
    {
        if ( parent instanceof UniqueNode )
        {
            final UniqueNode uniqueNode = (UniqueNode) parent;
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
        final QTreeWidgetItem item,
        final Integer index )
    {
        for ( final MouseListener listener : mouseListeners )
        {
            listener.mouseClicked( null );
        }
    }

    private void fireSelectedUniques(
        final List<Unique> uniques )
    {
        for ( final UniqueSelectionListener listener : listeners )
        {
            listener.selectedUniques( uniques );
        }
    }

    private void initComponents()
    {
        setSortingEnabled( true );
        setSelectionMode( SelectionMode.ExtendedSelection );
        root = invisibleRootItem();

        setDragEnabled( true );
        setDropIndicatorShown( true );
        setDragDropMode( DragDropMode.InternalMove );
    }

    private void initListeners()
    {
        addSelectionListener( Singletons.instance( ToolBar.class ) );
        addSelectionListener( Singletons.instance( RelatedPanel.class ) );

        itemDoubleClicked.connect( this, "fireDoubleClicked(QTreeWidgetItem, Integer)" );
        itemSelectionChanged.connect( this, "itemSelectionChanged()" );
        itemExpanded.connect( this, "onExpand(QTreeWidgetItem)" );
        doubleClicked.connect( this, "navigate(QModelIndex)" );
    }

    @SuppressWarnings( "unused" )
    private void itemSelectionChanged()
    {
        final List<Unique> uniques = new ArrayList<Unique>();
        for ( final QTreeWidgetItem node : selectedItems() )
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
        final QModelIndex index )
    {
        if ( selectedItems().size() > 0 )
        {
            final QTreeWidgetItem item = selectedItems().get( 0 );
            if ( item instanceof UniqueNode && !item.parent().equals( root ) )
            { // leaf doubleclicked
                Singletons.instance( CollectionPanel.class ).selectUnique( ((UniqueNode) item).getUnique() );
            }
        }
    }

    @SuppressWarnings( "unused" )
    private void onExpand(
        final QTreeWidgetItem parent )
    {
        if ( parent instanceof UniqueNode )
        {
            final UniqueNode uniqueNode = (UniqueNode) parent;
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
        final QTreeWidgetItem node )
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

    protected void addToParent(
        final UniqueNode parent,
        final QTreeWidgetItem child )
    {
        if ( child.childCount() > 0 )
        {
            child.setText( 1, child.childCount() + "" );
            parent.addChild( child );
        }
    }

    @Override
    protected void contextMenuEvent(
        final QContextMenuEvent arg__1 )
    {
        final List<Unique> uniques = new ArrayList<Unique>();
        for ( final QTreeWidgetItem item : selectedItems() )
        {
            if ( item instanceof UniqueNode )
            {
                uniques.add( ((UniqueNode) item).getUnique() );
            }
        }
        if ( uniques.size() > 0 )
        {
            final CollectionTreeMenu menu = new CollectionTreeMenu( uniques );
            menu.exec( arg__1.globalPos() );
        }
    }

    protected UniqueNode createNode(
        final Unique unique )
    {
        return new UniqueNode( unique );
    }

    @Override
    protected void dragEnterEvent(
        final QDragEnterEvent event )
    {
        // allow dragging categories into parents
        final QTreeWidgetItem itemAt = this.itemAt( event.pos() );
        if ( itemAt instanceof UniqueNode )
        {
            final Unique unique = ((UniqueNode) itemAt).getUnique();
            if ( unique instanceof Category )
            {
                draggedCategory = (Category) unique;
                event.accept();
            }
        }
    }

    @Override
    protected void dropEvent(
        final QDropEvent event )
    {
        // allow categories accepting children
        final QTreeWidgetItem itemAt = this.itemAt( event.pos() );
        if ( itemAt instanceof UniqueNode )
        {
            final UniqueNode node = (UniqueNode) itemAt;
            if ( node.getUnique() instanceof Category )
            {
                final Category destCategory = (Category) node.getUnique();
                if ( !destCategory.equals( draggedCategory ) && !destCategory.equals( draggedCategory.getParent() ) )
                { // cannot be a parent of itself
                    if ( draggedCategory.getParent() == null )
                    { // remove category from top
                        Storage.getBookShelf().getCategories().remove( draggedCategory );
                    }
                    draggedCategory.setParent( destCategory );
                    event.accept();
                    // refresh children
                    removeChildren( node );
                    addChildren( node );
                }
            }
        }
        draggedCategory = null;
    }

    @Override
    protected void focusInEvent(
        final QFocusEvent event )
    {
        super.focusInEvent( event );
        for ( final FocusListener listener : focusListeners )
        {
            listener.focusGained( null );
        }
    }

    @Override
    protected void focusOutEvent(
        final QFocusEvent event )
    {
        super.focusOutEvent( event );
        for ( final FocusListener listener : focusListeners )
        {
            listener.focusLost( null );
        }
    }

    protected abstract EReference getReference();
}
