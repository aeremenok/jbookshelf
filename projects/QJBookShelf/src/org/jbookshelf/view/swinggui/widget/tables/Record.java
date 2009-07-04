/**
 * 
 */
package org.jbookshelf.view.swinggui.widget.tables;

/**
 * a row of {@link ExpandTableModel}
 * 
 * @author eav 2009
 */
public interface Record
{
    /**
     * @param column table column
     * @return value at this column
     */
    Object getValueAt(
        final int column );
}
