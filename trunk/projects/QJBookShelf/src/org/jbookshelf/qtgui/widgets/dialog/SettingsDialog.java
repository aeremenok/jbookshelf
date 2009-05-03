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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jbookshelf.controller.Settings;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.qtgui.MainWindow;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;

import com.trolltech.qt.core.QRect;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QStyleFactory;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QFileDialog.FileMode;

/**
 * A dialog for editing program {@link Settings}
 * 
 * @author eav
 */
public class SettingsDialog
    extends QDialogExt
    implements
        PropertyChangeListener
{
    private final Settings     settings       = Singletons.instance( Settings.class );

    private final QPushButton  cancelButton   = new QPushButton( this );
    private final QPushButton  okButton       = new QPushButton( this );

    private final QLabel       lafLabel       = new QLabel( this );
    private final QLabel       langLabel      = new QLabel( this );
    private final QLabel       settingsLabel  = new QLabel( this );
    private final QLabel       jbsFolderLabel = new QLabel( this );

    private final QComboBox    lafComboBox    = new QComboBox( this );
    private final QComboBox    langComboBox   = new QComboBox( this );

    private final FilePathEdit jbsFolder      = new FilePathEdit( this );

    public SettingsDialog(
        final QWidget parent )
    {
        super( parent );

        initComponents();
        initListeners();

        getSettingValues();

        retranslate();
    }

    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        // apply new settings when LAF or LANGUAGE are changed
        final String propertyName = evt.getPropertyName();
        if ( propertyName.equals( settings.LAF.getKey() ) )
        {
            // QApplication.setStyle( settings.LAF.getValue() );
        }
        else if ( propertyName.equals( settings.LANGUAGE.getKey() ) )
        {
            Translator.retranslate( settings.LANGUAGE.getValue() );
        }
    }

    public void retranslate()
    {
        setWindowTitle( tr( "Settings" ) );

        settingsLabel.setText( tr( "Settings" ) );
        langLabel.setText( tr( "Language" ) );
        lafLabel.setText( tr( "Look-and-feel" ) );
        jbsFolderLabel.setText( tr( "JBookShelf folder" ) );

        okButton.setText( tr( "OK" ) );
        cancelButton.setText( tr( "Cancel" ) );

        jbsFolder.setCaption( tr( "Select a folder" ) );
    }

    private void getSettingValues()
    {
        langComboBox.setCurrentIndex( langComboBox.findText( settings.LANGUAGE.getValue() ) );
        // lafComboBox.setCurrentIndex( lafComboBox.findText( settings.LAF.getValue() ) );
        jbsFolder.setText( settings.JBS_DIR.getValue() );
    }

    private void initComponents()
    {
        setModal( true );

        final QRect geometry = geometry();
        geometry.setWidth( 770 );
        geometry.moveCenter( Singletons.instance( MainWindow.class ).geometry().center() );
        setGeometry( geometry );

        final QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( settingsLabel, 0, 0, 1, 2 );

        layout.addWidget( langLabel, 1, 0 );
        layout.addWidget( langComboBox, 1, 1 );

        layout.addWidget( lafLabel, 2, 0 );
        layout.addWidget( lafComboBox, 2, 1 );

        layout.addWidget( jbsFolderLabel, 3, 0 );
        layout.addWidget( jbsFolder, 3, 1 );

        final QWidget buttonWidget = new QWidget();
        buttonWidget.setLayout( new QHBoxLayout() );
        buttonWidget.layout().addWidget( okButton );
        buttonWidget.layout().addWidget( cancelButton );
        layout.addWidget( buttonWidget, 4, 0, 1, 2 );

        settingsLabel.setFont( new QFont( "Tahoma", 14 ) );

        langComboBox.addItem( "English" );
        langComboBox.addItem( "Russian" );

        lafComboBox.addItems( QStyleFactory.keys() );

        jbsFolder.setFileMode( FileMode.DirectoryOnly );
    }

    private void initListeners()
    {
        lafComboBox.currentStringChanged.connect( settings.LAF, "setValue(Object)" );
        langComboBox.currentStringChanged.connect( settings.LANGUAGE, "setValue(Object)" );

        cancelButton.released.connect( this, "onCancel()" );
        okButton.released.connect( this, "onOK()" );

        settings.addPropertyChangeListener( this );
    }

    @SuppressWarnings( "unused" )
    private void onCancel()
    {
        // load the old settings, so they can be applied via PropertyChangeEvent
        settings.load();
        settings.firePropertyChange( settings.LAF.getKey(), "", settings.LAF.getValue() );
        settings.firePropertyChange( settings.LANGUAGE.getKey(), "", settings.LANGUAGE.getValue() );
        close();
    }

    @SuppressWarnings( "unused" )
    private void onOK()
    {
        setSettingValues();
        settings.save();
        close();
    }

    private void setSettingValues()
    {
        settings.LANGUAGE.setValue( langComboBox.currentText() );
        // settings.LAF.setValue( lafComboBox.currentText() );
        settings.JBS_DIR.setValue( jbsFolder.text() );
    }
}