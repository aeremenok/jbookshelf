/**
 * 
 */
package org.jbookshelf.model.db.api.spec;

import org.jbookshelf.model.db.api.HasBooks;

/**
 * @author eav
 * @param <B>
 */
public interface IAuthor<B extends IBook>
    extends
    HasBooks<B>
{}
