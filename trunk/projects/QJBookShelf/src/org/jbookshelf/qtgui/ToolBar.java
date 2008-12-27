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

import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;

import com.trolltech.qt.gui.QToolBar;

/**
 * @author eav
 */
public class ToolBar
    extends QToolBar
    implements
        UniqueSelectionListener
{
    private static final ToolBar instance = new ToolBar();

    private Unique               selectedUnique;

    public static ToolBar getInstance()
    {
        return instance;
    }

    public ToolBar()
    {
        initComponents();
    }

    public void nothingSelected()
    {
        // removeButton.setEnabled( false );
        // openButton.setEnabled( false );
        // editButton.setEnabled( false );
    }

    public void selectedUnique(
        Unique unique )
    {
        this.selectedUnique = unique;
        // removeButton.setEnabled( true );
        if ( selectedUnique instanceof ReadingUnit )
        {
            // openButton.setEnabled( true );
            // editButton.setEnabled( true );
        }
    }

    private void backupButtonActionPerformed()
    {
        // JFileChooser chooser = new JFileChooser();
        // chooser.setMultiSelectionEnabled( false );
        // chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        //
        // SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        // chooser.setSelectedFile( storage.getCollectionStorageFile() );
        //
        // if ( chooser.showSaveDialog( this ) == JFileChooser.APPROVE_OPTION )
        // {
        // Storage.backupCollection( chooser.getSelectedFile() );
        // }
    }

    private void importButtonActionPerformed()
    {
        // JFileChooser chooser = new JFileChooser();
        // chooser.setLocale( Resourses.getCurrentLocale() );
        // chooser.setMultiSelectionEnabled( false );
        // chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        //
        // if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        // {
        // FileImporter importer = new FileImporter()
        // {
        // @Override
        // protected void onImportFailure(
        // File file,
        // Exception e )
        // {
        // // todo
        // System.out.println( "-cannot import file " + file.getAbsolutePath() + " cause:" + e.getMessage() );
        // // e.printStackTrace();
        // }
        //
        // @Override
        // protected void onImportSuccess(
        // ReadingUnit book )
        // {
        // // todo
        // System.out.println( "+imported " + book.getAuthors().get( 0 ).getName() + ". " + book.getName() );
        // }
        // };
        //
        // String mask = Settings.getInstance().getProperty( JBookShelfSettings.IMPORT_MASK );
        // String result = JOptionPane.showInputDialog( "Enter import mask", mask ); // todo message
        // if ( result != null && !result.equals( "" ) )
        // {
        // mask = result;
        // importer.importFiles( chooser.getSelectedFile().listFiles(), mask, Storage.getBookShelf() );
        // }
        //
        // CollectionPanel.getInstance().updateTree();
        // }
    }

    private void initComponents()
    {
        // todo
    }

    private void removeButtonActionPerformed()
    {
        // if ( JOptionPane.showConfirmDialog( this, "Remove " + selectedUnique.getName() + "?" ) ==
        // JOptionPane.YES_OPTION )
        // {
        // Storage.getBookShelf().removeUnique( selectedUnique );
        // CollectionPanel.getInstance().removeSelectedItem();
        // JOptionPane.showMessageDialog( this, selectedUnique.getName() + " removed" );
        // }
    }

    private void restoreButtonActionPerformed()
    {
        // JFileChooser chooser = new JFileChooser();
        // chooser.setMultiSelectionEnabled( false );
        // chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        //
        // SingleFileStorageImpl storage = (SingleFileStorageImpl) Storage.getImpl();
        // chooser.setSelectedFile( storage.getCollectionStorageFile() );
        //
        // if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        // {
        // Storage.restoreCollection( chooser.getSelectedFile() );
        // CollectionPanel.getInstance().updateTree();
        // }
    }

}
