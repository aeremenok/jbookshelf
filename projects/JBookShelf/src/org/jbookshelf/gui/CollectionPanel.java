/*
 * CollectionPanel.java Created on 15 Ноябрь 2008 г., 12:42
 */

package org.jbookshelf.gui;

import org.jbookshelf.BookShelf;

/**
 * @author eav
 */
public class CollectionPanel
    extends javax.swing.JPanel
{
    private BookShelf bookShelf;

    /** Creates new form CollectionPanel */
    public CollectionPanel()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree3 = new javax.swing.JTree();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTree4 = new javax.swing.JTree();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTree5 = new javax.swing.JTree();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        jButton5.setText( bundle.getString( "CollectionPanel.jButton5.text" ) ); // NOI18N

        jTextField1.setText( bundle.getString( "CollectionPanel.jTextField1.text" ) ); // NOI18N

        jButton6.setText( bundle.getString( "CollectionPanel.jButton6.text" ) ); // NOI18N
        jButton6.setAutoscrolls( true );

        jComboBox1.setModel( new javax.swing.DefaultComboBoxModel( new String[] { "All", "Read", "Unread" } ) );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout( jPanel3 );
        jPanel3.setLayout( jPanel3Layout );
        jPanel3Layout.setHorizontalGroup( jPanel3Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                jPanel3Layout.createSequentialGroup().addContainerGap().addComponent( jTextField1,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE )
                    .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE )
                    .addComponent( jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButton5 ).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButton6 ) ) );
        jPanel3Layout.setVerticalGroup( jPanel3Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addGroup(
                jPanel3Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jButton6 )
                    .addComponent( jButton5 ).addComponent( jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent(
                        jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE ) ) );

        jScrollPane1.setViewportView( jTree3 );

        jTabbedPane2.addTab( bundle.getString( "CollectionPanel.jScrollPane1.TabConstraints.tabTitle" ), jScrollPane1 ); // NOI18N

        jScrollPane4.setViewportView( jTree4 );

        jTabbedPane2.addTab( bundle.getString( "CollectionPanel.jScrollPane4.TabConstraints.tabTitle" ), jScrollPane4 ); // NOI18N

        jScrollPane5.setViewportView( jTree5 );

        jTabbedPane2.addTab( bundle.getString( "CollectionPanel.jScrollPane5.TabConstraints.tabTitle" ), jScrollPane5 ); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout( jPanel1 );
        jPanel1.setLayout( jPanel1Layout );
        jPanel1Layout.setHorizontalGroup( jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addComponent( jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE ).addComponent(
                jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) );
        jPanel1Layout.setVerticalGroup( jPanel1Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addGroup(
                jPanel1Layout.createSequentialGroup().addComponent( jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ).addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jTabbedPane2,
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton     jButton5;
    private javax.swing.JButton     jButton6;
    private javax.swing.JComboBox   jComboBox1;
    private javax.swing.JPanel      jPanel1;
    private javax.swing.JPanel      jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField  jTextField1;
    private javax.swing.JTree       jTree3;
    private javax.swing.JTree       jTree4;
    private javax.swing.JTree       jTree5;

    // End of variables declaration//GEN-END:variables

    public void setCollection(
        BookShelf bookShelf )
    {
        this.bookShelf = bookShelf;
    }
}
