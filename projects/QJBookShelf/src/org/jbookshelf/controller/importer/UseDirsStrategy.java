package org.jbookshelf.controller.importer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.view.i18n.I18N;

public class UseDirsStrategy
    implements
    FileImportStrategy
{
    public String[] dirNames(
        final IPhysicalBook physicalBook )
    {
        return physicalBook.getFileName().split( "/" );
    }

    @SuppressWarnings( "unchecked" )
    public IBook importBook(
        final IPhysicalBook physicalBook )
    {
        final IBook book = BookShelf.createBook();

        book.setName( FilenameUtils.getBaseName( physicalBook.getFile().getName() ) );
        book.setPhysicalBook( physicalBook );

        final String[] dirNames = dirNames( physicalBook );
        final IAuthor author = BookShelf.getOrAddUnique( IAuthor.class, dirNames[dirNames.length - 2] );
        book.getAuthors().add( author );

        final ICategory category = lastCategory( dirNames );
        if ( category != null )
        {
            book.getCategories().add( category );
        }

        return book;
    }

    public ICategory lastCategory(
        final String[] dirNames )
    {
        final List<ICategory> categories = new ArrayList<ICategory>();
        categories.add( BookShelf.rootCategory() );

        ICategory category = null;
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
