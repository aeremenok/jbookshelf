/**
 * 
 */
package org.jbookshelf.model.db.api.spec;

import java.util.Set;

import org.jbookshelf.model.db.api.HasBooks;

/**
 * @author eav
 */
public interface ICategory
    extends
    HasBooks
{
    String ROOT = "!ROOT!";

    Set<ICategory> getChildren();

    ICategory getParent();

    void setParent(
        final ICategory parent );
}
