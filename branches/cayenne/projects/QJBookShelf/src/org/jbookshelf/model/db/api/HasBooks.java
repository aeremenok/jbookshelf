/**
 * 
 */
package org.jbookshelf.model.db.api;

import java.util.Set;

import org.jbookshelf.model.db.api.spec.IBook;

/**
 * @author eav 2009
 * @param <B>
 */
public interface HasBooks<B extends IBook>
    extends
    Named
{
    Set<B> getBooks();
}
