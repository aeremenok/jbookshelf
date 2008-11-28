/*
 * CollectionPanel.java Created on 15 Ноябрь 2008 г., 12:42
 */
package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbookshelf.Unique;
import org.jbookshelf.gui.widgets.panel.RelatedTreePanel;
import org.jbookshelf.gui.widgets.tree.AuthorTree;
import org.jbookshelf.gui.widgets.tree.BookTree;
import org.jbookshelf.gui.widgets.tree.CategoryTree;
import org.jbookshelf.gui.widgets.tree.CollectionTree;
import org.jbookshelf.gui.widgets.tree.UniqueNode;
import org.util.storage.Storage;

/**
 * @author eav
 */
public class CollectionPanel
    extends JPanel
{
    private static CollectionPanel instance;

    private JTree                  authorTree;
    private JTree                  bookTree;
    private JTree                  categoryTree;

    private JButton                cleanButton;
    private JComboBox              isReadComboBox;

    private JButton                searchButton;
    private JTextField             searchTextField;
    private JTabbedPane            viewTabbedPane;

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
        super( new BorderLayout() );
        initComponents();
        initListeners();
        registerComponents();
    }

    private void registerComponents()
    {
        searchButton.setName( "searchButton" );
        searchTextField.setName( "searchTextField" );

        Resourses.register( getClass(), searchTextField );
        Resourses.register( getClass(), searchButton );

        searchTextField.setText( Resourses.getString( getClass(), searchTextField ) );
        searchButton.setText( Resourses.getString( getClass(), searchButton ) );
    }

    public JTree[] getTrees()
    {
        if ( trees == null )
        {
            trees = new JTree[] { bookTree, authorTree, categoryTree };
        }
        return trees;
    }

    public void removeSelectedItem()
    {
        getActiveTree().removeSelectedItem();
    }

    public void selectRelatedUnique(
        final RelatedTreePanel relatedTreePanel,
        final Unique selectedUnique )
    {
        final CollectionTree activeTree = getActiveTree();
        activeTree.setSelectionRow( 0 );

        final MouseAdapter mouseAdapter = new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                MouseEvent e )
            {
                if ( e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 )
                {
                    Object node = activeTree.getLastSelectedPathComponent();
                    if ( node instanceof UniqueNode )
                    {
                        UniqueNode uniqueNode = (UniqueNode) node;
                        relatedTreePanel.onRelatedSelection( uniqueNode.getUnique(), selectedUnique );
                        activeTree.removeMouseListener( this );
                    }
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

    private CollectionTree getActiveTree()
    {
        return (CollectionTree) getTrees()[viewTabbedPane.getSelectedIndex()];
    }

    private void initComponents()
    {
        cleanButton = new JButton();
        searchTextField = new JTextField();
        searchButton = new JButton();
        isReadComboBox = new JComboBox();
        viewTabbedPane = new JTabbedPane();
        bookTree = new BookTree();
        authorTree = new AuthorTree();
        categoryTree = new CategoryTree();

        cleanButton.setIcon( Resourses.createIcon( "edit-clear-locationbar-rtl.png" ) );
        searchButton.setIcon( Resourses.createIcon( "edit-find.png" ) );

        isReadComboBox.setModel( new DefaultComboBoxModel( new String[] { "All", "Read", "Unread" } ) );

        JPanel searchPanel = new JPanel( new BorderLayout() );
        add( searchPanel, BorderLayout.NORTH );
        searchPanel.add( searchTextField, BorderLayout.CENTER );

        JPanel panel = new JPanel();
        searchPanel.add( panel, BorderLayout.EAST );
        panel.add( cleanButton );
        panel.add( isReadComboBox );
        panel.add( searchButton );

        add( viewTabbedPane, BorderLayout.CENTER );
        viewTabbedPane.addTab( Resourses.getSpecificString( "CollectionPanel.bookScrollPane.TabConstraints.tabTitle" ),
            new JScrollPane( bookTree ) );
        viewTabbedPane.addTab(
            Resourses.getSpecificString( "CollectionPanel.authorScrollPane.TabConstraints.tabTitle" ), new JScrollPane(
                authorTree ) );
        viewTabbedPane.addTab( Resourses
            .getSpecificString( "CollectionPanel.categoryScrollPane.TabConstraints.tabTitle" ), new JScrollPane(
            categoryTree ) );

        viewTabbedPane.setSelectedIndex( 0 );
        viewTabbedPaneStateChanged();
    }

    private void initListeners()
    {
        cleanButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                searchTextField.setText( "" );
            }
        } );
        searchButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                searchButtonActionPerformed();
            }
        } );
        viewTabbedPane.addChangeListener( new ChangeListener()
        {
            public void stateChanged(
                ChangeEvent evt )
            {
                viewTabbedPaneStateChanged();
            }
        } );
        searchTextField.addFocusListener( new FocusListener()
        {
            public void focusGained(
                FocusEvent e )
            {
                // todo searchTextField.setText( "" );
            }

            public void focusLost(
                FocusEvent e )
            {
                // todo searchTextField.setText( Resourses.getString( "CollectionPanel.searchTextField" ) );
            }
        } );
    }

    private void searchButtonActionPerformed()
    {
        String text = searchTextField.getText();
        CollectionTree tree = getActiveTree();
        if ( tree instanceof BookTree )
        {
            Boolean isRead = null;
            int index = isReadComboBox.getSelectedIndex();
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
