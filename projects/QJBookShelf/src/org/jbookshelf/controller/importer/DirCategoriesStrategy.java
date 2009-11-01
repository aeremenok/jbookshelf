package org.jbookshelf.controller.importer;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.view.i18n.I18N;

public class DirCategoriesStrategy
    extends UseMasksStrategy
{
    @SuppressWarnings( "unused" )
    private static final Logger   log             = Logger.getLogger( DirCategoriesStrategy.class );
    private final UseDirsStrategy useDirsStrategy = new UseDirsStrategy();

    @Override
    public IBook importBook(
        final IPhysicalBook physicalBook )
    {
        final IBook book = super.importBook( physicalBook );
        if ( book != null )
        {
            final String[] dirNames = useDirsStrategy.dirNames( physicalBook );
            final ICategory category = useDirsStrategy.lastCategory( dirNames );
            book.getCategories().add( category );
        }
        return book;
    }

    @Override
    public String longDescription()
    {
        return I18N
            .tr( "<html>File names will be parsed using these masks: <b>%a</b> = author, <b>%b</b> = book, <b>%c</b> = category<br>Directories will be imported as categories</html>" );
    }

    @Override
    public String toString()
    {
        return I18N.tr( "Use masks and import directories as categroies" );
    }

}
