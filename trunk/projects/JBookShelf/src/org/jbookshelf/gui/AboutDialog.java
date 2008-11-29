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
    private JLabel  header            = new JLabel();
    private JLabel  authorLabel       = new JLabel();
    private JLabel  authorValueLabel  = new JLabel();
    private JLabel  licenseLabel      = new JLabel();
    private JLabel  licenseValueLabel = new JLabel();
    private JLabel  versionLabel      = new JLabel();
    private JLabel  versionValueLabel = new JLabel();

    private JButton closeButton       = new JButton();

    public AboutDialog(
        Frame parent,
        boolean modal )
    {
        super( parent, modal );
        registerComponents();
        initComponents();
        initListeners();
    }

    private void initComponents()
    {
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        JPanel mainPanel = new JPanel();
        add( mainPanel );
        mainPanel.setLayout( new BorderLayout() );

        header.setFont( new Font( "Tahoma", 1, 18 ) );
        header.setHorizontalAlignment( SwingConstants.CENTER );
        mainPanel.add( header, BorderLayout.PAGE_START );

        mainPanel.add( closeButton, BorderLayout.PAGE_END );

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
