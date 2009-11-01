/**
 * 
 */
package org.jbookshelf.model.db.api;


/**
 * @author eav
 */
public interface Named
    extends
    Identifiable
{
    String getName();

    void setName(
        String name );
}
