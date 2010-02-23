/**
 * 
 */
package org.jbookshelf.controller.importer;

import static java.util.Arrays.asList;
import static java.util.Collections.binarySearch;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.Named;

/**
 * @author eav 2010
 */
public class CompareUtil
{
    public static class BookComparator
        implements
        Comparator<Book>
    {
        @Override
        public int compare(
            final Book o1,
            final Book o2 )
        {
            if ( !new EqualsBuilder().append( o1.getName(), o2.getName() ).isEquals() )
            {
                return 1;
            }
            if ( !isEqual( o1.getAuthors(), o2.getAuthors(), AUTHOR_COMPARATOR ) )
            {
                return 1;
            }
            if ( !isEqual( o1.getCategories(), o2.getCategories(), CATEGORY_COMPARATOR ) )
            {
                return 1;
            }
            return 0;
        }
    }

    public static class NamedComparator<N extends Named>
        implements
        Comparator<N>
    {
        @Override
        public int compare(
            final N o1,
            final N o2 )
        {
            return new CompareToBuilder().append( o1.getName(), o2.getName() ).toComparison();
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger               log                 = Logger.getLogger( CompareUtil.class );

    private final static Comparator<Author>   AUTHOR_COMPARATOR   = new NamedComparator<Author>();

    private final static Comparator<Category> CATEGORY_COMPARATOR = new NamedComparator<Category>();

    public static Set<Author> authors(
        final String... authorNames )
    {
        final Set<Author> authors = new HashSet<Author>();
        for ( final String name : authorNames )
        {
            final Author author = new Author();
            author.setName( name );
            authors.add( author );
        }
        return authors;
    }

    @SuppressWarnings( "unchecked" )
    public static Book book(
        final String bookName,
        final Set... sets )
    {
        final Book book = new Book();
        book.setName( bookName );
        if ( sets.length > 0 )
        {
            book.getAuthors().addAll( sets[0] );
        }
        if ( sets.length > 1 )
        {
            book.getCategories().addAll( sets[1] );
        }
        return book;
    }

    public static File file(
        final String resourcePath )
        throws URISyntaxException
    {
        return new File( CompareUtil.class.getResource( resourcePath ).toURI() );
    }

    public static <T> boolean isEqual(
        final Collection<T> collection1,
        final Collection<T> collection2,
        final Comparator<T> comparator )
    {
        if ( collection1 == collection2 )
        {
            return true;
        }
        if ( collection1 == null || collection2 == null )
        {
            return false;
        }

        if ( collection1.size() != collection2.size() )
        {
            return false;
        }

        final List<T> list2 = new ArrayList<T>( collection2 );
        for ( final T t : collection1 )
        {
            final int found = binarySearch( list2, t, comparator );
            if ( found == -1 )
            {
                return false;
            }
        }
        return true;
    }

    public static Set<Book> set(
        final Book... books )
    {
        return new HashSet<Book>( asList( books ) );
    }

}
