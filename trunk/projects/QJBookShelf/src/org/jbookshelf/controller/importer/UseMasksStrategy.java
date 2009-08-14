package org.jbookshelf.controller.importer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;

public class UseMasksStrategy
    implements
    FileImportStrategy
{
    private static final Logger    log     = Logger.getLogger( FileImporter.class );
    private final List<NameParser> parsers = new ArrayList<NameParser>();

    public UseMasksStrategy(
        final String[] patterns )
    {
        super();
        // prepare parsers
        for ( final String string : patterns )
        {
            parsers.add( new NameParser( string ) );
        }
    }

    public Book importBook(
        final PhysicalBook physicalUnit )
    {
        for ( final NameParser parser : parsers )
        {
            final Book book = bookFromName( parser, physicalUnit );
            if ( book != null )
            {
                return book;
            }
        }
        return null;
    }

    /**
     * parse book name and create a book
     * 
     * @param parser parses name
     * @param physicalUnit physical book implementation
     * @return logical book
     */
    private Book bookFromName(
        final NameParser parser,
        final PhysicalBook physicalUnit )
    {
        try
        {
            parser.parse( FilenameUtils.getBaseName( physicalUnit.getFile().getName() ) );

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
