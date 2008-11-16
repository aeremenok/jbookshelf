/*
 * CollectionPanel.java Created on 15 Ноябрь 2008 г., 12:42
 */
package org.jbookshelf.gui;

import javax.swing.JTree;

import org.jbookshelf.gui.tree.AuthorTree;
import org.jbookshelf.gui.tree.BookTree;
import org.jbookshelf.gui.tree.CategoryTree;
import org.jbookshelf.gui.tree.CollectionTree;
import org.util.storage.Storage;

/**
 * @author eav
 */
public class CollectionPanel
    extends javax.swing.JPanel
{

    private static CollectionPanel instance;

    /**
     * Creates new form CollectionPanel
     * 
     * @param mainWindow
     */
    public CollectionPanel()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        cleanButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        isReadComboBox = new javax.swing.JComboBox();
        viewTabbedPane = new javax.swing.JTabbedPane();
        bookScrollPane = new javax.swing.JScrollPane();
        bookTree = new BookTree();
        authorScrollPane = new javax.swing.JScrollPane();
        authorTree = new AuthorTree();
        categoryScrollPane = new javax.swing.JScrollPane();
        categoryTree = new CategoryTree();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        cleanButton.setText( bundle.getString( "CollectionPanel.cleanButton.text" ) ); // NOI18N
        cleanButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                cleanButtonActionPerformed( evt );
            }
        } );

        searchTextField.setText( bundle.getString( "CollectionPanel.searchTextField.text" ) ); // NOI18N

        searchButton.setText( bundle.getString( "CollectionPanel.searchButton.text" ) ); // NOI18N
        searchButton.setAutoscrolls( true );
        searchButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                searchButtonActionPerformed( evt );
            }
        } );

        isReadComboBox.setModel( new javax.swing.DefaultComboBoxModel( new String[] { "All", "Read", "Unread" } ) );

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout( searchPanel );
        searchPanel.setLayout( searchPanelLayout );
        searchPanelLayout.setHorizontalGroup( searchPanelLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
            javax.swing.GroupLayout.Alignment.TRAILING,
            searchPanelLayout.createSequentialGroup().addContainerGap().addComponent( searchTextField,
                javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE ).addComponent( isReadComboBox,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( cleanButton ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( searchButton ) ) );
        searchPanelLayout.setVerticalGroup( searchPanelLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
            searchPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent(
                searchButton ).addComponent( cleanButton ).addComponent( searchTextField,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent( isReadComboBox,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE ) ) );

        viewTabbedPane.addChangeListener( new javax.swing.event.ChangeListener()
        {
            public void stateChanged(
                javax.swing.event.ChangeEvent evt )
            {
                viewTabbedPaneStateChanged( evt );
            }
        } );

        bookScrollPane.setViewportView( bookTree );

        viewTabbedPane.addTab( bundle.getString( "CollectionPanel.bookScrollPane.TabConstraints.tabTitle" ),
            bookScrollPane ); // NOI18N

        authorScrollPane.setViewportView( authorTree );

        viewTabbedPane.addTab( bundle.getString( "CollectionPanel.authorScrollPane.TabConstraints.tabTitle" ),
            authorScrollPane ); // NOI18N

        categoryScrollPane.setViewportView( categoryTree );

        viewTabbedPane.addTab( bundle.getString( "CollectionPanel.categoryScrollPane.TabConstraints.tabTitle" ),
            categoryScrollPane ); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout( jPanel1 );
        jPanel1.setLayout( jPanel1Layout );
        jPanel1Layout.setHorizontalGroup( jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addComponent( viewTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE ).addComponent(
                searchPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) );
        jPanel1Layout.setVerticalGroup( jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addGroup(
                jPanel1Layout.createSequentialGroup().addComponent( searchPanel,
                    javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE ).addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( viewTabbedPane,
                    javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE ) ) );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
        this.setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGap( 0,
            599, Short.MAX_VALUE ).addGroup(
            layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
                layout.createSequentialGroup().addGap( 0, 0, Short.MAX_VALUE ).addComponent( jPanel1,
                    javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE ).addGap( 0, 0, Short.MAX_VALUE ) ) ) );
        layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGap( 0,
            502, Short.MAX_VALUE ).addGroup(
            layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
                layout.createSequentialGroup().addGap( 0, 0, Short.MAX_VALUE ).addComponent( jPanel1,
                    javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE ).addGap( 0, 0, Short.MAX_VALUE ) ) ) );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_searchButtonActionPerformed
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
        // (tree instanceoof CategoryTree)
        {
            tree.showResult( Storage.getBookShelf().queryCategories( text ) );
        }
    }// GEN-LAST:event_searchButtonActionPerformed

    private void cleanButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_cleanButtonActionPerformed
        searchTextField.setText( "" );
    }// GEN-LAST:event_cleanButtonActionPerformed

    private void viewTabbedPaneStateChanged(
        @SuppressWarnings( "unused" ) javax.swing.event.ChangeEvent evt )
    {// GEN-FIRST:event_viewTabbedPaneStateChanged
        if ( getActiveTree() instanceof BookTree )
        {
            isReadComboBox.setEnabled( true );
        }
        else
        {
            isReadComboBox.setEnabled( false );
        }
        updateTree();
    }// GEN-LAST:event_viewTabbedPaneStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane authorScrollPane;
    private javax.swing.JTree       authorTree;
    private javax.swing.JScrollPane bookScrollPane;
    private javax.swing.JTree       bookTree;
    private javax.swing.JScrollPane categoryScrollPane;
    private javax.swing.JTree       categoryTree;
    private javax.swing.JButton     cleanButton;
    private javax.swing.JComboBox   isReadComboBox;
    private javax.swing.JPanel      jPanel1;
    private javax.swing.JButton     searchButton;
    private javax.swing.JPanel      searchPanel;
    private javax.swing.JTextField  searchTextField;
    private javax.swing.JTabbedPane viewTabbedPane;
    // End of variables declaration//GEN-END:variables

    private JTree[]                 trees;

    public JTree[] getTrees()
    {
        if ( trees == null )
        {
            trees = new JTree[] { bookTree, authorTree, categoryTree };
        }
        return trees;
    }

    public void updateTree()
    {
        getActiveTree().update( Storage.getBookShelf() );
    }

    public static CollectionPanel getInstance()
    {
        if ( instance == null )
        {
            instance = new CollectionPanel();
        }
        return instance;
    }

    public void removeSelectedItem()
    {
        getActiveTree().removeSelectedItem();
    }

    private CollectionTree getActiveTree()
    {
        return (CollectionTree) getTrees()[viewTabbedPane.getSelectedIndex()];
    }
}
