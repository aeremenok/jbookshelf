/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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

import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.controller.storage.SingleFileStorageImpl;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.completion.CompletionDictionary;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;
import org.jbookshelf.settings.Settings;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.Qt.WindowState;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QSplitter;

/**
 * The app starts here
 * 
 * @author eav
 */
public class MainWindow
    extends QMainWindow
    implements
        JBookShelfConstants,
        Singleton
{
    public static void main(
        final String[] args )
    {
        QApplication.initialize( args );

        QCoreApplication.setApplicationVersion( "0.4b2" );
        QCoreApplication.setApplicationName( "JBookShelf" );

        Singletons.instance( MainWindow.class ).show();

        QApplication.exec();
    }

    public void initSingleton()
    {
        initCollection();
        initLAF();

        initComponents();

        initLanguage();
    }

    private void initCollection()
    {
        final SingleFileStorageImpl singleFileStorageImpl = (SingleFileStorageImpl) Storage.getImpl();
        singleFileStorageImpl.setCollectionStorageFile( Singletons.instance( Settings.class ).getCollectionFile() );
        Storage.loadCollection();
    }

    private void initComponents()
    {
        setWindowTitle( "JBookShelf" );
        setWindowIcon( new QIcon( ICONPATH + "logo-64.png" ) );

        setWindowState( WindowState.WindowMaximized );
        addToolBar( Singletons.instance( ToolBar.class ) );

        final QSplitter splitter = new QSplitter();
        setCentralWidget( splitter );
        splitter.addWidget( Singletons.instance( CollectionPanel.class ) );
        splitter.addWidget( Singletons.instance( RelatedPanel.class ) );
    }

    private void initLAF()
    {
        // final Settings settings = Singletons.instance( Settings.class );
        // String lafName = settings.LAF.getValue();
        // if ( lafName == null )
        // {
        // final String os = System.getProperty( "os.name" );
        // if ( os.toLowerCase().contains( "windows" ) )
        // {
        // lafName = "WindowsXP";
        // }
        // else
        // {
        // lafName = "Plastique";
        // }
        // // todo mac os
        // settings.LAF.setValue( lafName );
        // }
        // QApplication.setStyle( lafName );
    }

    private void initLanguage()
    {
        Translator.retranslate( Singletons.instance( Settings.class ).LANGUAGE.getValue() );
    }

    @Override
    protected void closeEvent(
        final QCloseEvent arg__1 )
    {
        try
        {
            Storage.saveCollection();
            Singletons.instance( CompletionDictionary.class ).save();
        }
        catch ( final Throwable e1 )
        {
            e1.printStackTrace();
        }

        super.closeEvent( arg__1 );
    }
}
