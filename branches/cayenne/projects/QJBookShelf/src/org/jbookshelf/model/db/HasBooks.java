/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.Set;

/**
 * @author eav 2009
 */
public interface HasBooks
    extends
    Named
{
    Set<Book> getBooks();
}
