/**
 * 
 */
package org.jbookshelf.model.db.util;

/**
 * @author eav 2009
 */
public class SQLError
    extends Error
{
    public SQLError()
    {
        super();
    }

    public SQLError(
        final String message )
    {
        super( message );
    }

    public SQLError(
        final String message,
        final Throwable cause )
    {
        super( message, cause );
    }

    public SQLError(
        final Throwable cause )
    {
        super( cause );
    }
}
