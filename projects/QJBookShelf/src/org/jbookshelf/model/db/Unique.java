/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;

/**
 * @author eav
 */
public interface Unique
    extends
    Serializable
{
    Long getId();

    String getName();

    void setId(
        Long id );

    void setName(
        String name );
}
