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
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.qtgui.widgets.menu;

import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.ToolBar;
import org.jbookshelf.qtgui.widgets.ext.QMenuExt;
import org.jbookshelf.qtgui.widgets.tree.UniqueNode;

import com.trolltech.qt.gui.QAction;

/**
 * popups over collection tree
 * 
 * @author eav
 */
public class CollectionTreeMenu
    extends QMenuExt
{
    /**
     * what to manage
     */
    private Unique unique;

    public CollectionTreeMenu(
        UniqueNode uniqueNode )
    {
        unique = uniqueNode.getUnique();

        if ( unique instanceof ReadingUnit )
        { // only the book can be opened and edited
            addAction( ToolBar.getInstance().getOpenAction() );
            addAction( ToolBar.getInstance().getOpenFolderAction() );
            addAction( ToolBar.getInstance().getEditAction() );
        }

        // all can be removed
        addAction( ToolBar.getInstance().getRemoveAction() );

        if ( unique instanceof ReadingUnit )
        { // only the book can be read
            addSeparator();
            addAction( isReadAction( (ReadingUnit) unique ) );
        }
    }

    private QAction isReadAction(
        ReadingUnit book )
    {
        QAction isReadAction = new QAction( tr( "Is Read" ), this );
        isReadAction.setCheckable( true );
        isReadAction.setChecked( book.isRead() );

        isReadAction.toggled.connect( this, "setRead()" );

        return isReadAction;
    }

    @SuppressWarnings( "unused" )
    private void setRead()
    {
        ReadingUnit book = (ReadingUnit) unique;
        book.setRead( !book.isRead() );
    }
}
