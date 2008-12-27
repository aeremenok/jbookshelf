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
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.widgets.treepanel.CommentTreePanel;
import org.jbookshelf.qtgui.widgets.treepanel.RelatedTreePanel;
import org.jbookshelf.qtgui.widgets.treepanel.SearchableTreePanel;

import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class RelatedPanel
    extends QWidget
    implements
        UniqueSelectionListener
{
    private static final RelatedPanel instance     = new RelatedPanel();

    private SearchableTreePanel[]     searchableTreePanels;
    // private JButton addButton = new JButton();
    // private JTabbedPane tabbedPane = new JTabbedPane();
    // private JButton removeButton = new JButton();
    // private JTextField searchTextField = new JTextField();

    private SearchableTreePanel       commentPanel = new CommentTreePanel( this );
    private SearchableTreePanel       relatedPanel = new RelatedTreePanel( this );

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
        // removeButton.setEnabled( true );
    }

    public void focusLost()
    {
        // removeButton.setEnabled( false );
    }

    public SearchableTreePanel getActiveTreePanel()
    {
        return null;
        // return getSearchableTreePanels()[tabbedPane.getSelectedIndex()];
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
        // tabbedPane.addTab( Resourses.getSpecificString( "RelatedPanel.commentPanel.TabConstraints.tabTitle" ),
        // commentPanel );
        // tabbedPane.addTab( Resourses.getSpecificString( "RelatedPanel.relatedPanel.TabConstraints.tabTitle" ),
        // relatedPanel );
        //
        // searchTextField.setText( Resourses.getString( "RelatedPanel.searchTextField" ) );
        //
        // addButton.setIcon( Resourses.createIcon( "list-add-small.png" ) );
        // removeButton.setIcon( Resourses.createIcon( "list-remove-small.png" ) );
        //
        // removeButton.setEnabled( false );
        //
        // JPanel panel = new JPanel( new BorderLayout() );
        // JPanel buttonPanel = new JPanel();
        //
        // panel.add( buttonPanel, BorderLayout.WEST );
        // panel.add( searchTextField, BorderLayout.CENTER );
        //
        // buttonPanel.add( addButton );
        // buttonPanel.add( removeButton );
        //
        // add( panel, BorderLayout.NORTH );
        // add( tabbedPane, BorderLayout.CENTER );
    }

    private void initListeners()
    {
        // searchTextField.addFocusListener( new FocusListener()
        // {
        // @SuppressWarnings( "synthetic-access" )
        // public void focusGained(
        // FocusEvent e )
        // {
        // searchTextField.setText( "" );
        // }
        //
        // @SuppressWarnings( "synthetic-access" )
        // public void focusLost(
        // FocusEvent e )
        // {
        // searchTextField.setText( Resourses.getString( "RelatedPanel.searchTextField" ) );
        // }
        // } );
        //
        // searchTextField.addKeyListener( new KeyAdapter()
        // {
        // @Override
        // public void keyReleased(
        // KeyEvent e )
        // {
        // getActiveTreePanel().onKeyReleased( e );
        // }
        // } );
        // removeButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // getActiveTreePanel().onRemove();
        // }
        // } );
        // addButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // getActiveTreePanel().onAdd();
        // }
        // } );
    }
}