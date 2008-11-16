/*
 * BookAdditionDialog.java Created on 1 Ноябрь 2008 г., 16:35
 */

package org.jbookshelf.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jdesktop.swingx.VerticalLayout;

/**
 * @author eav
 */
public class BookAdditionDialog
    extends JDialog
{
    public BookAdditionDialog(
        java.awt.Frame parent,
        boolean modal )
    {
        super( parent, modal );
        initComponents();
    }

    private void initComponents()
    {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        addNContinueButton = new javax.swing.JButton();
        addNCloseButton = new javax.swing.JButton();
        bookPanel = new BookPanel();

        setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );

        jLabel1.setFont( new java.awt.Font( "Tahoma", 1, 14 ) );
        jLabel1.setText( "Add book to Collection" );

        cancelButton.setText( "Cancel" );
        cancelButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                cancelButtonActionPerformed( evt );
            }
        } );

        addNContinueButton.setText( "Add And Continue" );
        addNContinueButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                addNContinueButtonActionPerformed( evt );
            }
        } );

        addNCloseButton.setText( "Add And Close" );
        addNCloseButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                addNCloseButtonActionPerformed( evt );
            }
        } );

        JPanel contentPanel = new JPanel( new VerticalLayout() );
        add( contentPanel );

        contentPanel.add( jLabel1 );
        contentPanel.add( jSeparator1 );
        contentPanel.add( bookPanel );
        JPanel buttonPanel = new JPanel();
        contentPanel.add( buttonPanel );
        buttonPanel.add( addNContinueButton );
        buttonPanel.add( addNCloseButton );
        buttonPanel.add( cancelButton );

        pack();
    }

    private void cancelButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {
        dispose();
    }

    private void addNContinueButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {
        if ( getBookPanel().addBook() )
        {
            getBookPanel().clear();
        }
    }

    private void addNCloseButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {
        if ( getBookPanel().addBook() )
        {
            dispose();
        }
    }

    public BookPanel getBookPanel()
    {
        return (BookPanel) bookPanel;
    }

    private javax.swing.JButton    addNCloseButton;
    private javax.swing.JButton    addNContinueButton;
    private javax.swing.JPanel     bookPanel;
    private javax.swing.JButton    cancelButton;
    private javax.swing.JLabel     jLabel1;
    private javax.swing.JSeparator jSeparator1;
}
