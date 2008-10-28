package org.jbookshelf;

import org.eclipse.emf.ecore.EObject;

/**
 * stores metadata on hdd<br>
 * now - a single file <br>
 * further - consider using javadb
 * 
 * @author eav
 * @model
 */
public interface BookShelfStorage extends EObject
{
    /**
     * @model
     */
    void loadCollection();

    /**
     * @model
     */
    void saveCollection();
}
