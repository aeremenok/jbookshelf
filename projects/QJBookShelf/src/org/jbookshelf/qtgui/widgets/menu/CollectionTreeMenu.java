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

import org.jbookshelf.controller.storage.Storage;
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

        public static boolean accept(
            final Unique unique )
        {
            return unique instanceof Author || unique instanceof Category;
        }

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
            final String message = tr( "Enter new name" );
            text = QInputDialog.getText( MainWindow.getInstance(), text(), message, EchoMode.Normal, text );
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

    /**
     * moves a {@link Category} to top level
     * 
     * @author eav
     */
    private static class TopCategoryAction
        extends QAction
    {
        static final QIcon     icon = new QIcon( ICONPATH + "go-top.png" );
        private final Category category;

        public static boolean accept(
            final Unique unique )
        {
            if ( unique instanceof Category )
            {
                return ((Category) unique).getParent() != null;
            }
            return false;
        }

        public TopCategoryAction(
            final QObject parent,
            final Category category )
        {
            super( icon, parent.tr( "Move to top" ), parent );
            this.category = category;
            triggered.connect( TopCategoryAction.this, "toTop()" );
        }

        @SuppressWarnings( "unused" )
        private void toTop()
        {
            category.setParent( null );
            Storage.getBookShelf().getCategories().add( category );
            CollectionPanel.getInstance().updateTree();
        }
    }

    public CollectionTreeMenu(
        final List<Unique> uniques )
    {
        if ( uniques.size() == 1 && RenameAction.accept( uniques.get( 0 ) ) )
        { // single author or category selected
            addAction( new RenameAction( this, uniques.get( 0 ) ) );
            if ( TopCategoryAction.accept( uniques.get( 0 ) ) )
            {
                addAction( new TopCategoryAction( this, (Category) uniques.get( 0 ) ) );
            }
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
