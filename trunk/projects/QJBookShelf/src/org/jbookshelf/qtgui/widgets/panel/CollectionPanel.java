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
package org.jbookshelf.qtgui.widgets.panel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.ext.QWidgetExt;
import org.jbookshelf.qtgui.widgets.tree.AuthorTree;
import org.jbookshelf.qtgui.widgets.tree.BookTree;
import org.jbookshelf.qtgui.widgets.tree.CategoryTree;
import org.jbookshelf.qtgui.widgets.tree.CollectionTree;
import org.jbookshelf.qtgui.widgets.tree.UniqueNode;
import org.jbookshelf.qtgui.widgets.treepanel.RelatedTreePanel;

import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QTreeWidgetItem;

/**
 * @author eav
 */
public class CollectionPanel
    extends QWidgetExt
    implements
        JBookShelfConstants
{
    private static CollectionPanel instance;

    private CollectionTree         authorTree      = new AuthorTree();
    private CollectionTree         bookTree        = new BookTree();
    private CollectionTree         categoryTree    = new CategoryTree();

    private QPushButton            cleanButton     = new QPushButton( this );
    private QPushButton            searchButton    = new QPushButton( this );
    private QComboBox              isReadComboBox  = new QComboBox( this );

    private QLineEdit              searchTextField = new QLineEdit( this );
    private QTabWidget             viewTabbedPane  = new QTabWidget( this );

    public static CollectionPanel getInstance()
    {
        if ( instance == null )
        {
            instance = new CollectionPanel();
        }
        return instance;
    }

    public CollectionPanel()
    {
        initComponents();
        initListeners();

        Translator.addTranslatable( this );
    }

    public void removeSelectedItem()
    {
        getActiveTree().removeSelectedItem();
    }

    public void retranslate()
    {
        searchButton.setText( tr( "Search" ) );
        searchTextField.setText( tr( "search..." ) );

        viewTabbedPane.setTabText( 0, tr( "Books" ) );
        viewTabbedPane.setTabText( 1, tr( "Authors" ) );
        viewTabbedPane.setTabText( 2, tr( "Categories" ) );

        isReadComboBox.setItemText( 0, tr( "All" ) );
        isReadComboBox.setItemText( 1, tr( "Read" ) );
        isReadComboBox.setItemText( 2, tr( "Unread" ) );
    }

    public void selectRelatedUnique(
        final RelatedTreePanel relatedTreePanel,
        final Unique selectedUnique )
    {
        final CollectionTree activeTree = getActiveTree();

        final MouseAdapter mouseAdapter = new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                MouseEvent e )
            {
                QTreeWidgetItem node = activeTree.selectedItems().get( 0 );
                if ( node instanceof UniqueNode )
                {
                    UniqueNode uniqueNode = (UniqueNode) node;
                    relatedTreePanel.relatedSelectionFinished( uniqueNode.getUnique(), selectedUnique );
                    activeTree.removeMouseListener( this );
                }
            }
        };

        activeTree.addMouseListener( mouseAdapter );

        activeTree.addFocusListener( new FocusAdapter()
        {
            @Override
            public void focusLost(
                FocusEvent e )
            {
                activeTree.removeMouseListener( mouseAdapter );
                activeTree.removeFocusListener( this );
            }
        } );
    }

    public void updateTree()
    {
        getActiveTree().update( Storage.getBookShelf() );
    }

    @SuppressWarnings( "unused" )
    private void clean()
    {
        searchTextField.setText( "" );
    }

    private CollectionTree getActiveTree()
    {
        return (CollectionTree) viewTabbedPane.currentWidget();
    }

    private void initComponents()
    {
        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        cleanButton.setIcon( new QIcon( ICONPATH + "edit-clear-locationbar-rtl.png" ) );
        searchButton.setIcon( new QIcon( ICONPATH + "edit-find.png" ) );

        isReadComboBox.addItem( "" );
        isReadComboBox.addItem( "" );
        isReadComboBox.addItem( "" );

        layout.addWidget( searchTextField, 0, 0 );
        layout.addWidget( cleanButton, 0, 1 );
        layout.addWidget( isReadComboBox, 0, 2 );
        layout.addWidget( searchButton, 0, 3 );

        layout.addWidget( viewTabbedPane, 1, 0, 1, 4 );

        viewTabbedPane.addTab( bookTree, "" );
        viewTabbedPane.addTab( authorTree, "" );
        viewTabbedPane.addTab( categoryTree, "" );

        updateTree();
    }

    private void initListeners()
    {
        cleanButton.released.connect( this, "clean()" );
        searchButton.released.connect( this, "searchButtonActionPerformed()" );
        viewTabbedPane.currentChanged.connect( this, "viewTabbedPaneStateChanged()" );
    }

    @SuppressWarnings( "unused" )
    private void searchButtonActionPerformed()
    {
        String text = searchTextField.text();
        CollectionTree tree = getActiveTree();
        if ( tree instanceof BookTree )
        {
            Boolean isRead = null;
            int index = isReadComboBox.currentIndex();
            if ( index == 1 )
            {
                isRead = true;
            }
            else if ( index == 2 )
            {
                isRead = false;
            }
            tree.showResult( Storage.getBookShelf().queryUnits( text, isRead ) );
        }
        else if ( tree instanceof AuthorTree )
        {
            tree.showResult( Storage.getBookShelf().queryAuthors( text ) );
        }
        else
        // (tree instanceof CategoryTree)
        {
            tree.showResult( Storage.getBookShelf().queryCategories( text ) );
        }
    }

    @SuppressWarnings( "unused" )
    private void viewTabbedPaneStateChanged()
    {
        if ( getActiveTree() instanceof BookTree )
        {
            isReadComboBox.setEnabled( true );
        }
        else
        {
            isReadComboBox.setEnabled( false );
        }
        updateTree();
    }
}
