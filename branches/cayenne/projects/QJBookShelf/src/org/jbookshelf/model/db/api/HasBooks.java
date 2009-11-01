/**
 * 
 */
package org.jbookshelf.model.db.api;

import java.util.Set;

import org.jbookshelf.model.db.api.spec.IBook;

/**
 * @author eav 2009
 */
public interface HasBooks
    extends
    Named
{
    Set<IBook> getBooks();
}
