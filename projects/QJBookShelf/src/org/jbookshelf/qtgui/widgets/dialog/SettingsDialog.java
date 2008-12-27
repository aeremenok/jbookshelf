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

import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class SettingsDialog
    extends QDialog
    implements
        JBookShelfSettings
{
    private Settings settings = Settings.getInstance();

    // private JFileChooser fileChooser = new JFileChooser();
    //
    // private JButton cancelButton = new JButton();
    // private JButton chooseJBSFolderButton = new JButton();
    // private JButton chooseTempFolderButton = new JButton();
    // private JButton okButton = new JButton();
    // private JButton restoreButton = new JButton();
    // private JButton saveButton = new JButton();
    //
    // private JLabel importMaskLabel = new JLabel();
    // private JLabel jbsFolderLabel = new JLabel();
    // private JLabel lafLabel = new JLabel();
    // private JLabel languageLabel = new JLabel();
    // private JLabel settingsLabel = new JLabel();
    // private JLabel tmpFolderLabel = new JLabel();
    //
    // private JTextField importTextField = new JTextField();
    // private JTextField jbsTextField = new JTextField();
    // private JTextField tmpTextField = new JTextField();
    //
    // private JComboBox lafComboBox = new JComboBox();
    // private JComboBox langComboBox = new JComboBox();

    public SettingsDialog(
        QWidget parent )
    {
        super( parent );

        initComponents();
        initListeners();

        registerComponents();

        arrangeSettingValues();

        // fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
    }

    private void arrangeSettingValues()
    {
        // langComboBox.setSelectedItem( settings.getProperty( LANGUAGE ) );
        // String language = langComboBox.getSelectedItem().toString();
        // settings.setProperty( JBookShelfSettings.LANGUAGE, language );
        // Resourses.switchLanguage( language );
        // lafComboBox.setSelectedItem( settings.getProperty( LAF ) );
        // lafComboBoxActionPerformed();
        // tmpTextField.setText( settings.getProperty( TEMP_FOLDER ) );
        // jbsTextField.setText( settings.getProperty( JBS_FOLDER ) );
        // importTextField.setText( settings.getProperty( IMPORT_MASK ) );
    }

    private void initComponents()
    {
        // settingsLabel.setFont( new Font( "Tahoma", 1, 18 ) );
        //
        // lafComboBox.setModel( new DefaultComboBoxModel( Resourses.LAF_NAMES ) );
        // langComboBox.setModel( new DefaultComboBoxModel( new String[] { "Russian", "English" } ) );
        //
        // JSeparator jSeparator1 = new JSeparator();
        // GroupLayout layout = new GroupLayout( getContentPane() );
        // getContentPane().setLayout( layout );
        // layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
        // layout.createSequentialGroup().addComponent( settingsLabel ).addContainerGap() ).addComponent( jSeparator1,
        // GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE ).addGroup(
        // layout.createSequentialGroup().addContainerGap().addComponent( saveButton ).addPreferredGap(
        // LayoutStyle.ComponentPlacement.RELATED ).addComponent( restoreButton ).addPreferredGap(
        // LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE ).addComponent( okButton )
        // .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED ).addComponent( cancelButton )
        // .addContainerGap() ).addGroup(
        // layout.createSequentialGroup().addContainerGap().addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent( importMaskLabel )
        // .addComponent( jbsFolderLabel ).addComponent( tmpFolderLabel ).addComponent( lafLabel )
        // .addComponent( languageLabel ) ).addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
        // .addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent( importTextField,
        // GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE ).addGroup(
        // GroupLayout.Alignment.TRAILING,
        // layout.createSequentialGroup().addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.TRAILING ).addComponent( langComboBox,
        // GroupLayout.Alignment.LEADING, 0, 485, Short.MAX_VALUE ).addComponent( lafComboBox,
        // GroupLayout.Alignment.LEADING, 0, 485, Short.MAX_VALUE ).addComponent( tmpTextField,
        // GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE )
        // .addComponent( jbsTextField, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE ) )
        // .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED ).addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent(
        // chooseTempFolderButton ).addComponent( chooseJBSFolderButton ) ) ) )
        // .addContainerGap() ) );
        // layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
        // GroupLayout.Alignment.TRAILING,
        // layout.createSequentialGroup().addComponent( settingsLabel ).addPreferredGap(
        // LayoutStyle.ComponentPlacement.RELATED ).addComponent( jSeparator1, GroupLayout.PREFERRED_SIZE, 10,
        // GroupLayout.PREFERRED_SIZE ).addPreferredGap( LayoutStyle.ComponentPlacement.RELATED ).addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( languageLabel )
        // .addComponent( langComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
        // GroupLayout.PREFERRED_SIZE ) ).addGap( 18, 18, 18 ).addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( lafLabel ).addComponent(
        // lafComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) )
        // .addGap( 18, 18, 18 ).addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( tmpFolderLabel )
        // .addComponent( tmpTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
        // GroupLayout.PREFERRED_SIZE ).addComponent( chooseTempFolderButton ) ).addGap( 18, 18, 18 )
        // .addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( jbsFolderLabel )
        // .addComponent( chooseJBSFolderButton ).addComponent( jbsTextField, GroupLayout.PREFERRED_SIZE,
        // GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) ).addGap( 18, 18, 18 ).addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( importMaskLabel )
        // .addComponent( importTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
        // GroupLayout.PREFERRED_SIZE ) ).addGap( 18, 18, 18 ).addGroup(
        // layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( saveButton )
        // .addComponent( restoreButton ).addComponent( cancelButton ).addComponent( okButton ) )
        // .addContainerGap() ) );
        //
        // pack();
    }

    private void initListeners()
    {
        // restoreButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // settings.loadDefaults();
        // arrangeSettingValues();
        // }
        // } );
        //
        // cancelButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // dispose();
        // }
        // } );
        //
        // okButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // readSettingValues();
        // settings.save( settings.getSettingsFile().getAbsolutePath(), true );
        // dispose();
        // }
        // } );
        //
        // saveButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // readSettingValues();
        // settings.saveAsDefaults();
        // }
        // } );
        //
        // chooseJBSFolderButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // fileChooser.setCurrentDirectory( new File( settings.getProperty( JBS_FOLDER ) ) );
        // if ( fileChooser.showOpenDialog( SettingsDialog.this ) == JFileChooser.APPROVE_OPTION )
        // {
        // jbsTextField.setText( fileChooser.getSelectedFile().getAbsolutePath() );
        // }
        // }
        // } );
        //
        // chooseTempFolderButton.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // fileChooser.setCurrentDirectory( new File( settings.getProperty( TEMP_FOLDER ) ) );
        // if ( fileChooser.showOpenDialog( SettingsDialog.this ) == JFileChooser.APPROVE_OPTION )
        // {
        // tmpTextField.setText( fileChooser.getSelectedFile().getAbsolutePath() );
        // }
        // }
        // } );
        //
        // lafComboBox.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // lafComboBoxActionPerformed();
        // }
        // } );
        //
        // langComboBox.addActionListener( new ActionListener()
        // {
        // public void actionPerformed(
        // ActionEvent evt )
        // {
        // String language = langComboBox.getSelectedItem().toString();
        // settings.setProperty( JBookShelfSettings.LANGUAGE, language );
        // Resourses.switchLanguage( language );
        // }
        // } );
    }

    private void lafComboBoxActionPerformed()
    {
        // try
        // {
        // String lafName = lafComboBox.getSelectedItem().toString();
        // settings.setProperty( JBookShelfSettings.LAF, lafName );
        //
        // UIManager.setLookAndFeel( Resourses.getLAFClassName( lafName ) );
        //
        // SwingUtilities.updateComponentTreeUI( this );
        // pack();
        //
        // SwingUtilities.updateComponentTreeUI( getParent() );
        // ((Window) getParent()).pack();
        // }
        // catch ( Exception ex )
        // {
        // throw new Error( ex );
        // }
    }

    private void readSettingValues()
    {
        // settings.setProperty( LANGUAGE, langComboBox.getSelectedItem().toString() );
        // settings.setProperty( LAF, lafComboBox.getSelectedItem().toString() );
        // settings.setProperty( TEMP_FOLDER, tmpTextField.getText() );
        // settings.setProperty( JBS_FOLDER, jbsTextField.getText() );
        // settings.setProperty( IMPORT_MASK, importTextField.getText() );
    }

    /**
     * register components to be localized
     */
    private void registerComponents()
    {
        // restoreButton.setName( "restoreButton" );
        // cancelButton.setName( "cancelButton" );
        // okButton.setName( "okButton" );
        // saveButton.setName( "saveButton" );
        // settingsLabel.setName( "settingsLabel" );
        // languageLabel.setName( "languageLabel" );
        // lafLabel.setName( "lafLabel" );
        // tmpFolderLabel.setName( "tmpFolderLabel" );
        // jbsFolderLabel.setName( "jbsFolderLabel" );
        // importMaskLabel.setName( "importMaskLabel" );
        // chooseJBSFolderButton.setName( "chooseJBSFolderButton" );
        // chooseTempFolderButton.setName( "chooseTempFolderButton" );
        //
        // Resourses.register( getClass(), restoreButton );
        // Resourses.register( getClass(), chooseJBSFolderButton );
        // Resourses.register( getClass(), cancelButton );
        // Resourses.register( getClass(), okButton );
        // Resourses.register( getClass(), saveButton );
        // Resourses.register( getClass(), chooseTempFolderButton );
        //
        // Resourses.register( getClass(), settingsLabel );
        // Resourses.register( getClass(), languageLabel );
        // Resourses.register( getClass(), lafLabel );
        // Resourses.register( getClass(), tmpFolderLabel );
        // Resourses.register( getClass(), jbsFolderLabel );
        // Resourses.register( getClass(), importMaskLabel );
    }
}
