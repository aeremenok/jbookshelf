package org.jbookshelf.controller.importer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;

public class UseDirsStrategy
    implements
    FileImportStrategy
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( UseDirsStrategy.class );

    public String[] dirNames(
        final PhysicalBook physicalBook )
    {
        return physicalBook.getFileName().split( "/" );
    }

    public Book importBook(
        final PhysicalBook physicalBook )
    {
        final Book book = new Book();

        book.setName( FilenameUtils.getBaseName( physicalBook.getFile().getName() ) );
        book.setPhysicalBook( physicalBook );

        final String[] dirNames = dirNames( physicalBook );
        final Author author = BookShelf.getOrAddUnique( Author.class, dirNames[dirNames.length - 2] );
        book.getAuthors().add( author );

        final Category category = lastCategory( dirNames );
        if ( category != null )
        {
            book.getCategories().add( category );
        }

        return book;
    }

    public Category lastCategory(
        final String[] dirNames )
    {
        final List<Category> categories = new ArrayList<Category>();
        categories.add( BookShelf.rootCategory() );

        Category category = null;
        for ( int i = 0; i < dirNames.length - 2; i++ )
        {
            final String dirName = dirNames[i];
            category = BookShelf.getOrAddCategory( dirName, categories.get( i ) );
            categories.add( category );
        }
        return category;
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
