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
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.qtgui;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QSplitter;

/**
 * @author eav
 */
public class MainWindow
    extends QMainWindow
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
        String[] args )
    {
        QApplication.initialize( args );

        getInstance().show();

        QApplication.exec();
    }

    private MainWindow()
    {
        super();

        ((SingleFileStorageImpl) Storage.getImpl()).setCollectionStorageFile( settings.getCollectionFile() );
        Storage.loadCollection();

        // todo set look-and-feel

        initComponents();
    }

    private void initComponents()
    {
        addToolBar( ToolBar.getInstance() );

        QSplitter splitter = new QSplitter();
        setCentralWidget( splitter );
        splitter.addWidget( CollectionPanel.getInstance() );
        splitter.addWidget( RelatedPanel.getInstance() );
    }

    @Override
    protected void closeEvent(
        QCloseEvent arg__1 )
    {
        try
        {
            Storage.saveCollection();
        }
        catch ( Throwable e1 )
        {
            e1.printStackTrace();
        }

        super.closeEvent( arg__1 );
    }
}
