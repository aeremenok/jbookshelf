package org.jbookshelf.controller.importer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.view.i18n.I18N;

public class UseMasksStrategy
    implements
    FileImportStrategy
{
    private static final Logger    log     = Logger.getLogger( FileImporter.class );

    private final List<NameParser> parsers = new ArrayList<NameParser>();

    public IBook importBook(
        final IPhysicalBook physicalUnit )
    {
        for ( final NameParser parser : parsers )
        {
            final IBook book = bookFromName( parser, physicalUnit );
            if ( book != null )
            {
                return book;
            }
        }
        return null;
    }

    @Override
    public String longDescription()
    {
        return I18N
            .tr( "<html>File names will be parsed using these masks: <b>%a</b> = author, <b>%b</b> = book, <b>%c</b> = category</html>" );
    }

    public void setMasks(
        final List<String> masks )
    {
        // prepare parsers
        for ( final String string : masks )
        {
            parsers.add( new NameParser( string ) );
        }
    }

    @Override
    public String toString()
    {
        return I18N.tr( "Use masks only" );
    }

    /**
     * parse book name and create a book
     * 
     * @param parser parses name
     * @param physicalUnit physical book implementation
     * @return logical book
     */
    private IBook bookFromName(
        final NameParser parser,
        final IPhysicalBook physicalUnit )
    {
        try
        {
            parser.parse( FilenameUtils.getBaseName( physicalUnit.getFileName() ) );

            final String authorName = parser.getAuthorName();
            final String categoryName = parser.getCategoryName();
            final String bookName = parser.getBookName();

            if ( bookName != null && !"".equals( bookName ) )
            {
                return BookShelf.createBook( bookName, authorName, categoryName, physicalUnit );
            }
            return null;
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            return null;
        }
    }
}
