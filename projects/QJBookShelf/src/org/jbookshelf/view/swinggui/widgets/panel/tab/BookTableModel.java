/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.view.i18n.I18N;

public class BookTableModel
    extends DefaultTableModel
{
    private static String[] names =
                                  { I18N.tr( "Name" ), I18N.tr( "Author" ), I18N.tr( "Category" ) };

    private List<Object[]>  rows  = new ArrayList<Object[]>();

    public BookTableModel()
    {
        super( names, 0 );
    }

    @Override
    public void addRow(
        final Object[] rowData )
    {
        rows.add( rowData );
        final int row = rows.size();
        fireTableRowsInserted( row, row );
    }

    public List<Book> getBooks(
        final int[] rowNumbers )
    {
        final List<Book> list = new ArrayList<Book>();
        for ( final int i : rowNumbers )
        {
            final Object id = rows.get( i )[3];
            list.add( BookShelf.bookById( id ) );
        }
        return list;
    }

    @Override
    public int getRowCount()
    {
        return rows != null
            ? rows.size() : 0;
    }

    @Override
    public Object getValueAt(
        final int row,
        final int column )
    {
        return rows.get( row )[column];
    }

    @Override
    public boolean isCellEditable(
        final int row,
        final int column )
    {
        return false;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(
        final List<Object[]> rows )
    {
        this.rows = rows;
        fireTableDataChanged();
    }
}