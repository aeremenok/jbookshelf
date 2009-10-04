/**
 * 
 */
package org.jbookshelf.view.swinggui.multiedit;

import icons.IMG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;

import org.jbookshelf.view.i18n.I18N;

/**
 * contains uniques for multi-edit table with no duplicates
 * 
 * @author eav 2009
 * @param <V> unique type ( {@link Object#toString()} used for displaying the values )
 */
public class ListTableModel<V>
    extends DefaultTableModel
{
    /**
     * model values, contains no duplicates
     */
    private final List<V>         values = new ArrayList<V>();

    /**
     * column names
     */
    private static final String[] names  =
                                         { I18N.tr( "Name" ), "" };

    public ListTableModel()
    {
        super( names, 0 );
    }

    public void addValue(
        final V value )
    {
        if ( !values.contains( value ) )
        {
            values.add( value );
            fireTableDataChanged();
        }
    }

    public void clear()
    {
        values.clear();
        fireTableDataChanged();
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

    @Override
    public int getRowCount()
    {
        return values != null
            ? values.size() : 0;
    }

    @Override
    public Object getValueAt(
        final int row,
        final int column )
    {
        switch ( column )
        {
            case 0:
                return values.get( row );
            case 1:
                return IMG.icon( IMG.LIST_REMOVE_PNG );
        }
        return super.getValueAt( row, column );
    }

    public List<V> getValues()
    {
        return this.values;
    }

    @Override
    public boolean isCellEditable(
        final int row,
        final int column )
    {
        return false;
    }

    @Override
    public void removeRow(
        final int row )
    {
        values.remove( row );
        fireTableRowsDeleted( row, row );
    }

    public void setValues(
        final Collection<V> objects )
    {
        values.clear();
        values.addAll( objects );
        fireTableDataChanged();
    }
}