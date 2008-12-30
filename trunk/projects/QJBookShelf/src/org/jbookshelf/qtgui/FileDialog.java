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

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.trolltech.qt.gui.QFileDialog;
import com.trolltech.qt.gui.QWidget;

/**
 * @author eav
 */
public class FileDialog
    extends QFileDialog
{
    public FileDialog(
        QWidget parent )
    {
        super( parent );
        connect();
        setModal( true );
    }

    public FileDialog(
        QWidget parent,
        String caption )
    {
        super( parent, caption );
        connect();
        setModal( true );
    }

    public FileDialog(
        QWidget parent,
        String caption,
        String directory )
    {
        super( parent, caption, directory );
        connect();
        setModal( true );
    }

    public FileDialog(
        QWidget parent,
        String caption,
        String directory,
        String filter )
    {
        super( parent, caption, directory, filter );
        connect();
        setModal( true );
    }

    public File getSelectedFile()
    {
        return new File( selectedFiles().get( 0 ) );
    }

    public File[] getSelectedFileArray()
    {
        File selectedFile = getSelectedFile();
        if ( selectedFile.isDirectory() )
        {
            return selectedFile.listFiles();
        }

        List<String> selectedFiles = selectedFiles();
        File[] files = new File[selectedFiles.size()];
        for ( int i = 0; i < files.length; i++ )
        {
            files[i] = new File( selectedFiles.get( i ) );
        }
        return files;
    }

    public List<File> getSelectedFiles()
    {
        return Arrays.asList( getSelectedFileArray() );
    }

    public void setSelectedFile(
        File file )
    {
        selectFile( file.getAbsolutePath() );
    }

    private void connect()
    {
        filesSelected.connect( this, "filesSelected()" );
    }

    protected void filesSelected()
    {
    }
}