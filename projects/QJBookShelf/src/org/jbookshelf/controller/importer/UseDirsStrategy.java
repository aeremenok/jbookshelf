package org.jbookshelf.controller.importer;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.i18n.I18N;

public class UseDirsStrategy
    implements
    FileImportStrategy
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( UseDirsStrategy.class );

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
            .tr( "<html>Directory, where a book resides, will be imported as the author's name<br>Its parents will be imported as categories</html>" );
    }

    @Override
    public String toString()
    {
        return I18N.tr( "Import directories only, the deepest one is author" );
    }

}
