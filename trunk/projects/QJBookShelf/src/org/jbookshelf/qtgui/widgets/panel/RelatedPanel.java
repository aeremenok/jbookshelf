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

import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.treepanel.CommentTreePanel;
import org.jbookshelf.qtgui.widgets.treepanel.RelatedTreePanel;
import org.jbookshelf.qtgui.widgets.treepanel.SearchableTreePanel;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class RelatedPanel
    extends QWidget
    implements
        JBookShelfConstants,
        UniqueSelectionListener
{
    private static final RelatedPanel instance        = new RelatedPanel();

    private SearchableTreePanel[]     searchableTreePanels;

    private QPushButton               addButton       = new QPushButton();
    private QPushButton               removeButton    = new QPushButton();

    private QTabWidget                tabbedPane      = new QTabWidget();
    private QLineEdit                 searchTextField = new QLineEdit();

    private SearchableTreePanel       commentPanel    = new CommentTreePanel( this );
    private SearchableTreePanel       relatedPanel    = new RelatedTreePanel( this );

    public static RelatedPanel getInstance()
    {
        return instance;
    }

    public RelatedPanel()
    {
        initComponents();
        initListeners();
    }

    public void focusGained()
    {
        removeButton.setEnabled( true );
    }

    public void focusLost()
    {
        removeButton.setEnabled( false );
    }

    public SearchableTreePanel getActiveTreePanel()
    {
        return getSearchableTreePanels()[tabbedPane.currentIndex()];
    }

    public SearchableTreePanel[] getSearchableTreePanels()
    {
        if ( searchableTreePanels == null )
        {
            searchableTreePanels = new SearchableTreePanel[] { commentPanel, relatedPanel };
        }
        return searchableTreePanels;
    }

    public void nothingSelected()
    {
        for ( SearchableTreePanel panel : getSearchableTreePanels() )
        {
            panel.nothingSelected();
        }
    }

    public void selectedUnique(
        Unique unique )
    {
        for ( SearchableTreePanel panel : getSearchableTreePanels() )
        {
            panel.selectedUnique( unique );
        }
    }

    private void initComponents()
    {
        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( addButton, 0, 0 );
        layout.addWidget( removeButton, 0, 1 );
        layout.addWidget( searchTextField, 0, 2 );

        layout.addWidget( tabbedPane, 1, 0, 1, 3 );

        tabbedPane.addTab( commentPanel, tr( "Comments" ) );
        tabbedPane.addTab( relatedPanel, tr( "Related" ) );

        searchTextField.setText( tr( "search..." ) );

        addButton.setIcon( new QIcon( ICONPATH + "list-add-small.png" ) );
        removeButton.setIcon( new QIcon( ICONPATH + "list-remove-small.png" ) );
        removeButton.setEnabled( false );
    }

    private void initListeners()
    {
        searchTextField.textChanged.connect( this, "keyReleased()" );
        removeButton.released.connect( this, "onRemove()" );
        addButton.released.connect( this, "onAdd()" );
    }

    @SuppressWarnings( "unused" )
    private void keyReleased()
    {
        getActiveTreePanel().search( searchTextField.text() );
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
}
