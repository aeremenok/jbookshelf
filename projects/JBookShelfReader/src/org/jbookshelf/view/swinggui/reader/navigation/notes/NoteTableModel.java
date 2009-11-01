/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.navigation.notes;

import icons.IMG;

import java.util.List;

import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;

import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.view.i18n.I18N;

public class NoteTableModel
    extends DefaultTableModel
{
    private static final String[] names = new String[]
                                        { I18N.tr( "Note" ), "" };
    private List<INote>           notes;

    public NoteTableModel()
    {
        super( names, 0 );
    }

    @Override
    public Class<?> getColumnClass(
        final int columnIndex )
    {
        switch ( columnIndex )
        {
            case 0:
                return INote.class;
            case 1:
                return Icon.class;
        }
        return super.getColumnClass( columnIndex );
    }

    public List<INote> getNotes()
    {
        return this.notes;
    }

    @Override
    public int getRowCount()
    {
        return notes == null
            ? 0 : notes.size();
    }

    @Override
    public Object getValueAt(
        final int row,
        final int column )
    {
        switch ( column )
        {
            case 0:
                return notes.get( row );
            case 1:
                return IMG.icon( IMG.LIST_REMOVE_PNG );
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

    public void setNotes(
        final List<INote> notes )
    {
        this.notes = notes;
        fireTableDataChanged();
    }

}