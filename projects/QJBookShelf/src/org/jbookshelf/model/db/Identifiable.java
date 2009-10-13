/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;

/**
 * @author eav 2009
 */
public interface Identifiable
    extends
    Serializable
{
    Long getId();

    void setId(
        Long id );
}
