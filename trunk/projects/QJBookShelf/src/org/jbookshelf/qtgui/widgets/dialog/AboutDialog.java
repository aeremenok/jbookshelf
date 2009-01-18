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
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */
package org.jbookshelf.qtgui.widgets.dialog;

import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;

/**
 * About: a logo, a title, some info and a close button
 * 
 * @author eav
 */
public class AboutDialog
    extends QDialogExt
    implements
        JBookShelfConstants
{
    private QLabel      header            = new QLabel();
    private QLabel      authorLabel       = new QLabel();
    private QLabel      authorValueLabel  = new QLabel();
    private QLabel      licenseLabel      = new QLabel();
    private QLabel      licenseValueLabel = new QLabel();
    private QLabel      versionLabel      = new QLabel();
    private QLabel      versionValueLabel = new QLabel();

    private QPushButton closeButton       = new QPushButton();

    public AboutDialog(
        QWidget parent )
    {
        super( parent );
        initComponents();
        initListeners();

        retranslate();
    }

    public void retranslate()
    {
        setWindowTitle( tr( "About" ) );

        authorLabel.setText( tr( "Author" ) );
        licenseLabel.setText( tr( "License" ) );
        versionLabel.setText( tr( "Version" ) );

        authorValueLabel.setText( tr( "Andrey Yeremenok (eav1986_at_gmail_com)" ) );
        licenseValueLabel.setText( "GPL v.3" );
        versionValueLabel.setText( QCoreApplication.applicationVersion() );

        closeButton.setText( tr( "&Close" ) );
    }

    private void initComponents()
    {
        setModal( true );

        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        QLabel picLabel = new QLabel();
        picLabel.setPixmap( new QPixmap( ICONPATH + "logo-128.png" ) );

        layout.addWidget( picLabel, 0, 0 );
        layout.addWidget( header, 0, 1 );

        layout.addWidget( authorLabel, 1, 0 );
        layout.addWidget( authorValueLabel, 1, 1 );
        layout.addWidget( licenseLabel, 2, 0 );
        layout.addWidget( licenseValueLabel, 2, 1 );
        layout.addWidget( versionLabel, 3, 0 );
        layout.addWidget( versionValueLabel, 3, 1 );

        layout.addWidget( closeButton, 4, 1 );

        header.setText( QCoreApplication.applicationName() );
        header.setFont( new QFont( "Tahoma", 14 ) );
    }

    private void initListeners()
    {
        closeButton.released.connect( this, "close()" );
    }
}