/**
 * 
 */
package org.jbookshelf.model.db.api;

import java.io.Serializable;

/**
 * @author eav 2009
 */
public interface Identifiable
    extends
    Serializable
{
    Serializable getId();

    void setId(
        Serializable id );
}
