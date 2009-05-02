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
package org.jbookshelf.qtgui.widgets.panel;

import java.util.List;

import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.ext.QWidgetExt;
import org.jbookshelf.qtgui.widgets.ext.SearchTextField;
import org.jbookshelf.qtgui.widgets.treepanel.CommentTreePanel;
import org.jbookshelf.qtgui.widgets.treepanel.RelatedTreePanel;
import org.jbookshelf.qtgui.widgets.treepanel.SearchableTreePanel;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTabWidget;

/**
 * @author eav
 */
public class RelatedPanel
    extends QWidgetExt
    implements
        JBookShelfConstants,
        UniqueSelectionListener,
        Singleton
{
    private final QPushButton addButton       = new QPushButton( this );
    private final QPushButton removeButton    = new QPushButton( this );
    private final QLineEdit   searchTextField = new SearchTextField( this );

    private final QTabWidget  tabbedPane      = new QTabWidget( this );

    public SearchableTreePanel getActiveTreePanel()
    {
        return (SearchableTreePanel) tabbedPane.currentWidget();
    }

    public QPushButton getRemoveButton()
    {
        return removeButton;
    }

    public void initSingleton()
    {
        initComponents();
        initListeners();

        Translator.addTranslatable( this );
    }

    public void itemSelected()
    {
        removeButton.setEnabled( true );
    }

    public void nothingSelected()
    {
        removeButton.setEnabled( false );
    }

    public void retranslate()
    {
        tabbedPane.setTabText( 0, tr( "Comments" ) );
        tabbedPane.setTabText( 1, tr( "Related" ) );
    }

    public void selectedUniques(
        final List<Unique> uniques )
    {
        if ( uniques.size() > 0 )
        {
            addButton.setEnabled( true );
        }
        else
        {
            addButton.setEnabled( false );
            removeButton.setEnabled( false );
        }

        Singletons.instance( CommentTreePanel.class ).selectedUniques( uniques );
        Singletons.instance( RelatedTreePanel.class ).selectedUniques( uniques );
    }

    private void initComponents()
    {
        final QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( addButton, 0, 0 );
        layout.addWidget( removeButton, 0, 1 );
        layout.addWidget( searchTextField, 0, 2 );

        layout.addWidget( tabbedPane, 1, 0, 1, 3 );

        tabbedPane.addTab( Singletons.instance( CommentTreePanel.class ), "" );
        tabbedPane.addTab( Singletons.instance( RelatedTreePanel.class ), "" );

        addButton.setIcon( new QIcon( ICONPATH + "list-add-small.png" ) );
        removeButton.setIcon( new QIcon( ICONPATH + "list-remove-small.png" ) );
        removeButton.setEnabled( false );

        addButton.setToolTip( tr( "Add" ) );
        removeButton.setToolTip( tr( "Remove" ) );
    }

    private void initListeners()
    {
        searchTextField.textChanged.connect( this, "onSearch()" );
        removeButton.released.connect( this, "onRemove()" );
        addButton.released.connect( this, "onAdd()" );
    }

    @SuppressWarnings( "unused" )
    private void onAdd()
    {
        getActiveTreePanel().onAdd();
    }

    @SuppressWarnings( "unused" )
    private void onRemove()
    {
        getActiveTreePanel().onRemove();
    }

    @SuppressWarnings( "unused" )
    private void onSearch()
    {
        getActiveTreePanel().search( searchTextField.text() );
    }
}
