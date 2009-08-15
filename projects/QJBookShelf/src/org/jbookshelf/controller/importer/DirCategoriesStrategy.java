package org.jbookshelf.controller.importer;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.i18n.I18N;

public class DirCategoriesStrategy
    implements
    FileImportStrategy
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( DirCategoriesStrategy.class );

    public Book importBook(
        final PhysicalBook physicalBook )
    {
        // TODO Auto-generated method stub
        return null;
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
