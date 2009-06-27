/**
 * 
 */
package org.jbookshelf.view.swinggui.widget.tables;

import java.io.Serializable;

/**
 * @author eav 2009
 */
public interface Record
    extends
    Serializable
{
    String[] getColumnNames();

    Object getValueAt(
        final int column );
}
