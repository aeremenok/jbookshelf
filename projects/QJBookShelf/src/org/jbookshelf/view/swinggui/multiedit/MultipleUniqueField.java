/**
 * 
 */
package org.jbookshelf.view.swinggui.multiedit;

import org.jbookshelf.model.db.Unique;
import org.jbookshelf.model.db.util.BookShelf;
import org.xnap.commons.gui.Builder;
import org.xnap.commons.gui.completion.CompletionModel;

/**
 * allows to specify multiple {@link Unique}s
 * 
 * @author eav 2009
 * @param <T> {@link Unique} type
 */
public class MultipleUniqueField<T extends Unique>
    extends MultipleField<T>
{
    protected final Class<T> clazz;

    /**
     * @param clazz class of T to work with database
     */
    public MultipleUniqueField(
        final Class<T> clazz )
    {
        super();
        this.clazz = clazz;
        // add name completion 
        Builder.addCompletion( field, createCompletionModel( clazz ) );
    }

    protected CompletionModel createCompletionModel(
        final Class<T> clazz )
    {
        return new UniqueCompletionModel( clazz, model );
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
