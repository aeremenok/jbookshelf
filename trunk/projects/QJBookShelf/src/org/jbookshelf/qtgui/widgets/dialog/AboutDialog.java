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
package org.jbookshelf.qtgui.widgets.dialog;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class AboutDialog
    extends QDialog
{
    // private JLabel header = new JLabel();
    // private JLabel authorLabel = new JLabel();
    // private JLabel authorValueLabel = new JLabel();
    // private JLabel licenseLabel = new JLabel();
    // private JLabel licenseValueLabel = new JLabel();
    // private JLabel versionLabel = new JLabel();
    // private JLabel versionValueLabel = new JLabel();

    // private JButton closeButton = new JButton();

    public AboutDialog(
        QWidget parent )
    {
        super( parent );
        registerComponents();
        initComponents();
        initListeners();
    }

    private void initComponents()
    {
        // setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        //
        // JPanel mainPanel = new JPanel();
        // add( mainPanel );
        // mainPanel.setLayout( new BorderLayout() );
        //
        // header.setFont( new Font( "Tahoma", 1, 18 ) );
        // header.setHorizontalAlignment( SwingConstants.CENTER );
        // mainPanel.add( header, BorderLayout.PAGE_START );
        //
        // mainPanel.add( closeButton, BorderLayout.PAGE_END );
        //
        // JPanel contentPanel = new JPanel();
        // mainPanel.add( contentPanel, BorderLayout.CENTER );
        // contentPanel.setLayout( new GridLayout( 3, 2, 10, 10 ) );
        //
        // contentPanel.add( authorLabel );
        // contentPanel.add( authorValueLabel );
        //
        // contentPanel.add( versionLabel );
        // contentPanel.add( versionValueLabel );
        //
        // contentPanel.add( licenseLabel );
        // contentPanel.add( licenseValueLabel );
        //
        // pack();
    }

    private void initListeners()
    {
        // closeButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // dispose();
        // }
        // } );
    }

    private void registerComponents()
    {
        // closeButton.setName( "closeButton" );
        // authorLabel.setName( "authorLabel" );
        // authorValueLabel.setName( "authorValueLabel" );
        // licenseLabel.setName( "licenseLabel" );
        // licenseValueLabel.setName( "licenseValueLabel" );
        // versionLabel.setName( "versionLabel" );
        // versionValueLabel.setName( "versionValueLabel" );
        // header.setName( "header" );
        //
        // Resourses.register( getClass(), authorLabel );
        // Resourses.register( getClass(), authorValueLabel );
        // Resourses.register( getClass(), versionLabel );
        // Resourses.register( getClass(), versionValueLabel );
        // Resourses.register( getClass(), licenseLabel );
        // Resourses.register( getClass(), licenseValueLabel );
        // Resourses.register( getClass(), header );
        // Resourses.register( getClass(), closeButton );
    }
}
