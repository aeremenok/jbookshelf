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
package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.gui.widgets.panel.CollectionPanel;
import org.jbookshelf.gui.widgets.panel.RelatedPanel;

/**
 * @author eav
 */
public class MainWindow
    extends JFrame
{
    private static MainWindow instance;

    private final Settings    settings = Settings.getInstance();

    public static MainWindow getInstance()
    {
        if ( instance == null )
        {
            instance = new MainWindow();
        }
        return instance;
    }

    public static void main(
        String args[] )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                MainWindow.getInstance().setVisible( true );
            }
        } );
    }

    public MainWindow()
    {
        ((SingleFileStorageImpl) Storage.getImpl()).setCollectionStorageFile( settings.getCollectionFile() );
        Storage.loadCollection();

        try
        {
            UIManager.setLookAndFeel( Resourses.getLAFClassName( settings.getProperty( JBookShelfSettings.LAF ) ) );
            SwingUtilities.updateComponentTreeUI( this );
        }
        catch ( Exception e )
        {
            throw new Error( e );
        }

        initComponents();
    }

    private void initComponents()
    {
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        add( ToolBar.getInstance(), BorderLayout.NORTH );

        JSplitPane splitPane = new JSplitPane();
        add( splitPane, BorderLayout.CENTER );

        splitPane.setOneTouchExpandable( true );
        splitPane.setLeftComponent( CollectionPanel.getInstance() );
        splitPane.setRightComponent( RelatedPanel.getInstance() );
        splitPane.setResizeWeight( 0.6 );

        addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing(
                WindowEvent e )
            {
                try
                {
                    Storage.saveCollection();
                }
                catch ( Throwable e1 )
                {
                    e1.printStackTrace();
                }
            }
        } );

        pack();
    }
}
