/**
 * 
 */
package org.jbookshelf.view.swinggui.widget.tables;

/**
 * creates {@link Record}s for {@link ExpandTableModel}
 * 
 * @author eav 2009
 * @param <T> record type
 */
public interface RecordFactory<T extends Record>
{
    /**
     * @param row a row of the table
     * @return a record for this row
     * @throws Exception failed to create {@link Record}
     */
    T createRecord(
        int row )
        throws Exception;

    /**
     * @return names of columns for the table
     */
    String[] getColumnNames();

    /**
     * @return total number of records
     */
    int recordCount();
}
