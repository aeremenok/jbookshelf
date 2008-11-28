/*
 * CollectionPanel.java Created on 15 Ноябрь 2008 г., 12:42
 */
package org.jbookshelf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
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

    private JScrollPane            authorScrollPane;
    private JTree                  authorTree;
    private JScrollPane            bookScrollPane;
    private JTree                  bookTree;
    private JScrollPane            categoryScrollPane;

    private JTree                  categoryTree;
    private JButton                cleanButton;
    private JComboBox              isReadComboBox;
    private JPanel                 jPanel1;
    private JButton                searchButton;
    private JPanel                 searchPanel;
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
        initComponents();
        initListeners();
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
        jPanel1 = new JPanel();
        searchPanel = new JPanel();
        cleanButton = new JButton();
        searchTextField = new JTextField();
        searchButton = new JButton();
        isReadComboBox = new JComboBox();
        viewTabbedPane = new JTabbedPane();
        bookScrollPane = new JScrollPane();
        bookTree = new BookTree();
        authorScrollPane = new JScrollPane();
        authorTree = new AuthorTree();
        categoryScrollPane = new JScrollPane();
        categoryTree = new CategoryTree();

        cleanButton.setIcon( new ImageIcon( getClass().getResource(
            "/org/jbookshelf/gui/images/edit-clear-locationbar-rtl.png" ) ) ); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        searchTextField.setText( bundle.getString( "CollectionPanel.searchTextField.text" ) ); // NOI18N

        searchButton.setIcon( new ImageIcon( getClass().getResource( "/org/jbookshelf/gui/images/edit-find.png" ) ) ); // NOI18N
        searchButton.setText( bundle.getString( "CollectionPanel.searchButton.text" ) ); // NOI18N
        searchButton.setAutoscrolls( true );

        isReadComboBox.setModel( new DefaultComboBoxModel( new String[] { "All", "Read", "Unread" } ) );

        GroupLayout searchPanelLayout = new GroupLayout( searchPanel );
        searchPanel.setLayout( searchPanelLayout );
        searchPanelLayout.setHorizontalGroup( searchPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup(
                GroupLayout.Alignment.TRAILING,
                searchPanelLayout.createSequentialGroup().addContainerGap().addComponent( searchTextField,
                    GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE ).addPreferredGap(
                    LayoutStyle.ComponentPlacement.UNRELATED ).addComponent( cleanButton, GroupLayout.PREFERRED_SIZE,
                    50, GroupLayout.PREFERRED_SIZE ).addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED )
                    .addComponent( isReadComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE ).addGap( 18, 18, 18 ).addComponent( searchButton ) ) );
        searchPanelLayout.setVerticalGroup( searchPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup(
                searchPanelLayout.createSequentialGroup().addGroup(
                    searchPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
                        searchPanelLayout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent(
                            searchButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE ).addComponent(
                            searchTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE ) )
                        .addComponent( isReadComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE )
                        .addComponent( cleanButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE ) )
                    .addContainerGap() ) );

        bookScrollPane.setViewportView( bookTree );

        viewTabbedPane.addTab( bundle.getString( "CollectionPanel.bookScrollPane.TabConstraints.tabTitle" ),
            bookScrollPane ); // NOI18N

        authorScrollPane.setViewportView( authorTree );

        viewTabbedPane.addTab( bundle.getString( "CollectionPanel.authorScrollPane.TabConstraints.tabTitle" ),
            authorScrollPane ); // NOI18N

        categoryScrollPane.setViewportView( categoryTree );

        viewTabbedPane.addTab( bundle.getString( "CollectionPanel.categoryScrollPane.TabConstraints.tabTitle" ),
            categoryScrollPane ); // NOI18N

        GroupLayout jPanel1Layout = new GroupLayout( jPanel1 );
        jPanel1.setLayout( jPanel1Layout );
        jPanel1Layout.setHorizontalGroup( jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addComponent( searchPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ).addComponent( viewTabbedPane, GroupLayout.DEFAULT_SIZE,
                715, Short.MAX_VALUE ) );
        jPanel1Layout.setVerticalGroup( jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
            jPanel1Layout.createSequentialGroup().addComponent( searchPanel, GroupLayout.PREFERRED_SIZE, 30,
                GroupLayout.PREFERRED_SIZE ).addPreferredGap( LayoutStyle.ComponentPlacement.RELATED ).addComponent(
                viewTabbedPane, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE ) ) );

        GroupLayout layout = new GroupLayout( this );
        this.setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0, 715,
            Short.MAX_VALUE ).addGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
                layout.createSequentialGroup().addGap( 0, 0, Short.MAX_VALUE ).addComponent( jPanel1,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addGap( 0, 0,
                    Short.MAX_VALUE ) ) ) );
        layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0, 531,
            Short.MAX_VALUE ).addGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
                layout.createSequentialGroup().addGap( 0, 0, Short.MAX_VALUE ).addComponent( jPanel1,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ).addGap( 0, 0,
                    Short.MAX_VALUE ) ) ) );
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
