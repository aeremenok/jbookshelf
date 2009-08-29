/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.util.ArrayList;
import java.util.List;

import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 * @param <PageType>
 */
public abstract class ReaderFactory<PageType>
{
    protected final List<String> features = new ArrayList<String>();

    public abstract BookContent<PageType> createBookContent(
        Book book );

    public List<String> getFeatures()
    {
        return this.features;
    }
}
