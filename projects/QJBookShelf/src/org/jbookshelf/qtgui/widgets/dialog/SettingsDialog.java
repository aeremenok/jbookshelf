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

import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.FilePathEdit;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QStyleFactory;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QFileDialog.FileMode;

/**
 * Shows program {@link Settings}, allows edit them, save as defaults and restore from defaults
 * 
 * @author eav
 */
public class SettingsDialog
    extends QDialogExt
    implements
        JBookShelfSettings
{
    private Settings     settings            = Settings.getInstance();

    private QPushButton  cancelButton        = new QPushButton( this );
    private QPushButton  okButton            = new QPushButton( this );
    private QPushButton  restoreButton       = new QPushButton( this );
    private QPushButton  saveButton          = new QPushButton( this );

    private QLabel       importMaskLabel     = new QLabel( this );
    private QLabel       lafLabel            = new QLabel( this );
    private QLabel       langLabel           = new QLabel( this );
    private QLabel       settingsLabel       = new QLabel( this );

    private QLineEdit    importMaskTextField = new QLineEdit( this );

    private QComboBox    lafComboBox         = new QComboBox( this );
    private QComboBox    langComboBox        = new QComboBox( this );

    private FilePathEdit jbsFolder           = new FilePathEdit( this );
    private FilePathEdit tmpFolder           = new FilePathEdit( this );

    private QLabel       jbsFolderLabel      = new QLabel( this );
    private QLabel       tmpFolderLabel      = new QLabel( this );

    public SettingsDialog(
        QWidget parent )
    {
        super( parent );

        initComponents();
        initListeners();

        arrangeSettingValues();

        retranslate();
    }

    public void retranslate()
    {
        setWindowTitle( tr( "Settings" ) );

        settingsLabel.setText( tr( "Settings" ) );
        langLabel.setText( tr( "Language" ) );
        lafLabel.setText( tr( "Look-and-feel" ) );
        jbsFolderLabel.setText( tr( "JBookShelf folder" ) );
        tmpFolderLabel.setText( tr( "Temp folder" ) );
        importMaskLabel.setText( tr( "Default import mask" ) );

        saveButton.setText( tr( "Save as defaults" ) );
        restoreButton.setText( tr( "Restore defaults" ) );
        okButton.setText( tr( "OK" ) );
        cancelButton.setText( tr( "Cancel" ) );

        jbsFolder.setCaption( tr( "Select a folder" ) );
        tmpFolder.setCaption( tr( "Select a folder" ) );
    }

    private void arrangeSettingValues()
    {
        langComboBox.setCurrentIndex( langComboBox.findText( settings.getProperty( LANGUAGE ) ) );
        lafComboBox.setCurrentIndex( lafComboBox.findText( settings.getProperty( LAF ) ) );

        tmpFolder.setText( settings.getProperty( TEMP_FOLDER ) );
        jbsFolder.setText( settings.getProperty( JBS_FOLDER ) );
        importMaskTextField.setText( settings.getProperty( IMPORT_MASK ) );
    }

    private void initComponents()
    {
        setModal( true );

        QGridLayout layout = new QGridLayout();
        setLayout( layout );

        layout.addWidget( settingsLabel, 0, 0, 1, 5 );

        layout.addWidget( langLabel, 1, 0 );
        layout.addWidget( langComboBox, 1, 1, 1, 4 );

        layout.addWidget( lafLabel, 2, 0 );
        layout.addWidget( lafComboBox, 2, 1, 1, 4 );

        layout.addWidget( jbsFolderLabel, 3, 0 );
        layout.addWidget( jbsFolder, 3, 1, 1, 4 );

        layout.addWidget( tmpFolderLabel, 4, 0 );
        layout.addWidget( tmpFolder, 4, 1, 1, 4 );

        layout.addWidget( importMaskLabel, 5, 0 );
        layout.addWidget( importMaskTextField, 5, 1, 1, 4 );

        layout.addWidget( saveButton, 6, 0 );
        layout.addWidget( restoreButton, 6, 1 );
        layout.addWidget( new QLabel( " " ), 6, 2 ); // todo insert spacer
        layout.addWidget( okButton, 6, 3 );
        layout.addWidget( cancelButton, 6, 4 );

        settingsLabel.setFont( new QFont( "Tahoma", 14 ) );

        langComboBox.addItem( "English" );
        langComboBox.addItem( "Russian" );

        lafComboBox.addItems( QStyleFactory.keys() );

        jbsFolder.setFileMode( FileMode.DirectoryOnly );
        tmpFolder.setFileMode( FileMode.DirectoryOnly );
    }

    private void initListeners()
    {
        lafComboBox.currentIndexChanged.connect( this, "lafComboBoxActionPerformed()" );
        langComboBox.currentIndexChanged.connect( this, "languageChanged()" );

        cancelButton.released.connect( this, "close()" );
        okButton.released.connect( this, "onOK()" );

        saveButton.released.connect( this, "onSave()" );
        restoreButton.released.connect( this, "onRestore()" );
    }

    @SuppressWarnings( "unused" )
    private void lafComboBoxActionPerformed()
    {
        String lafName = lafComboBox.currentText();
        settings.setProperty( JBookShelfSettings.LAF, lafName );

        QApplication.setStyle( lafName );
    }

    @SuppressWarnings( "unused" )
    private void languageChanged()
    {
        String language = langComboBox.currentText();
        settings.setProperty( LANGUAGE, language );

        Translator.retranslate( language );
        retranslate();
    }

    @SuppressWarnings( "unused" )
    private void onOK()
    {
        readSettingValues();
        settings.save( settings.getSettingsFile().getAbsolutePath(), true );
        close();
    }

    @SuppressWarnings( "unused" )
    private void onRestore()
    {
        settings.loadDefaults();
        arrangeSettingValues();
    }

    @SuppressWarnings( "unused" )
    private void onSave()
    {
        readSettingValues();
        settings.saveAsDefaults();
    }

    private void readSettingValues()
    {
        settings.setProperty( LANGUAGE, langComboBox.currentText() );
        settings.setProperty( LAF, lafComboBox.currentText() );
        settings.setProperty( TEMP_FOLDER, tmpFolder.text() );
        settings.setProperty( JBS_FOLDER, jbsFolder.text() );
        settings.setProperty( IMPORT_MASK, importMaskTextField.text() );
    }
}