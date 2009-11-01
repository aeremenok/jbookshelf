/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog.importer;

import icons.IMG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;

import org.jbookshelf.view.i18n.I18N;

/**
 * contains files that failed to be imported
 * 
 * @author eav 2009
 */
public class FailTableModel
    extends DefaultTableModel
{
    private static final String[] names =
                                        { I18N.tr( "File" ), "" };
    private List<File>            files = new ArrayList<File>();

    public FailTableModel()
    {
        super( names, 0 );
    }

    public void addFile(
        final File file )
    {
        files.add( file );
        final int row = files.size() - 1;
        fireTableRowsInserted( row, row );
    }

    @Override
    public Class<?> getColumnClass(
        final int columnIndex )
    {
        if ( columnIndex == 1 )
        {
            return Icon.class;
        }
        return super.getColumnClass( columnIndex );
    }

    /**
     * @return the files
     */
    public List<File> getFiles()
    {
        return this.files;
    }

    @Override
    public int getRowCount()
    {
        return files != null
            ? files.size() : 0;
    }

    @Override
    public Object getValueAt(
        final int row,
        final int column )
    {
        switch ( column )
        {
            case 0:
                return files.get( row ).getAbsolutePath();
            case 1:
                return IMG.icon( IMG.LIST_ADD_PNG );
        }
        return super.getValueAt( row, column );
    }

    @Override
    public boolean isCellEditable(
        final int row,
        final int column )
    {
        return false;
    }

    /**
     * @param file file to remove
     */
    public void removeFile(
        final File file )
    {
        final int indexOf = files.indexOf( file );
        files.remove( indexOf );
        fireTableRowsDeleted( indexOf, indexOf );
    }

    /**
     * @param files the files to set
     */
    public void setFiles(
        final List<File> files )
    {
        this.files = files;
        fireTableDataChanged();
    }

}
