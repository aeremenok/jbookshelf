/*
 * AboutDialog.java Created on 23 Ноябрь 2008 г., 19:16
 */

package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @author eav
 */
public class AboutDialog
    extends JDialog
{
    private JLabel  header;
    private JLabel  authorLabel;
    private JLabel  authorValueLabel;
    private JLabel  licenseLabel;
    private JLabel  licenseValueLabel;
    private JLabel  versionLabel;
    private JLabel  versionValueLabel;

    private JButton closeButton;

    public AboutDialog(
        Frame parent,
        boolean modal )
    {
        super( parent, modal );
        initComponents();
        initListeners();
    }

    private void initListeners()
    {
        closeButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                dispose();
            }
        } );
    }

    private void initComponents()
    {
        header = new JLabel();
        closeButton = new JButton();
        authorLabel = new JLabel();
        authorValueLabel = new JLabel();
        versionLabel = new JLabel();
        versionValueLabel = new JLabel();
        licenseLabel = new JLabel();
        licenseValueLabel = new JLabel();

        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        JPanel mainPanel = new JPanel();
        add( mainPanel );
        mainPanel.setLayout( new BorderLayout() );

        header.setFont( new Font( "Tahoma", 1, 18 ) ); // NOI18N
        header.setHorizontalAlignment( SwingConstants.CENTER );
        mainPanel.add( header, BorderLayout.PAGE_START );

        mainPanel.add( closeButton, BorderLayout.PAGE_END );

        registerComponents();

        closeButton.setText( Resourses.getString( getClass(), closeButton ) );
        header.setText( Resourses.getString( getClass(), header ) );
        authorLabel.setText( Resourses.getString( getClass(), authorLabel ) );
        authorValueLabel.setText( Resourses.getString( getClass(), authorValueLabel ) );
        versionLabel.setText( Resourses.getString( getClass(), versionLabel ) );
        versionValueLabel.setText( Resourses.getString( getClass(), versionValueLabel ) );
        licenseLabel.setText( Resourses.getString( getClass(), licenseLabel ) );
        licenseValueLabel.setText( Resourses.getString( getClass(), licenseValueLabel ) );

        JPanel contentPanel = new JPanel();
        mainPanel.add( contentPanel, BorderLayout.CENTER );
        contentPanel.setLayout( new GridLayout( 3, 2, 10, 10 ) );

        contentPanel.add( authorLabel );
        contentPanel.add( authorValueLabel );

        contentPanel.add( versionLabel );
        contentPanel.add( versionValueLabel );

        contentPanel.add( licenseLabel );
        contentPanel.add( licenseValueLabel );

        pack();
    }

    private void registerComponents()
    {
        closeButton.setName( "closeButton" );
        authorLabel.setName( "authorLabel" );
        authorValueLabel.setName( "authorValueLabel" );
        licenseLabel.setName( "licenseLabel" );
        licenseValueLabel.setName( "licenseValueLabel" );
        versionLabel.setName( "versionLabel" );
        versionValueLabel.setName( "versionValueLabel" );
        header.setName( "header" );

        Resourses.register( getClass(), authorLabel );
        Resourses.register( getClass(), authorValueLabel );
        Resourses.register( getClass(), versionLabel );
        Resourses.register( getClass(), versionValueLabel );
        Resourses.register( getClass(), licenseLabel );
        Resourses.register( getClass(), licenseValueLabel );
        Resourses.register( getClass(), header );
        Resourses.register( getClass(), closeButton );
    }
}
