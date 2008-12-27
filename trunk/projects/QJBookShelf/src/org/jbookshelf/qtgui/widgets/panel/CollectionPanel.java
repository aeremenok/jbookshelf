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

import javax.swing.JTree;

import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.widgets.tree.AuthorTree;
import org.jbookshelf.qtgui.widgets.tree.BookTree;
import org.jbookshelf.qtgui.widgets.tree.CategoryTree;
import org.jbookshelf.qtgui.widgets.tree.CollectionTree;
import org.jbookshelf.qtgui.widgets.treepanel.RelatedTreePanel;

import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class CollectionPanel
    extends QWidget
{
    private static CollectionPanel instance;

    private CollectionTree         authorTree   = new AuthorTree();
    private CollectionTree         bookTree     = new BookTree();
    private CollectionTree         categoryTree = new CategoryTree();

    // private JButton cleanButton = new JButton();
    // private JComboBox isReadComboBox = new JComboBox();
    //
    // private JButton searchButton = new JButton();
    // private JTextField searchTextField = new JTextField();
    // private JTabbedPane viewTabbedPane = new JTabbedPane();

    private JTree[]                trees;

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
        // registerComponents();
    }

    public JTree[] getTrees()
    {
        return null;
        // if ( trees == null )
        // {
        // trees = new JTree[] { bookTree, authorTree, categoryTree };
        // }
        // return trees;
    }

    public void removeSelectedItem()
    {
        getActiveTree().removeSelectedItem();
    }

    public void selectRelatedUnique(
        final RelatedTreePanel relatedTreePanel,
        final Unique selectedUnique )
    {
        // final CollectionTree activeTree = getActiveTree();
        // activeTree.setSelectionRow( 0 );
        //
        // final MouseAdapter mouseAdapter = new MouseAdapter()
        // {
        // @Override
        // public void mouseClicked(
        // MouseEvent e )
        // {
        // if ( e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 )
        // {
        // Object node = activeTree.getLastSelectedPathComponent();
        // if ( node instanceof UniqueNode )
        // {
        // UniqueNode uniqueNode = (UniqueNode) node;
        // relatedTreePanel.onRelatedSelection( uniqueNode.getUnique(), selectedUnique );
        // activeTree.removeMouseListener( this );
        // }
        // }
        // }
        // };
        //
        // activeTree.addMouseListener( mouseAdapter );
        //
        // activeTree.addFocusListener( new FocusAdapter()
        // {
        // @Override
        // public void focusLost(
        // FocusEvent e )
        // {
        // activeTree.removeMouseListener( mouseAdapter );
        // activeTree.removeFocusListener( this );
        // }
        // } );
    }

    public void updateTree()
    {
        getActiveTree().update( Storage.getBookShelf() );
    }

    private CollectionTree getActiveTree()
    {
        return null;
        // return (CollectionTree) getTrees()[viewTabbedPane.getSelectedIndex()];
    }

    private void initComponents()
    {
        // cleanButton.setIcon( Resourses.createIcon( "edit-clear-locationbar-rtl.png" ) );
        // searchButton.setIcon( Resourses.createIcon( "edit-find.png" ) );
        //
        // isReadComboBox.setModel( new DefaultComboBoxModel( new String[] { "All", "Read", "Unread" } ) );
        //
        // JPanel searchPanel = new JPanel( new BorderLayout() );
        // add( searchPanel, BorderLayout.NORTH );
        // searchPanel.add( searchTextField, BorderLayout.CENTER );
        //
        // JPanel panel = new JPanel();
        // searchPanel.add( panel, BorderLayout.EAST );
        // panel.add( cleanButton );
        // panel.add( isReadComboBox );
        // panel.add( searchButton );
        //
        // add( viewTabbedPane, BorderLayout.CENTER );
        //
        // viewTabbedPane.addTab( Resourses.getSpecificString( "CollectionPanel.bookScrollPane.TabConstraints.tabTitle"
        // ),
        // new JScrollPane( bookTree ) );
        // viewTabbedPane.addTab(
        // Resourses.getSpecificString( "CollectionPanel.authorScrollPane.TabConstraints.tabTitle" ), new JScrollPane(
        // authorTree ) );
        // viewTabbedPane.addTab( Resourses
        // .getSpecificString( "CollectionPanel.categoryScrollPane.TabConstraints.tabTitle" ), new JScrollPane(
        // categoryTree ) );
        //
        // viewTabbedPane.setSelectedIndex( 0 );
        // viewTabbedPaneStateChanged();
    }

    private void initListeners()
    {
        // cleanButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // searchTextField.setText( "" );
        // }
        // } );
        // searchButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // searchButtonActionPerformed();
        // }
        // } );
        // viewTabbedPane.addChangeListener( new ChangeListener()
        // {
        // public void stateChanged(
        // ChangeEvent evt )
        // {
        // viewTabbedPaneStateChanged();
        // }
        // } );
        // searchTextField.addFocusListener( new FocusListener()
        // {
        // public void focusGained(
        // FocusEvent e )
        // {
        // // todo searchTextField.setText( "" );
        // }
        //
        // public void focusLost(
        // FocusEvent e )
        // {
        // // todo searchTextField.setText( Resourses.getString( "CollectionPanel.searchTextField" ) );
        // }
        // } );
    }

    // private void registerComponents()
    // {
    // searchButton.setName( "searchButton" );
    // searchTextField.setName( "searchTextField" );
    //
    // Resourses.register( getClass(), searchTextField );
    // Resourses.register( getClass(), searchButton );
    //
    // searchTextField.setText( Resourses.getString( getClass(), searchTextField ) );
    // searchButton.setText( Resourses.getString( getClass(), searchButton ) );
    // }

    private void searchButtonActionPerformed()
    {
        // String text = searchTextField.getText();
        // CollectionTree tree = getActiveTree();
        // if ( tree instanceof BookTree )
        // {
        // Boolean isRead = null;
        // int index = isReadComboBox.getSelectedIndex();
        // if ( index == 1 )
        // {
        // isRead = true;
        // }
        // else if ( index == 2 )
        // {
        // isRead = false;
        // }
        // tree.showResult( Storage.getBookShelf().queryUnits( text, isRead ) );
        // }
        // else if ( tree instanceof AuthorTree )
        // {
        // tree.showResult( Storage.getBookShelf().queryAuthors( text ) );
        // }
        // else
        // // (tree instanceof CategoryTree)
        // {
        // tree.showResult( Storage.getBookShelf().queryCategories( text ) );
        // }
    }

    private void viewTabbedPaneStateChanged()
    {
        // if ( getActiveTree() instanceof BookTree )
        // {
        // isReadComboBox.setEnabled( true );
        // }
        // else
        // {
        // isReadComboBox.setEnabled( false );
        // }
        // updateTree();
    }
}
