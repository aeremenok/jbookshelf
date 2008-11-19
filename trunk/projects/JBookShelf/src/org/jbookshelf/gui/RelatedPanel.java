/*
 * RelatedPanel.java Created on 15 Ноябрь 2008 г., 12:42
 */

package org.jbookshelf.gui;

/**
 * @author eav
 */
public class RelatedPanel
    extends javax.swing.JPanel
{
    /** Creates new form RelatedPanel */
    public RelatedPanel()
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        relatedTree = new javax.swing.JTree();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        commentTree = new javax.swing.JTree();
        dateLabel = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();
        titleTextField = new javax.swing.JTextField();
        searchTextField = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();

        jScrollPane2.setViewportView( relatedTree );

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        jTabbedPane1.addTab( bundle.getString( "RelatedPanel.jScrollPane2.TabConstraints.tabTitle" ), jScrollPane2 ); // NOI18N

        jScrollPane3.setViewportView( commentTree );

        dateLabel.setText( bundle.getString( "RelatedPanel.dateLabel.text" ) ); // NOI18N

        commentTextArea.setColumns( 20 );
        commentTextArea.setRows( 5 );
        commentTextArea.setText( bundle.getString( "RelatedPanel.commentTextArea.text" ) ); // NOI18N
        jScrollPane6.setViewportView( commentTextArea );

        titleTextField.setText( bundle.getString( "RelatedPanel.titleTextField.text" ) ); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout( jPanel4 );
        jPanel4.setLayout( jPanel4Layout );
        jPanel4Layout.setHorizontalGroup( jPanel4Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addGroup(
                jPanel4Layout.createSequentialGroup().addComponent( dateLabel ).addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( titleTextField,
                    javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE ).addContainerGap() ).addComponent(
                jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE ).addGroup(
                jPanel4Layout.createSequentialGroup().addContainerGap().addComponent( jScrollPane6,
                    javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE ).addContainerGap() ) );
        jPanel4Layout.setVerticalGroup( jPanel4Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                jPanel4Layout.createSequentialGroup().addGroup(
                    jPanel4Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent(
                        dateLabel ).addComponent( titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
                    .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jScrollPane6,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE ).addGap( 18, 18, 18 ).addComponent(
                        jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244,
                        javax.swing.GroupLayout.PREFERRED_SIZE ).addContainerGap() ) );

        jTabbedPane1.addTab( bundle.getString( "RelatedPanel.jPanel4.TabConstraints.tabTitle" ), jPanel4 ); // NOI18N

        searchTextField.setText( bundle.getString( "RelatedPanel.searchTextField.text" ) ); // NOI18N
        searchTextField.addKeyListener( new java.awt.event.KeyAdapter()
        {
            @Override
            public void keyTyped(
                java.awt.event.KeyEvent evt )
            {
                searchTextFieldKeyTyped( evt );
            }
        } );

        removeButton.setText( bundle.getString( "RelatedPanel.removeButton.text" ) ); // NOI18N
        removeButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                removeButtonActionPerformed( evt );
            }
        } );

        addButton.setText( bundle.getString( "RelatedPanel.addButton.text" ) ); // NOI18N
        addButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                addButtonActionPerformed( evt );
            }
        } );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
        this.setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
            .addComponent( jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE ).addGroup(
                layout.createSequentialGroup().addComponent( addButton ).addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( removeButton ).addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( searchTextField,
                    javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE ).addContainerGap() ) );
        layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
            javax.swing.GroupLayout.Alignment.TRAILING,
            layout.createSequentialGroup().addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( addButton )
                    .addComponent( removeButton ).addComponent( searchTextField,
                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE ) ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE ).addComponent( jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377,
                javax.swing.GroupLayout.PREFERRED_SIZE ) ) );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_removeButtonActionPerformed

    private void searchTextFieldKeyTyped(
        @SuppressWarnings( "unused" ) java.awt.event.KeyEvent evt )
    {// GEN-FIRST:event_searchTextFieldKeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_searchTextFieldKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton     addButton;
    private javax.swing.JTextArea   commentTextArea;
    private javax.swing.JTree       commentTree;
    private javax.swing.JLabel      dateLabel;
    private javax.swing.JPanel      jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTree       relatedTree;
    private javax.swing.JButton     removeButton;
    private javax.swing.JTextField  searchTextField;
    private javax.swing.JTextField  titleTextField;
    // End of variables declaration//GEN-END:variables

}
