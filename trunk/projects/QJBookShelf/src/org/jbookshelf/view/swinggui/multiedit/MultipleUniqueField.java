/**
 * 
 */
package org.jbookshelf.view.swinggui.multiedit;

import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Unique;
import org.xnap.commons.gui.Builder;

/**
 * allows to specify multiple {@link Unique}s
 * 
 * @author eav 2009
 * @param <T> {@link Unique} type
 */
public class MultipleUniqueField<T extends Unique>
    extends MultipleField<T>
{
    private final Class<T> clazz;

    /**
     * @param clazz class of T to work with database
     */
    public MultipleUniqueField(
        final Class<T> clazz )
    {
        super();
        this.clazz = clazz;
        // add name completion
        Builder.addCompletion( field, new UniqueCompletionModel( clazz, model ) );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.MultipleField#convertValue(java.lang.String)
     */
    @Override
    protected T fromString(
        final String text )
    {
        return BookShelf.getOrAddUnique( clazz, text );
    }
}
