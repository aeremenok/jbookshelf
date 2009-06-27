/**
 * 
 */
package org.jbookshelf.view.swinggui.widget.tables;

/**
 * @author eav 2009
 * @param <T>
 */
public interface RecordFactory<T extends Record>
{
    T createRecord(
        int index );

    int recordCount();
}
