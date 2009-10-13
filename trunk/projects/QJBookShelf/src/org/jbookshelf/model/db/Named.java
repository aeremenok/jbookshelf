/**
 * 
 */
package org.jbookshelf.model.db;

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
