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

import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.menu.RelatedTreeMenu;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QTreeWidget;
import com.trolltech.qt.gui.QTreeWidgetItem;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;

/**
 * Panel with immediate tree search
 * 
 * @author eav
 */
public abstract class SearchableTreePanel
    extends QWidget
    implements
        UniqueSelectionListener
{
    /**
     * tree with context menu
     * 
     * @author eav
     */
    protected class SearchableTree
        extends QTreeWidget
    {
        @Override
        protected void contextMenuEvent(
            QContextMenuEvent arg__1 )
        {
            QTreeWidgetItem item = itemAt( arg__1.pos() );
            if ( item != null )
            {
                item.setSelected( true );

                RelatedTreeMenu menu = new RelatedTreeMenu( relatedPanel );
                menu.exec( arg__1.globalPos() );
            }
        }
    }

    /**
     * a panel which contains and controls us
     */
    protected final RelatedPanel   relatedPanel;
    /**
     * a working tree
     */
    protected final SearchableTree searchableTree = new SearchableTree();
    /**
     * its root
     */
    protected QTreeWidgetItem      root;

    public SearchableTreePanel(
        RelatedPanel relatedPanel )
    {
        this.relatedPanel = relatedPanel;

        root = searchableTree.invisibleRootItem();
        searchableTree.header().hide();
        searchableTree.setSelectionMode( SelectionMode.SingleSelection );
    }

    /**
     * add a new tree item
     */
    public abstract void onAdd();

    /**
     * remove selected tree item
     */
    public abstract void onRemove();

    /**
     * show only items with captions containng text
     * 
     * @param text text to search
     */
    public abstract void search(
        String text );
}
