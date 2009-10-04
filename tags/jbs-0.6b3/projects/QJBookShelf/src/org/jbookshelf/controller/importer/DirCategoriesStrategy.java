package org.jbookshelf.controller.importer;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.i18n.I18N;

public class DirCategoriesStrategy
    extends UseMasksStrategy
{
    @SuppressWarnings( "unused" )
    private static final Logger   log             = Logger.getLogger( DirCategoriesStrategy.class );
    private final UseDirsStrategy useDirsStrategy = new UseDirsStrategy();

    @Override
    public Book importBook(
        final PhysicalBook physicalBook )
    {
        final Book book = super.importBook( physicalBook );
        if ( book != null )
        {
            final String[] dirNames = useDirsStrategy.dirNames( physicalBook );
            final Category category = useDirsStrategy.lastCategory( dirNames );
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
