/**
 * 
 */
package org.jbookshelf.model.db.api.spec;

import java.util.Date;

import org.jbookshelf.model.db.api.Bookmark;

/**
 * @author eav
 */
public interface INote
    extends
    Bookmark
{
    Date getChangeDate();

    String getCitation();

    String getContent();

    String getTitle();

    void setCitation(
        final String citation );

    void setContent(
        final String content );

    void setTitle(
        final String title );

    void timestamp();

}
