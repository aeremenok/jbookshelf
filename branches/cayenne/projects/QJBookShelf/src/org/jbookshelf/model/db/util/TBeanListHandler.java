/**
 * 
 */
package org.jbookshelf.model.db.util;

import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * @author eav 2009
 * @param <T>
 */
public class TBeanListHandler<T>
    extends BeanListHandler
{
    public TBeanListHandler(
        final Class<T> type )
    {
        super( type );
    }

    public TBeanListHandler(
        final Class<T> type,
        final RowProcessor convert )
    {
        super( type, convert );
    }

}
