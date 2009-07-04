/**
 * 
 */
package org.jbookshelf.view.swinggui.widget.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

/**
 * table model that shows only the head {@link Record} collection and can be expanded further
 * 
 * @author eav 2009
 * @param <T> record type
 */
public class ExpandTableModel<T extends Record>
    extends DefaultTableModel
{
    private static final Logger    log    = Logger.getLogger( ExpandTableModel.class );

    /**
     * displayed records
     */
    private final List<T>          window = new ArrayList<T>();
    /**
     * creates records
     */
    private final RecordFactory<T> factory;
    /**
     * number of records to expand at every time
     */
    private final int              pageSize;
    /**
     * total number of records
     */
    private int                    recordCount;

    public ExpandTableModel(
        final RecordFactory<T> factory,
        final int pageSize )
    {
        super( factory.getColumnNames(), 0 );

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

    public synchronized void reset()
    {
        window.clear();
        fireTableDataChanged();
        recordCount = factory.recordCount();
        expand();
    }
}
