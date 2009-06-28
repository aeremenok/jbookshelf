/**
 * 
 */
package org.jbookshelf.view.swinggui.widget.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

/**
 * @author eav 2009
 * @param <T>
 */
public class ExpandTableModel<T extends Record>
    extends DefaultTableModel
{
    private static final Logger log = Logger.getLogger( ExpandTableModel.class );

    private static <T> T newInstance(
        final Class<T> clazz )
    {
        try
        {
            return clazz.newInstance();
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    private final List<T>          window = new ArrayList<T>();
    private final RecordFactory<T> factory;
    private final int              pageSize;
    private int                    recordCount;

    public ExpandTableModel(
        final Class<T> recordClass,
        final RecordFactory<T> factory,
        final int pageSize )
    {
        super( newInstance( recordClass ).getColumnNames(), 0 );

        this.factory = factory;
        this.pageSize = pageSize;

        this.recordCount = factory.recordCount();
    }

    public synchronized void expand()
    {
        try
        {
            final int offset = window.size();
            final int end = Math.min( offset + pageSize, recordCount );
            for ( int windowIndex = offset; windowIndex < end; windowIndex++ )
            {
                final T record = factory.createRecord( windowIndex );
                window.add( record );
            }

            fireTableRowsInserted( offset, window.size() - 1 );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public int getRowCount()
    {
        return window != null
            ? window.size() : 0;
    }

    @Override
    public Object getValueAt(
        final int row,
        final int column )
    {
        return window.get( row ).getValueAt( column );
    }

    @Override
    public boolean isCellEditable(
        final int row,
        final int column )
    {
        return false;
    }

    public void reset()
    {
        window.clear();
        fireTableDataChanged();
        recordCount = factory.recordCount();
        expand();
    }
}
