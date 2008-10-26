package org.jbookshelf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @author eav
 * @model
 */
public interface ReadingUnit
    extends
        Unique,
        Commentable,
        Categorizable,
        EObject
{
    /**
     * @return
     * @model
     */
    EList<Author> getAuthors();
}
