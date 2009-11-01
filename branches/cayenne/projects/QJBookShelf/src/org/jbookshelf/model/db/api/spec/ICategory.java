/**
 * 
 */
package org.jbookshelf.model.db.api.spec;

import java.util.Set;

import org.jbookshelf.model.db.api.HasBooks;

/**
 * @author eav
 * @param <C>
 * @param <B>
 */
public interface ICategory<C extends ICategory, B extends IBook>
    extends
    HasBooks<B>
{
    String ROOT = "!ROOT!";

    Set<C> getChildren();

    ICategory getParent();

    void setParent(
        final ICategory parent );
}
