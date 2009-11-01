/**
 * 
 */
package org.jbookshelf.view.swinggui.multiedit;

import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.util.BookShelf;
import org.xnap.commons.gui.completion.CompletionModel;

/**
 * do not allow user to put books into the ROOT {@link Category}
 * 
 * @author eav 2009
 */
public class CategoryMultipleField
    extends MultipleUniqueField<Category>
{
    /**
     * @author eav 2009
     */
    private final class CategoryCompletionModel
        extends UniqueCompletionModel
    {
        private CategoryCompletionModel(
            final Class<? extends Named> clazz,
            final ListTableModel<? extends Named> model )
        {
            super( clazz, model );
        }

        @Override
        public void addElement(
            final Object anObject )
        {
            if ( !Category.ROOT.equals( anObject.toString() ) )
            {
                super.addElement( anObject );
            }
        }
    }

    public CategoryMultipleField()
    {
        super( Category.class );
    }

    @Override
    protected CompletionModel createCompletionModel(
        final Class<Category> clazz )
    { // block completion
        return new CategoryCompletionModel( clazz, model );
    }

    @Override
    protected Category fromString(
        final String text )
    { // block addition
        final Category category = BookShelf.getOrAddUnique( Category.class, text );
        return BookShelf.rootCategory().equals( category )
            ? null : category;
    }
}
