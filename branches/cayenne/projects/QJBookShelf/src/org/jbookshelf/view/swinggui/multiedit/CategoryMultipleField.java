/**
 * 
 */
package org.jbookshelf.view.swinggui.multiedit;

import org.apache.log4j.Category;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.xnap.commons.gui.completion.CompletionModel;

/**
 * do not allow user to put books into the ROOT {@link Category}
 * 
 * @author eav 2009
 */
public class CategoryMultipleField
    extends MultipleUniqueField<ICategory>
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
            if ( !ICategory.ROOT.equals( anObject.toString() ) )
            {
                super.addElement( anObject );
            }
        }
    }

    public CategoryMultipleField()
    {
        super( ICategory.class );
    }

    @Override
    protected CompletionModel createCompletionModel(
        final Class<ICategory> clazz )
    { // block completion
        return new CategoryCompletionModel( clazz, model );
    }

    @Override
    protected ICategory fromString(
        final String text )
    { // block addition
        final ICategory category = BookShelf.getOrAddUnique( ICategory.class, text );
        return BookShelf.rootCategory().equals( category )
            ? null : category;
    }
}
