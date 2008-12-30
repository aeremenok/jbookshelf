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
package org.jbookshelf.qtgui.widgets.tree;

import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.ToolBar;
import org.jbookshelf.qtgui.logic.SoucesUniqueSelection;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.gui.QFocusEvent;
import com.trolltech.qt.gui.QTreeWidget;
import com.trolltech.qt.gui.QTreeWidgetItem;

public abstract class CollectionTree
    extends QTreeWidget
    implements
        SoucesUniqueSelection
{
    private List<UniqueSelectionListener> listeners      = new ArrayList<UniqueSelectionListener>();
    private List<FocusListener>           focusListeners = new ArrayList<FocusListener>();
    private List<MouseListener>           mouseListeners = new ArrayList<MouseListener>();

    protected QTreeWidgetItem             root;

    public CollectionTree()
    {
        super();

        initComponents();
        initListeners();

        ToolBar.getInstance().nothingSelected();
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

    public void removeSelectedItem()
    {
        UniqueNode uniqueNode = (UniqueNode) selectedItems().get( 0 );
        uniqueNode.parent().removeChild( uniqueNode );
    }

    public void removeSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.remove( listener );
    }

    public void showResult(
        EList<? extends Unique> uniques )
    {
        int childCount = root.childCount();
        for ( int i = 0; i < childCount; i++ )
        {
            root.removeChild( root.child( 0 ) );
        }

        for ( Unique unique : uniques )
        {
            UniqueNode parent = new UniqueNode( unique );
            root.addChild( parent );
            addChildren( parent );
        }

        root.setExpanded( true );
    }

    @SuppressWarnings( "unchecked" )
    public void update(
        BookShelf bookShelf )
    {
        showResult( (EList<Unique>) bookShelf.eGet( getReference() ) );
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

    private void fireNothingSelected()
    {
        for ( UniqueSelectionListener listener : listeners )
        {
            listener.nothingSelected();
        }
    }

    private void fireSelectedUnique(
        Unique unique )
    {
        for ( UniqueSelectionListener listener : listeners )
        {
            listener.selectedUnique( unique );
        }
    }

    private void initComponents()
    {
        root = invisibleRootItem();
        header().hide();
    }

    private void initListeners()
    {
        addSelectionListener( ToolBar.getInstance() );
        addSelectionListener( RelatedPanel.getInstance() );

        itemDoubleClicked.connect( this, "fireDoubleClicked(QTreeWidgetItem, Integer)" );
        itemSelectionChanged.connect( this, "itemSelectionChanged()" );
    }

    @SuppressWarnings( "unused" )
    private void itemSelectionChanged()
    {
        if ( selectedItems().size() == 0 )
        {
            fireNothingSelected();
        }
        else
        {
            QTreeWidgetItem node = selectedItems().get( 0 );
            if ( node instanceof UniqueNode )
            {
                fireSelectedUnique( ((UniqueNode) node).getUnique() );
            }
            else
            {
                fireNothingSelected();
            }
        }
    }

    protected abstract void addChildren(
        UniqueNode parent );

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
