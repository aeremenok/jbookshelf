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

import java.util.List;

import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.MainWindow;
import org.jbookshelf.qtgui.ToolBar;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.ext.QMenuExt;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;

import com.trolltech.qt.core.QObject;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QInputDialog;
import com.trolltech.qt.gui.QLineEdit.EchoMode;

/**
 * popups over collection tree
 * 
 * @author eav
 */
public class CollectionTreeMenu
    extends QMenuExt
    implements
        JBookShelfConstants
{
    /**
     * renames {@link Author}'s and {@link Category}'ies
     * 
     * @author eav
     */
    private static class RenameAction
        extends QAction
    {
        static final QIcon icon = new QIcon( ICONPATH + "document-properties.png" );
        final Unique       unique;

        public RenameAction(
            final QObject parent,
            final Unique unique )
        {
            super( icon, parent.tr( "Rename" ), parent );
            this.unique = unique;
            triggered.connect( RenameAction.this, "rename()" );
        }

        @SuppressWarnings( "unused" )
        private void rename()
        {
            String text = unique.getName();
            text =
                QInputDialog.getText( MainWindow.getInstance(), text(), tr( "Enter new name" ), EchoMode.Normal, text );
            text = text == null ? "" : text;
            unique.setName( text );
            CollectionPanel.getInstance().updateTree();
        }
    }

    /**
     * sets books read/unread
     * 
     * @author eav
     */
    private static class SetReadAction
        extends QAction
    {
        final List<Book> books;

        public SetReadAction(
            final QObject parent,
            final List<Book> books )
        {
            super( parent.tr( "Is read" ), parent );
            this.books = books;

            setCheckable( true );
            // todo gray checkbox
            setChecked( areAllRead() );

            triggered.connect( SetReadAction.this, "setRead()" );
        }

        private boolean areAllRead()
        {
            for ( final Book book : books )
            {
                if ( book.getRead() != 1 )
                { // at least one unread
                    return false;
                }
            }
            // all are read
            return true;
        }

        @SuppressWarnings( "unused" )
        private void setRead()
        {
            for ( final Book book : books )
            {
                book.setRead( isChecked() ? 1 : 0 );
            }
        }
    }

    public CollectionTreeMenu(
        final List<Unique> uniques )
    {
        if ( uniques.size() == 1 && (uniques.get( 0 ) instanceof Author || uniques.get( 0 ) instanceof Category) )
        { // single author or category selected
            addAction( new RenameAction( this, uniques.get( 0 ) ) );
        }
        else
        { // multiple books selected
            final List<Book> books = ToolBar.hasBooks( uniques );
            if ( books.size() > 0 )
            { // only the books can be opened, edited and read
                addAction( ToolBar.getInstance().getOpenAction() );
                addAction( ToolBar.getInstance().getOpenFolderAction() );
                addAction( ToolBar.getInstance().getEditAction() );
                addSeparator();
                addAction( new SetReadAction( this, books ) );
                addSeparator();
            }
        }

        // all can be removed
        addAction( ToolBar.getInstance().getRemoveAction() );
    }
}
