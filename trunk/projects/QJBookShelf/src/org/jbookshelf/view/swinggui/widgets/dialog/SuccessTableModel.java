/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.dialog;

import icons.IMG;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.i18n.I18N;

public class SuccessTableModel
    extends DefaultTableModel
{
    private static final String[] names =
                                        { I18N.tr( "Book" ), "" };

    private List<Book>            books = new ArrayList<Book>();

    public SuccessTableModel()
    {
        super( names, 0 );
    }

    public void addBook(
        final Book book )
    {
        books.add( book );
        final int row = books.size() - 1;
        fireTableRowsInserted( row, row );
    }

    /**
     * @return the books
     */
    public List<Book> getBooks()
    {
        return this.books;
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
        return books != null
            ? books.size() : 0;
    }

    @Override
    public Object getValueAt(
        final int row,
        final int column )
    {
        switch ( column )
        {
            case 0:
                return books.get( row ).getName();
            case 1:
                return IMG.icon( IMG.DOCUMENT_PROPERTIES_PNG );
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
     * @param books the books to set
     */
    public void setBooks(
        final List<Book> books )
    {
        this.books = books;
        fireTableDataChanged();
    }

}