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
package org.jbookshelf.qtgui.widgets;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.ext.QFileDialogExt;

import com.trolltech.qt.gui.QGroupBox;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QFileDialog.FileMode;

/**
 * file selection widget: a textfield and a file dialog button
 * 
 * @author eav
 */
public class FilePathEdit
    extends QGroupBox
    implements
        JBookShelfConstants
{
    private final QLineEdit   edit    = new QLineEdit( this );
    private final QPushButton button  = new QPushButton( this );
    private FileMode          mode    = FileMode.AnyFile;
    private String            caption = "";

    public FilePathEdit()
    {
        super();
        initComponents();
    }

    public FilePathEdit(
        QPrivateConstructor p )
    {
        super( p );
        initComponents();
    }

    public FilePathEdit(
        QWidget parent )
    {
        super( parent );
        initComponents();
    }

    public void setCaption(
        String caption )
    {
        this.caption = caption;
    }

    public void setFileMode(
        FileMode mode )
    {
        this.mode = mode;
    }

    public void setText(
        String text )
    {
        edit.setText( text );
        saveConfig( text );
    }

    public String text()
    {
        return edit.text();
    }

    @SuppressWarnings( "unused" )
    private void chooseFile()
    {
        QFileDialogExt fileDialog = new QFileDialogExt( this, caption )
        {
            @Override
            protected void filesSelected()
            {
                setText( selectedFiles().get( 0 ) );
                fileSelected( selectedFiles().get( 0 ) );
            }
        };
        fileDialog.selectFile( text() );
        fileDialog.setFileMode( mode );
        fileDialog.show();
    }

    private String getKey()
    {
        return parent().getClass().getName() + "/" + getClass().getName();
    }

    private void initComponents()
    {
        QHBoxLayout layout = new QHBoxLayout();
        setLayout( layout );
        layout.addWidget( edit );
        layout.addWidget( button );

        button.setIcon( new QIcon( ICONPATH + "document-open-folder.png" ) );

        // trying to set the last used path
        String lastPath = Settings.getInstance().getProperty( getKey() );
        if ( lastPath != null )
        {
            edit.setText( lastPath );
        }

        button.released.connect( this, "chooseFile()" );
        edit.textChanged.connect( this, "saveConfig(String)" );
    }

    private void saveConfig(
        String lastPath )
    {
        Settings.getInstance().setProperty( getKey(), lastPath );
        Settings.getInstance().save();
    }

    protected void fileSelected(
        @SuppressWarnings( "unused" ) String fileName )
    {
    }
}
