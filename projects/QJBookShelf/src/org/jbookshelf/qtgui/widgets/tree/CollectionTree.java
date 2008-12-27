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

public abstract class CollectionTree
    // extends JTree
    implements
        SoucesUniqueSelection
{
    private List<UniqueSelectionListener> listeners = new ArrayList<UniqueSelectionListener>();

    // protected DefaultMutableTreeNode root = new DefaultMutableTreeNode();

    public CollectionTree()
    {
        super();

        // ((DefaultTreeModel) getModel()).setRoot( root );
        // setExpandsSelectedPaths( true );
        //
        // getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        // setShowsRootHandles( false );

        addSelectionListener( ToolBar.getInstance() );
        addSelectionListener( RelatedPanel.getInstance() );

        // addTreeSelectionListener( new TreeSelectionListener()
        // {
        // @SuppressWarnings( "synthetic-access" )
        // public void valueChanged(
        // TreeSelectionEvent e )
        // {
        // DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
        // if ( node == null )
        // {
        // fireNothingSelected();
        // }
        // else
        // {
        // if ( node instanceof UniqueNode )
        // {
        // fireSelectedUnique( ((UniqueNode) node).getUnique() );
        // }
        // else
        // {
        // fireNothingSelected();
        // }
        // }
        // }
        // 
        // } );

        ToolBar.getInstance().nothingSelected();
    }

    public void addSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.add( listener );
    }

    public void removeSelectedItem()
    {
        // UniqueNode uniqueNode = (UniqueNode) getLastSelectedPathComponent();
        // uniqueNode.removeFromParent();
        // updateUI();
    }

    public void removeSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.remove( listener );
    }

    public void showResult(
        EList<? extends Unique> uniques )
    {
        // root.removeAllChildren();
        //
        // for ( Unique unique : uniques )
        // {
        // UniqueNode parent = new UniqueNode( unique );
        // root.add( parent );
        // addChildren( parent );
        // }
        //
        // expandRow( 0 );
        // setRootVisible( false );
        //
        // updateUI();
    }

    @SuppressWarnings( "unchecked" )
    public void update(
        BookShelf bookShelf )
    {
        showResult( (EList<Unique>) bookShelf.eGet( getReference() ) );
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

    protected abstract void addChildren(
        UniqueNode parent );

    protected abstract EReference getReference();
}
